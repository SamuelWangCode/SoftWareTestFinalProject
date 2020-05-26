package com.eruka.guideservice.controller;

import com.eruka.guideservice.model.Guide;
import com.eruka.guideservice.model.LolUserInfo;
import com.eruka.guideservice.remote.UserInforRemote;
import com.eruka.guideservice.result.Result;
import com.eruka.guideservice.result.ResultFactory;
import com.eruka.guideservice.service.GuideService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.Objects;

@Slf4j
//控制类
@RestController
@Api("Guide-Controller")
@RequestMapping("/api")
public class GuideController {
    @Autowired
    GuideService guideService;

    @Autowired
    UserInforRemote userInforRemote;

    @ApiImplicitParam(name = "title" , value = "标题-英雄名" ,dataType = "String" ,paramType="path",required = true)
    @ApiOperation(value = "搜索帖子",notes = "搜索帖子作者API")
    @GetMapping("/searchGuideByTitle/{title}")
    public Result searchGuideByTitle(@PathVariable String title){
        return guideService.searchGuideByTitle(title);
    }

    @ApiImplicitParam(name = "uID" , value = "用户ID" ,dataType = "String" ,paramType="path",required = true)
    @ApiOperation(value = "搜索帖子",notes = "搜索帖子标题API")
    @GetMapping("/searchGuideByUid/{uID}")
    public Result searchGuideByUid(@PathVariable String uID){ return guideService.searchGuideByUid(uID); }

    @ApiOperation(value = "推荐热门帖子",notes = "推荐热门帖子API")
    @PostMapping("/searchGuideByLikeNum")
    public Result searchGuideByLikeNum(){ return guideService.searchGuideByLikeNum(); }

    @ApiImplicitParam(name = "uID" , value = "用户ID" ,dataType = "String" ,paramType="path",required = true)
    @ApiOperation(value = "推荐定制帖子",notes = "推荐符合帖子API")
    @GetMapping("/getPreferGuide/{uID}")
    public Result getPreferGuideByuID(@PathVariable String uID){
       return guideService.getPreferGuideByuID(userInforRemote.getUserInfomationByID(uID));
    }

    @ApiImplicitParam(name = "gID" , value = "帖子ID" ,dataType = "String" ,paramType="path",required = true)
    @ApiOperation(value = "点赞帖子",notes = "点赞帖子API")
    @GetMapping("/addLikeNum/{gID}")
    public Result addlikeNumBygID(@PathVariable String gID){
        int GID = Integer.parseInt(gID);
        return guideService.addlikeNumBygID(GID);
    }

    @ApiImplicitParam(name = "gID" , value = "帖子id" ,dataType = "String" ,paramType="path",required = true)
    @ApiOperation(value = "搜索帖子",notes = "通过gID搜索帖子API")
    @GetMapping("/searchGuideBygID/{gID}")
    public Result searchGuideBygID(@PathVariable String gID){
        int GID = Integer.parseInt(gID);
        return guideService.searchGuideBygID(GID);
    }

    @ApiOperation(value = "获取装备列表",notes = "获取装备列表API")
    @PostMapping("/getItem")
    public Result getItem(){
        return guideService.getItem();}
}
