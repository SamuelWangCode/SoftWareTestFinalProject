package com.eruka.loginAndlogonservice.controller;

import com.eruka.loginAndlogonservice.MD5Util;
import com.eruka.loginAndlogonservice.model.LolRegisterInfo;
import com.eruka.loginAndlogonservice.model.LolUser;
import com.eruka.loginAndlogonservice.model.LolUserInfo;
import com.eruka.loginAndlogonservice.result.Result;
import com.eruka.loginAndlogonservice.result.ResultFactory;
import com.eruka.loginAndlogonservice.service.RegisterService;
import com.eruka.loginAndlogonservice.service.SelectionHelperService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Objects;

@Slf4j
@RestController
@Api("Logon-Controller")
@RequestMapping("/api")
public class RegisterController {
    @Autowired
    RegisterService registerService;
    @Autowired
    SelectionHelperService selectionHelperService;

    @ApiImplicitParam(name = "registerInfo" , value = "注册信息" ,dataType = "LolRegisterInfo" , required = true)
    @ApiOperation(value = "注册",notes = "注册API", produces = "application/json")
    @PostMapping("/register")
    public Result register(@Valid @RequestBody LolRegisterInfo registerInfo, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            String msg = String.format("注册失败，详细信息[%s]。",
                    Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
            return ResultFactory.buildFailResult(msg);
        }
        if (selectionHelperService.selectByNickName(registerInfo.getNickName()) != null){
            return ResultFactory.buildFailResult("用户名已存在");
        }
        if (selectionHelperService.selectByAccount(registerInfo.getAccount()) != null){
            return ResultFactory.buildFailResult("邮箱已存在");
        }

        //添加用户
        LolUser user = new LolUser();
        user.setAccount(registerInfo.getAccount());
        user.setPassword(registerInfo.getPassword());
        //加密
        MD5Util.encrypt(user);
        try {
            registerService.userRegister(user);
        }catch (Exception e){
            // 基本不会触发
            return ResultFactory.buildFailResult("邮箱已存在, 且用户回滚");
        }
        if (user.getUID() == null){
            return ResultFactory.buildFailResult("注册失败");
        }

        //添加信息
        LolUserInfo userInfo = new LolUserInfo();
        userInfo.setUID(user.getUID());
        userInfo.setNickName(registerInfo.getNickName());

        try{
            registerService.addInfo(userInfo);
        }catch (Exception e){
            // 基本不会触发
            return ResultFactory.buildFailResult("用户名已存在, 且用户回滚");
        }
        return ResultFactory.buildSuccessResult("用户注册成功");
    }
}
