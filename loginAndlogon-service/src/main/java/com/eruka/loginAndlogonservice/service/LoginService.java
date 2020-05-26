package com.eruka.loginAndlogonservice.service;

import com.eruka.loginAndlogonservice.model.LolUser;


public interface LoginService {
    LolUser selectByAccount(String account);
}