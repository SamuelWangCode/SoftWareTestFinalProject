package com.eruka.guideservice.mapper;

import com.eruka.guideservice.model.Guide;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PostGuideMapper {

    Long postGuide(Guide newGuide);
}
