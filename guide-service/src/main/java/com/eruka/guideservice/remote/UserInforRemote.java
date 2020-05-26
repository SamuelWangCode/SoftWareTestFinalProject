package com.eruka.guideservice.remote;

import com.eruka.guideservice.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient( name = "manageInformation-producer" , fallback = UserInforRemoteHystrix.class)
public interface UserInforRemote {

    @GetMapping("/api/myInformation/{uID}")
    Result getUserInfomationByID(@PathVariable String uID);
}
