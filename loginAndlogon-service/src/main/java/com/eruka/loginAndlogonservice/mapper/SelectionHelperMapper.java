package com.eruka.loginAndlogonservice.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SelectionHelperMapper {
    Integer selectByNickName(@Param("nickName") String nickName);
    Integer selectByAccount(@Param("account") String account);
}

