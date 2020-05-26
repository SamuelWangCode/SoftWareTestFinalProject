package com.eruka.guideservice.model;


import lombok.Data;

import java.io.Serializable;

@Data
public class Guide implements Serializable {
    /**
     * 帖子id
     */
    private Integer gID;

    /**
     * 帖子标题
     */

    private String title;

    /**
     * 帖子内容
     */
    private String context;

    /**
     * 作者id
     */
    private Integer uID;

    /**
     * 英雄类型 Assassin、Fighter、Mage、Marksman、Support、Tank
     */
    private Integer championTag;

    /**
     * 英雄名
     */
    private String championName;

    /**
     * 点赞数
     */
    private Integer likeNum;

    /**
     * 收藏数
     */
    private Integer collectNum;

    /**
     * 装备1
     */
    private String item1;

    /**
     * 装备2
     */
    private String item2;

    /**
     * 装备3
     */
    private String item3;

    /**
     * 装备4
     */
    private String item4;

    /**
     * 装备5
     */
    private String item5;

    /**
     * 装备6
     */
    private String item6;

    /**
     * 英雄图片6
     */
    private String championImg;
}
