package com.eruka.searchChampionservice.service;

import com.eruka.searchChampionservice.model.LolChampion;
import com.eruka.searchChampionservice.model.LolChampionInfo;
import com.eruka.searchChampionservice.result.Result;

import java.util.List;

public interface SearchChampService {
    Result checkBycID(Integer cID);

    Result showAll();

    Result searchChampByName(String alias);

    Result searchChampBytag(Integer tag);
}
