package com.eruka.manageInformationservice.controller;

import com.eruka.manageInformationservice.model.LolUserInfo;
import com.eruka.manageInformationservice.result.Result;
import com.eruka.manageInformationservice.service.InformationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;

@Slf4j
//控制类
@RestController
@Api("Information-Controller")
@RequestMapping("/api")
public class InformationController {
    @Autowired
    InformationService informationService;

    @ApiImplicitParam(name = "uID" , value = "用户ID" ,dataType = "String" , required = true)
    @ApiOperation(value = "查看个人信息",notes = "通过ID获取个人信息API")
    @GetMapping("/myInformation/{uID}")
    public Result getUserInfomationByID(@PathVariable String uID){
        int UID = Integer.parseInt(uID);
        return informationService.getUserInfoByuID(UID);
    }

    @ApiImplicitParam(name = "nickName" , value = "昵称" ,dataType = "String" , required = true)
    @ApiOperation(value = "查找个人信息",notes = "通过昵称获取个人信息API")
    @GetMapping("/searchUserByName/{nickName}")
    public Result getUserByName(@PathVariable String nickName){
        return informationService.getUserByname(nickName);
    }


    @ApiImplicitParams({
            @ApiImplicitParam(name = "uID" , value = "用户ID" ,dataType = "String" , required = true),
            @ApiImplicitParam(name = "iconID" , value = "先前图片ID" ,dataType = "String" ),
    })
    @ApiOperation(value = "个人头像",notes = "上传头像API")
    @PostMapping("/uploadIcon")
    public Result uploadIcon(@RequestParam("IconImg") MultipartFile file , @RequestParam("uID") String uID, @RequestParam("iconID") String iconID) throws IOException {
        int UID = Integer.parseInt(uID);
        return informationService.setUserIcon(file , UID , iconID);
    }

    @ApiImplicitParam(name = "lolUserInfo" , value = "登陆信息" ,dataType = "LolUserInfo" , required = true)
    @ApiOperation(value = "修改个人信息",notes = "修改个人信息API")
    @PostMapping("/setting")
    public Result updateUserInfo(@RequestBody LolUserInfo lolUserInfo){
        return informationService.updateUsersInfo(lolUserInfo.getNickName(),lolUserInfo.getGender(),lolUserInfo.getUID(),lolUserInfo.getPrefer());
    }



    @ResponseBody
    @ApiImplicitParam(name = "account" , value = "用户名" ,dataType = "String" , required = true)
    @ApiOperation(value = "查找个人信息",notes = "通过用户名获取个人信息API")
    @GetMapping("/searchUserByAccount/{account}")
    public Result getUserByAccount(@PathVariable String account){
        if(!account.contains(".")) {
            account = account + ".com";
        }
        return informationService.getUserInfobyaccount(account);
    }

    @ApiImplicitParam(name = "uID" , value = "昵称" , required = true)
    @ApiOperation(value = "查找个人关注者",notes = "通过ID获取个人关注者信息API")
    @GetMapping("/searchUsersFollow/{uID}")
    public Result getUsersFollow(@PathVariable Integer uID){
        return informationService.getUsersFollow(uID);
    }


    @ApiImplicitParams({
            @ApiImplicitParam(name = "uID" , value = "用户ID"  , required = true),
            @ApiImplicitParam(name = "oID" , value = "被关注用户ID"  ),
    })
    @ApiOperation(value = "关注用户",notes = "通过ID关注用户API")
    @GetMapping("/subscribe/{uID}/{oID}")
    public Result subscribeFollowerByID(@PathVariable Integer uID ,@PathVariable Integer oID){
        return informationService.subscribeFollower(uID , oID);
    }
}
