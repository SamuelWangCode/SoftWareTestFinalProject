package com.eruka.searchSummonerservice.model;

import lombok.Data;


import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
public class LolSummonerInfo implements Serializable {
    /**
     * 召唤使名字
     */
    public String summonerName;

    /**
     * 召唤使等级
     */
    public String summonerLevel;

    /**
     * 召唤使头像
     */
    public String summonerIcon;

    /**
     * 召唤使段位
     */
    public String summonerRank;

    /**
     * 召唤使擅长英雄
     */
    public String summonerBestC;

    /**
     * 召唤师比赛记录
     */
    public LolMatchInfo[] summonerMatch = new LolMatchInfo[5];

    /**
     * 获取比赛
     */
    public LolMatchInfo getLolMatchInfo(int index){
        return summonerMatch[index];
    }
}

