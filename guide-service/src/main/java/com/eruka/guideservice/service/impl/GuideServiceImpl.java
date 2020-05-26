package com.eruka.guideservice.service.impl;

import com.eruka.guideservice.mapper.GuideMapper;
import com.eruka.guideservice.model.Guide;
import com.eruka.guideservice.model.Item;
import com.eruka.guideservice.model.LolUserInfo;
import com.eruka.guideservice.result.Result;
import com.eruka.guideservice.result.ResultFactory;
import com.eruka.guideservice.service.GuideService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springfox.documentation.spring.web.json.Json;

import java.util.List;

@Service
public class GuideServiceImpl implements GuideService {
    @Autowired
    GuideMapper guideMapper;



    @Override
    public Result searchGuideByTitle(String title){
        List<Guide> guides;
        try {
            guides = guideMapper.searchGuideByTitle(title);
        }catch (Exception e){
            return  ResultFactory.buildFailResult(e.toString());
        }
        return ResultFactory.buildSuccessResult(guides);
    }

    @Override
    public Result searchGuideByUid(String uID){
        List<Guide> guides;
        try {
            guides = guideMapper.searchGuideByUid(uID);
        }catch (Exception e){
            return  ResultFactory.buildFailResult(e.toString());
        }
        return ResultFactory.buildSuccessResult(guides);
    }

    @Override
    public Result searchGuideByLikeNum(){
        List<Guide> guides;
        try{
            guides = guideMapper.searchGuideByLikeNum();
        }catch (Exception e){
            return ResultFactory.buildFailResult(e.toString());
        }
        return ResultFactory.buildSuccessResult(guides);
    }

    @Override
    public  Result getPreferGuideByuID(Result result){
        if(result.getCode()!= 200){
            return ResultFactory.buildFailResult(result.getMessage());
        }
        Gson gosn = new Gson();
        JsonObject jsonObject =gosn.toJsonTree(result.getData()).getAsJsonObject();
        int myprefer = jsonObject.get("prefer").getAsInt();
        List<Guide> guides;
        try{
            guides = guideMapper.searchGuideByPrefer(myprefer);
        }catch (Exception e){
            return ResultFactory.buildFailResult(e.toString());
        }
        return ResultFactory.buildSuccessResult(guides);
    }

    @Override
    public Result addlikeNumBygID(Integer gID){
        try {
            guideMapper.addLikeNumBygID(gID);
        }catch (Exception e){
            return  ResultFactory.buildFailResult(e.toString());
        }
        return ResultFactory.buildSuccessResult("success");
    }

    @Override
    public Result searchGuideBygID(Integer gID){
        Guide guide;
        try {
            guide = guideMapper.searchGuideBygID(gID);
        }catch (Exception e){
            return  ResultFactory.buildFailResult(e.toString());
        }
        return ResultFactory.buildSuccessResult(guide);
    }

    @Override
    public Result getItem(){
        List<Item> items;
        try{
            items = guideMapper.getItem();
        }catch (Exception e){
            return ResultFactory.buildFailResult(e.toString());
        }
        return ResultFactory.buildSuccessResult(items);
    }
}
