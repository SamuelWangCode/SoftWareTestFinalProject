package com.eruka.guideservice.controller;

import com.eruka.guideservice.model.Guide;
import com.eruka.guideservice.result.Result;
import com.eruka.guideservice.result.ResultFactory;
import com.eruka.guideservice.service.PostGuideService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.Objects;

@Slf4j
//控制类
@RestController
@Api("PostGuide-Controller")
@RequestMapping("/api")
public class PostGuideController {
    @Autowired
    PostGuideService postGuideService;


    @ApiOperation(value = "发布攻略",notes = "发布攻略API", produces = "application/json")
    @PostMapping("/postGuide")
    public Result postGudie(@RequestBody  Guide newGuide){
        return postGuideService.postGuide(newGuide);}


}
