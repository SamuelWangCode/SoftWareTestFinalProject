package com.eruka.guideservice.model;


import lombok.Data;

import java.io.Serializable;

@Data
public class Item implements Serializable {
    /**
     * 装备id
     */
    private Integer iID;

    /**
     * 装备名
     */
    private String itemName;

    /**
     * 装备图片
     */
    private String itemImg;


}
