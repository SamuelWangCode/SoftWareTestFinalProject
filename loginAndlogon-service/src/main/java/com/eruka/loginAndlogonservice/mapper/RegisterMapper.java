package com.eruka.loginAndlogonservice.mapper;

import com.eruka.loginAndlogonservice.model.LolUser;
import com.eruka.loginAndlogonservice.model.LolUserInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RegisterMapper {
    Long userRegister(LolUser user);
    Long addInfo(LolUserInfo info);

}
