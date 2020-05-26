package com.eruka.loginAndlogonservice.controller;


import com.eruka.loginAndlogonservice.MD5Util;
import com.eruka.loginAndlogonservice.model.LolLoginInfo;
import com.eruka.loginAndlogonservice.model.LolUser;
import com.eruka.loginAndlogonservice.service.LoginService;
import com.eruka.loginAndlogonservice.result.Result;
import com.eruka.loginAndlogonservice.result.ResultFactory;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Objects;

@Slf4j
//控制类
@RestController
@Api("Login-Controller")
@RequestMapping("/api")
public class LoginController {
    @Autowired
    LoginService loginService;

    @ApiImplicitParam(name = "loginInfo" , value = "登陆信息" ,dataType = "LolLoginInfo" , required = true)
    @ApiOperation(value = "登陆",notes = "登陆API")
    @PostMapping("/login")
    public Result Login(@Valid @RequestBody LolLoginInfo loginInfo, BindingResult bindingResult,HttpSession session){
        if (bindingResult.hasErrors()){
            String msg = String.format("注册失败，详细信息[%s]。",
                    Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
            return ResultFactory.buildFailResult(msg);
        }
        if (session.getAttribute("user") != null){
            return ResultFactory.buildFailResult("已经登陆了啦");
        }
        LolUser user;
        log.info(loginInfo.getAccount());
        try {
            log.info(loginService.toString());
            user = loginService.selectByAccount(loginInfo.getAccount());
        }catch (Exception e){
            return ResultFactory.buildFailResult(e.toString());
        }
        if( user == null){
            return ResultFactory.buildFailResult("不存在该用户");
        }
        String salt = user.getSalt();
        String MD5Pwd = MD5Util.getMD5Pwd(loginInfo.getPassword(), salt);
        if (MD5Pwd.equals(user.getPassword())){
            // session存储
            session.setAttribute("user", user);
            return ResultFactory.buildSuccessResult(user.getUID());
        }
        return ResultFactory.buildFailResult("密码错误");
    }

}
