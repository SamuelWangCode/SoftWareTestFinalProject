package com.eruka.manageInformationservice.service.impl;

import com.eruka.manageInformationservice.mapper.InformationMapper;
import com.eruka.manageInformationservice.model.LolUserInfo;
import com.eruka.manageInformationservice.result.Result;
import com.eruka.manageInformationservice.result.ResultFactory;
import com.eruka.manageInformationservice.service.InformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public class InformationServiceImpl implements InformationService {
    public final static String baseURL = System.getProperty("user.dir") + File.separator + "src"
            + File.separator + "resources" + File.separator;

    @Autowired
    InformationMapper informationMapper;

    @Override
    public Result getUserInfoByuID(Integer uID){
        if(uID == null){
            return ResultFactory.buildFailResult("请先登录");
        }
        LolUserInfo lolUserInfo;
        try {
            lolUserInfo = informationMapper.getUserInfoByuID(uID);
        }catch (Exception e){
            return ResultFactory.buildFailResult(e.toString());
        }
        return ResultFactory.buildSuccessResult(lolUserInfo);
    }

    @Override
    public Result getUserByname(String nickName){
        List<LolUserInfo> lolUserInfos;
        try {
            lolUserInfos = informationMapper.getUserByname(nickName);
        }catch (Exception e){
            return ResultFactory.buildFailResult(e.toString());
        }
        return ResultFactory.buildSuccessResult(lolUserInfos);
    }

    @Override
    public Result setUserIcon(MultipartFile file , Integer uID , String iconID) throws IOException {
        if(iconID == null){
            iconID = "default.jpg";
        }
        if (uID == null){
            return ResultFactory.buildFailResult("Please Login First");
        }
        if (!file.isEmpty()){
            if (file.getContentType() != null && file.getOriginalFilename()!= null){
                String fileName = file.getOriginalFilename();
                String type = fileName.substring(fileName.lastIndexOf("."));
                String uuid = UUID.randomUUID().toString().replace("-", "");
                String path = baseURL + "img" + File.separator + "userIcon" + File.separator + uID + File.separator + uuid + type;
                File dest = new File(path);
                //判断文件父目录是否存在
                if (!dest.getParentFile().exists()) {
                    dest.getParentFile().mkdir();
                }
                file.transferTo(dest);
                String final_path = uID + File.separator + uuid + type;
                try {
                    informationMapper.updateUsersIconByID(uID , final_path);
                    if(!iconID.equals("default.jpg")){
                        deleteFile(new File( baseURL + "img" + File.separator + "userIcon" + File.separator + iconID));
                    }
                }catch (Exception e){
                    return ResultFactory.buildFailResult("Unexpected Error");
                }
                return ResultFactory.buildSuccessResult("Upload Icon Success");
            }
        }
        return ResultFactory.buildFailResult("Upload Icon Fail");
    }

    @Override
    public Boolean deleteFile(File file) {
        if (!file.exists()){
            return false;
        }
        if (file.isDirectory()){
            File[] files = file.listFiles();
            for (File f: files){
                deleteFile(f);
            }
        }
        return file.delete();
    }

    @Override
    public  Result updateUsersInfo(String nickName,Integer gender, Integer uID , Integer prefer){
        if (uID == null){
            return ResultFactory.buildFailResult("Please Login First");
        }
        try {
            informationMapper.updateUserInforByID(uID , nickName ,gender , prefer);
        }catch (Exception e){
            return ResultFactory.buildFailResult("This name has existed already");
        }
        return ResultFactory.buildSuccessResult("Edit Success");
    }

    @Override
    public  Result getUserInfobyaccount(String account){
        LolUserInfo lolUserInfo;
        try {
            lolUserInfo = informationMapper.getUserByaccount(account);
            System.out.println(lolUserInfo.getNickName());
        }catch (Exception e){
            return ResultFactory.buildFailResult(e.toString());
        }
        return ResultFactory.buildSuccessResult(lolUserInfo);
    }

    @Override
    public Result getUsersFollow(Integer uID){
        if(uID == null){
            return ResultFactory.buildFailResult("请先登录");
        }
        List<LolUserInfo> followers = informationMapper.selectUsersFollowByUid(uID);

        return ResultFactory.buildSuccessResult(followers);
    }

    @Override
    public Result subscribeFollower(Integer uID , Integer oID){
        if (uID == null){
            return ResultFactory.buildFailResult("Please Login First");
        }
        if(oID.equals(uID)){
            return ResultFactory.buildFailResult("You can not subscribed yourself");
        }
        try {
            informationMapper.insertUsersFollowByID(uID , oID);
        }catch (Exception e){
            return ResultFactory.buildFailResult("You have subscribed him already");
        }
        return ResultFactory.buildSuccessResult("Subscribe Success");
    }
}
