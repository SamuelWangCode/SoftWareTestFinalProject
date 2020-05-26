package com.eruka.guideservice.model;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class LolUserInfo implements Serializable{
    /**
     * 用户唯一ID
     */
    @NotNull(message = "唯一ID不可为空")
    private Integer uID;

    /**
     * 用户昵称
     */
    @NotBlank(message = "用户名不可为空")
    private String nickName;

    /**
     * 用户头像url
     */
    private String icon;

    /**
     * 用户性别
     */
    private Integer gender;

    /**
     * 用户绑定账号
     */
    private String lolAccount;

    private Integer prefer;

}
