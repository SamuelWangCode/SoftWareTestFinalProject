package com.eruka.searchChampionservice.service.impl;

import com.eruka.searchChampionservice.mapper.SearchChampMapper;
import com.eruka.searchChampionservice.model.LolChampion;
import com.eruka.searchChampionservice.model.LolChampionInfo;
import com.eruka.searchChampionservice.result.Result;
import com.eruka.searchChampionservice.result.ResultFactory;
import com.eruka.searchChampionservice.service.SearchChampService;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchChampServiceImpl implements SearchChampService {
    @Autowired
    SearchChampMapper searchChampMapper;

    @Override
    public Result checkBycID(Integer cID){
        LolChampionInfo lolChampionInfo;
        try {
            lolChampionInfo = searchChampMapper.checkBycID(cID);
        }catch (Exception e){
            return  ResultFactory.buildSuccessResult(e.toString());
        }
        return ResultFactory.buildSuccessResult(lolChampionInfo);
    }

    @Override
    public Result showAll(){
        List<LolChampion> lolChampions;
        try {
            lolChampions = searchChampMapper.showAll();
        }catch (Exception e){
            return  ResultFactory.buildSuccessResult(e.toString());
        }
        return ResultFactory.buildSuccessResult(lolChampions);
    }

    @Override
    public Result searchChampByName(String alias){
        List<LolChampion> lolChampions;
        try {
            lolChampions = searchChampMapper.searchChampByname(alias);
        }catch (Exception e){
            return  ResultFactory.buildSuccessResult(e.toString());
        }
        return ResultFactory.buildSuccessResult(lolChampions);
    }
//         * 英雄类型 Assassin、Fighter、Mage、Marksman、Support、Tank
    @Override
    public Result searchChampBytag(Integer tag){
        List<LolChampion> lolChampions;
        if(tag == 1){
            try {
                lolChampions = searchChampMapper.searchChampBytag("Assassin" , "Marksman");
            }catch (Exception e){
                return  ResultFactory.buildSuccessResult(e.toString());
            }
        }else if(tag == 2){
            try {
                lolChampions = searchChampMapper.searchChampBytag("Tank" , "Fighter");
            }catch (Exception e){
                return  ResultFactory.buildSuccessResult(e.toString());
            }
        }else{
            try {
                lolChampions = searchChampMapper.searchChampBytag("Support" , "Mage");
            }catch (Exception e){
                return  ResultFactory.buildSuccessResult(e.toString());
            }
        }
        return ResultFactory.buildSuccessResult(lolChampions);
    }
}
