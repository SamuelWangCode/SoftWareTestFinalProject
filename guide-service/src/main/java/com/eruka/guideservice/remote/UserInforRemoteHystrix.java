package com.eruka.guideservice.remote;

import com.eruka.guideservice.result.Result;
import com.eruka.guideservice.result.ResultFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

@Component
public class UserInforRemoteHystrix implements UserInforRemote{

    @Override
    public Result getUserInfomationByID(@PathVariable String uID){
        return ResultFactory.buildFailResult("Connect to Server Error");
    }
}
