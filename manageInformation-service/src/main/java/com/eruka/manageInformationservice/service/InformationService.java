package com.eruka.manageInformationservice.service;

import com.eruka.manageInformationservice.result.Result;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;

public interface InformationService {
    Result getUserInfoByuID(Integer uID);

    Result getUserByname(String nickName);

    Result setUserIcon(MultipartFile file , Integer uID, String iconID) throws IOException;

    Boolean deleteFile(File file);

    Result updateUsersInfo(String nickName,Integer gender, Integer uID , Integer prefer);

    Result getUserInfobyaccount(String account);

    Result getUsersFollow(Integer uID);

    Result subscribeFollower(Integer uID , Integer oID);
}
