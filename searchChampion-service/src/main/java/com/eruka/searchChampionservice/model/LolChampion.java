package com.eruka.searchChampionservice.model;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class LolChampion implements Serializable {
    /**
     * 英雄id
     */
    private Integer cID;

    /**
     * 英雄名
     */
    private String name;

    /**
     * 英雄中文名
     */
    private String alias;

    /**
     * 英雄图片
     */
    private String fullImage;

    /**
     * 英雄类型 Assassin、Fighter、Mage、Marksman、Support、Tank
     */
    private String tags;

}
