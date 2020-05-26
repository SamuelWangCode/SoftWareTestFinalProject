package com.eruka.loginAndlogonservice.service.impl;

import com.eruka.loginAndlogonservice.mapper.RegisterMapper;
import com.eruka.loginAndlogonservice.model.LolUser;
import com.eruka.loginAndlogonservice.model.LolUserInfo;
import com.eruka.loginAndlogonservice.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterServiceImpl implements RegisterService {
    @Autowired
    private RegisterMapper registerMapper;

    @Override
    public Long userRegister(LolUser user) {
        return registerMapper.userRegister(user);
    }

    @Override
    public Long addInfo(LolUserInfo info) {
        return registerMapper.addInfo(info);
    }
}
