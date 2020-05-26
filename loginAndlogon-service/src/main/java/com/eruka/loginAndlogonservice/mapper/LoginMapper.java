package com.eruka.loginAndlogonservice.mapper;

import com.eruka.loginAndlogonservice.model.LolUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface LoginMapper {
    LolUser selectByAccount(@Param("account") String account);
}
