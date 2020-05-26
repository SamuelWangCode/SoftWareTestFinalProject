package com.eruka.searchChampionservice.mapper;

import com.eruka.searchChampionservice.model.LolChampion;
import com.eruka.searchChampionservice.model.LolChampionInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SearchChampMapper {
    LolChampionInfo checkBycID(@Param("cID") Integer cID);

    List<LolChampion> showAll();

    List<LolChampion> searchChampByname(@Param("alias") String alias);

    List<LolChampion> searchChampBytag(@Param("tags1") String tags1 , @Param("tags2") String tags2);
}
