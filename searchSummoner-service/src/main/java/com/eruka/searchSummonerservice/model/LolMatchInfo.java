package com.eruka.searchSummonerservice.model;


import lombok.Data;

import java.io.Serializable;

@Data
public class LolMatchInfo implements Serializable {
    /**
     * 比赛id
     */
    public String matchID;

    /**
     * 比赛使用者
     */
    public String matchChampion;

    /**
     * 比赛击杀
     */
    public String matchKill;

    /**
     * 比赛助攻
     */
    public String matchAssist;

    /**
     * 比赛死亡
     */
    public String matchDeath;

    /**
     * 比赛胜负
     */
    public String matchVD;
}

