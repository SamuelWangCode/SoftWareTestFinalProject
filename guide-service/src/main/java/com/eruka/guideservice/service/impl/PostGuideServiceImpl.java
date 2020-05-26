package com.eruka.guideservice.service.impl;

import com.eruka.guideservice.mapper.PostGuideMapper;
import com.eruka.guideservice.model.Guide;
import com.eruka.guideservice.result.Result;
import com.eruka.guideservice.result.ResultFactory;
import com.eruka.guideservice.service.PostGuideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class PostGuideServiceImpl implements PostGuideService {
    @Autowired
    PostGuideMapper postGuideMapper;



    @Override
    public Result postGuide(Guide newGuide){

        //添加帖子
        Guide guide = new Guide();
        guide.setUID(newGuide.getUID());
        guide.setChampionName(newGuide.getChampionName());
        guide.setTitle(newGuide.getChampionName() + ":" + newGuide.getTitle());
        guide.setContext(newGuide.getContext());
        guide.setChampionTag(newGuide.getChampionTag());
        guide.setChampionImg(newGuide.getChampionImg());
        guide.setItem1(newGuide.getItem1());
        guide.setItem2(newGuide.getItem2());
        guide.setItem3(newGuide.getItem3());
        guide.setItem4(newGuide.getItem4());
        guide.setItem5(newGuide.getItem5());
        guide.setItem6(newGuide.getItem6());

        try{
            postGuideMapper.postGuide(guide);
        }catch (Exception e){
            return ResultFactory.buildFailResult(e.toString());
        }
        return ResultFactory.buildSuccessResult("创建攻略成功！");
    }

}
