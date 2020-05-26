package com.eruka.loginAndlogonservice.service;


public interface SelectionHelperService {
    Integer selectByNickName(String nickName);
    Integer selectByAccount(String account);
}
