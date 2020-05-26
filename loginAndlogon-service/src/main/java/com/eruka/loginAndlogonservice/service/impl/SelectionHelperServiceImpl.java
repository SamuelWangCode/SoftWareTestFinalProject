package com.eruka.loginAndlogonservice.service.impl;

import com.eruka.loginAndlogonservice.mapper.SelectionHelperMapper;
import com.eruka.loginAndlogonservice.service.SelectionHelperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SelectionHelperServiceImpl implements SelectionHelperService {
    @Autowired
    SelectionHelperMapper selectionHelperMapper;

    @Override
    public Integer selectByNickName(String nickName) {
        return selectionHelperMapper.selectByNickName(nickName);
    }

    @Override
    public Integer selectByAccount(String account) {
        return selectionHelperMapper.selectByAccount(account);
    }
}
