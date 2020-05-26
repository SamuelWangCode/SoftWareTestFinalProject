package com.eruka.loginAndlogonservice.service.impl;

import com.eruka.loginAndlogonservice.mapper.LoginMapper;
import com.eruka.loginAndlogonservice.model.LolUser;
import com.eruka.loginAndlogonservice.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    LoginMapper loginMapper;

    @Override
    public LolUser selectByAccount(String account) {
        return loginMapper.selectByAccount(account);
    }

}
