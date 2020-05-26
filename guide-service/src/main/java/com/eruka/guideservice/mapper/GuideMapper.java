package com.eruka.guideservice.mapper;

import com.eruka.guideservice.model.Guide;
import com.eruka.guideservice.model.Item;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GuideMapper {

    List<Guide> searchGuideByTitle(@Param("title") String title);

    List<Guide> searchGuideByUid(@Param("uID") String uID);

    List<Guide> searchGuideByLikeNum();

    List<Guide> searchGuideByPrefer(@Param("prefer") Integer prefer);

    Long addLikeNumBygID(@Param("gID") Integer gID);

    Guide searchGuideBygID(@Param("gID") Integer gID);

    List<Item> getItem();
}