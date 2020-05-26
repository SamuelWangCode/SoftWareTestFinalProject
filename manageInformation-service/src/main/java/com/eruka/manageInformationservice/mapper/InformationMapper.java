package com.eruka.manageInformationservice.mapper;

import com.eruka.manageInformationservice.model.LolLoginInfo;
import com.eruka.manageInformationservice.model.LolUserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface InformationMapper {
    LolUserInfo getUserInfoByuID(@Param("uID") Integer uID);

    List<LolUserInfo> getUserByname(@Param("nickName") String nickName);

    LolUserInfo getUserByaccount(@Param("account") String account);

    Long updateUsersIconByID(@Param("uID") Integer uID, @Param("iconID") String iconID);

    Long updateUserInforByID(@Param("uID") Integer uID, @Param("nickName") String nickName, @Param("gender") Integer gender ,@Param("prefer") Integer prefer );

    List<LolUserInfo> selectUsersFollowByUid(@Param("uID") Integer uID);

    Integer selectPreferByuID(@Param("uID") Integer uID);

    Long insertUsersFollowByID(@Param("uID") Integer uID, @Param("followID") Integer followID);
}
