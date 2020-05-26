package com.eruka.loginAndlogonservice.service;


import com.eruka.loginAndlogonservice.model.LolUser;
import com.eruka.loginAndlogonservice.model.LolUserInfo;

public interface RegisterService {
    Long userRegister(LolUser user);
    Long addInfo(LolUserInfo info);
}
