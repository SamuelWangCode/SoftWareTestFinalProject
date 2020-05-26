package com.eruka.searchChampionservice.controller;

import com.eruka.searchChampionservice.model.LolChampionInfo;
import com.eruka.searchChampionservice.result.Result;
import com.eruka.searchChampionservice.result.ResultFactory;
import com.eruka.searchChampionservice.service.SearchChampService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.Objects;

@Slf4j
//控制类
@RestController
@Api("SearchChampion-Controller")
@RequestMapping("/api")
public class SearchChampController {
    @Autowired
    SearchChampService searchChampService;


    @ApiImplicitParam(name = "cID" , value = "英雄ID" ,dataType = "String" ,paramType="path",required = true)
    @ApiOperation(value = "查看英雄详细信息",notes = "查看英雄详细信息API")
    @GetMapping("/checkChamp/{cID}")
    public Result check(@PathVariable String cID){
        int CID = Integer.parseInt(cID);
        return searchChampService.checkBycID(CID);
    }


    @ApiOperation(value = "英雄资料表",notes = "显示英雄资料API")
    @PostMapping("/showChamp")
    public Result init(){
        return searchChampService.showAll();
    }

    @ApiImplicitParam(name = "alias" , value = "英雄名" ,dataType = "String" ,paramType="path",required = true)
    @ApiOperation(value = "查找英雄",notes = "查找英雄API")
    @GetMapping("/searchChamp/{alias}")
    public Result searchChamp(@PathVariable String alias){
        return searchChampService.searchChampByName(alias);
    }

    @ApiImplicitParam(name = "tag" , value = "攻略种类" ,dataType = "String" ,paramType="path",required = true)
    @ApiOperation(value = "查找英雄",notes = "查找英雄API")
    @GetMapping("/searchChampBytag/{tag}")
    public Result searchChampBytag(@PathVariable String tag){
        int TAG = Integer.parseInt(tag);
        return searchChampService.searchChampBytag(TAG);
    }
}
