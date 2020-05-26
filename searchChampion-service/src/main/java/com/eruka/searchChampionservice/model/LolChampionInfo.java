package com.eruka.searchChampionservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
@EqualsAndHashCode(callSuper = true)
@Data
public class LolChampionInfo extends  LolChampion{

    /**
     * 英雄介绍
     */
    private String lore;

    /**
     * 英雄技能1 ID
     */
    private String skillOneID;

    /**
     * 英雄技能1 名字
     */
    private String skillOneName;

    /**
     * 英雄技能1 描述
     */
    private String skillOneDes;

    /**
     * 英雄技能1 图片
     */
    private String skillOneImg;

    /**
     * 英雄技能2 ID
     */
    private String skillTwoID;

    /**
     * 英雄技能2 名字
     */
    private String skillTwoName;

    /**
     * 英雄技能2 描述
     */
    private String skillTwoDes;

    /**
     * 英雄技能2 图片
     */
    private String skillTwoImg;

    /**
     * 英雄技能3 ID
     */
    private String skillThrID;

    /**
     * 英雄技能3 名字
     */
    private String skillThrName;

    /**
     * 英雄技能3 描述
     */
    private String skillThrDes;

    /**
     * 英雄技能3 图片
     */
    private String skillThrImg;

    /**
     * 英雄技能4 ID
     */
    private String skillFouID;

    /**
     * 英雄技能4 名字
     */
    private String skillFouName;

    /**
     * 英雄技能4 描述
     */
    private String skillFouDes;

    /**
     * 英雄技能4 图片
     */
    private String skillFouImg;

    /**
         * 英雄被动 名字
     */
    private String passiveName;

    /**
     * 英雄被动 描述
     */
    private String passiveDes;

    /**
     * 英雄被动 图片
     */
    private String passiveImg;

}
