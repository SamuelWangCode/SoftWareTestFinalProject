package com.eruka.searchSummonerservice.controller;


import com.eruka.searchSummonerservice.model.LolMatchInfo;
import com.eruka.searchSummonerservice.model.LolSummonerInfo;
import com.eruka.searchSummonerservice.result.Result;
import com.eruka.searchSummonerservice.result.ResultFactory;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;
import java.util.Map;

@Slf4j
@RestController
@Api("SearchSummoner-Controller")
@RequestMapping("/api")
public class SearchController {



    public String findChampion(int myheroid) throws IOException {
        String answer = "null";
        URL url = new URL("http://ddragon.leagueoflegends.com/cdn/9.23.1/data/en_US/champion.json");
        URLConnection request = url.openConnection();
        request.connect();
        JsonParser jp = new JsonParser();
        JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
        JsonObject rootobj = root.getAsJsonObject();
        JsonObject heroinfor = rootobj.get("data").getAsJsonObject();
        Iterator<Map.Entry<String,JsonElement>> rtn =  heroinfor.entrySet().iterator();
        while(rtn.hasNext()) {
            Map.Entry<String, JsonElement> unit = rtn.next();
            if (unit.getValue().getAsJsonObject().get("key").getAsInt() == myheroid) {
                answer = unit.getValue().getAsJsonObject().get("name").getAsString();
                break;
            }
        }
        ((HttpURLConnection) request).disconnect();
        return answer;
    }


    public  LolMatchInfo[] getMatch(JsonArray matchlist, String apikey) throws IOException {
        LolMatchInfo[] lolMatchInfos = new LolMatchInfo[5];
        for(int i=0;i<5&&i<matchlist.size();i++)
        {
            LolMatchInfo lolMatchInfo = new LolMatchInfo();
            String matchid=matchlist.get(i).getAsJsonObject().get("gameId").getAsString();

            int x = matchlist.get(i).getAsJsonObject().get("champion").getAsInt();
            lolMatchInfo.matchChampion = findChampion(x);
            URL url = new URL("https://na1.api.riotgames.com/lol/match/v4/matches/"+matchid+"?api_key="+apikey);
            URLConnection request = url.openConnection();
            HttpURLConnection httpUrlConnection = (HttpURLConnection) request;
            request.connect();
            JsonParser jp = new JsonParser();
            JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
            JsonObject rootobj = root.getAsJsonObject();
            JsonArray matchdetail =rootobj.get("participants").getAsJsonArray();

            for(int j=0;j<matchdetail.size();j++)
            {
                if(matchdetail.get(j).getAsJsonObject().get("championId").getAsInt()== x)
                {
                    lolMatchInfo.matchDeath = matchdetail.get(j).getAsJsonObject().get("stats").getAsJsonObject().get("deaths").getAsString();
                    lolMatchInfo.matchKill= matchdetail.get(j).getAsJsonObject().get("stats").getAsJsonObject().get("kills").getAsString();
                    lolMatchInfo.matchAssist = matchdetail.get(j).getAsJsonObject().get("stats").getAsJsonObject().get("assists").getAsString();
                    if(matchdetail.get(j).getAsJsonObject().get("stats").getAsJsonObject().get("win").getAsBoolean())
                    {
                        lolMatchInfo.matchVD = "WIN";
                    }
                    else
                    {
                        lolMatchInfo.matchVD = "LOSE";
                    }
                    break;
                }
            }
            lolMatchInfos[i] = lolMatchInfo;
        }
        return  lolMatchInfos;
    }

    @ApiImplicitParam(name = "vID" , value = "召唤师名字" ,dataType = "String" , required = true)
    @ApiOperation(value = "查找战绩",notes = "查找战绩API")
    @GetMapping("/search/{vID}")
    @Cacheable(value = "Result")
    public Result getSummonerInfo(@PathVariable String vID) throws IOException {
        String apikey = "RGAPI-3847276e-2d99-46e8-89b1-b468b0a42563";
        log.info("start");
        String result = java.net.URLEncoder.encode(vID, "UTF-8");

        String ssURL="https://na1.api.riotgames.com/lol/summoner/v4/summoners/by-name/"+vID+"?api_key="+apikey;
        //找人
        URL url = new URL(ssURL);
        URLConnection request = url.openConnection();
        HttpURLConnection httpUrlConnection = (HttpURLConnection) request;
        request.connect();
        int code = httpUrlConnection.getResponseCode();
        if(code == 200){
            JsonParser jp = new JsonParser();
            JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
            JsonObject rootobj = root.getAsJsonObject();
            LolSummonerInfo lolSummonerInfo = new LolSummonerInfo();
            lolSummonerInfo.summonerIcon = "3795";
            lolSummonerInfo.summonerName = vID;
            lolSummonerInfo.summonerLevel = rootobj.get("summonerLevel").getAsString();
            lolSummonerInfo.summonerIcon = rootobj.get("profileIconId").getAsString();
            int temp = Integer.parseInt(lolSummonerInfo.summonerIcon);
            if(temp < 3776 || temp > 4380){
                if(temp < 1200){
                    lolSummonerInfo.summonerIcon = "3801";
                }else if(temp < 2400){
                    lolSummonerInfo.summonerIcon = "4093";
                }else if(temp < 3600){
                    lolSummonerInfo.summonerIcon = "4214";
                }else{
                    lolSummonerInfo.summonerIcon = "4362";
                }
            }
            log.info(lolSummonerInfo.summonerIcon);
            String myid = rootobj.get("id").getAsString();
            String myaid = rootobj.get("accountId").getAsString();
            ((HttpURLConnection) request).disconnect();

            ssURL = "https://na1.api.riotgames.com/lol/league/v4/entries/by-summoner/"+myid+"?api_key="+apikey;
            //找段位
            url = new URL(ssURL);
            request = url.openConnection();
            httpUrlConnection = (HttpURLConnection) request;
            request.connect();
            code = httpUrlConnection.getResponseCode();
            if(code == 200) {
                jp = new JsonParser();
                root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
                JsonArray rootarr = root.getAsJsonArray();
                if(rootarr.size()==0)
                {
                    lolSummonerInfo.summonerRank = "null";
                }
                else
                {
                    String league = rootarr.get(0).getAsJsonObject().get("tier").getAsString();
                    String rank = rootarr.get(0).getAsJsonObject().get("rank").getAsString();
                    lolSummonerInfo.summonerRank = league + " " + rank;
                }
            } else{
                return ResultFactory.buildFailResult("发生错误了");
            }
            ((HttpURLConnection) request).disconnect();

            //获取常用英雄
            ssURL = "https://na1.api.riotgames.com/lol/champion-mastery/v4/champion-masteries/by-summoner/"+myid+"?api_key="+apikey;
            url = new URL(ssURL);
            request = url.openConnection();
            httpUrlConnection = (HttpURLConnection) request;
            request.connect();
            code = httpUrlConnection.getResponseCode();
            if(code == 200){
                jp = new JsonParser();
                root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
                JsonArray rootarr = root.getAsJsonArray();
                if(rootarr.size() == 0){
                    lolSummonerInfo.summonerBestC = "null";
                }else{
                    int heroid = rootarr.get(0).getAsJsonObject().get("championId").getAsInt();
                    lolSummonerInfo.summonerBestC = findChampion(heroid);
                }
            }else{
                return ResultFactory.buildFailResult("发生错误了");
            }
            ((HttpURLConnection) request).disconnect();

            //最近比赛
            ssURL ="https://na1.api.riotgames.com/lol/match/v4/matchlists/by-account/"+myaid+"?api_key="+apikey;
            url = new URL(ssURL);
            request = url.openConnection();
            httpUrlConnection = (HttpURLConnection) request;
            request.connect();
            code = httpUrlConnection.getResponseCode();
            if(code == 200){
                jp = new JsonParser();
                root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
                rootobj = root.getAsJsonObject();
                JsonArray matchlist = rootobj.get("matches").getAsJsonArray();
                lolSummonerInfo.summonerMatch = getMatch( matchlist , apikey );
                log.info("success");
            }else{
                return ResultFactory.buildFailResult("发生错误了");
            }
            return ResultFactory.buildSuccessResult(lolSummonerInfo);
        }



        return ResultFactory.buildFailResult("发生错误了");
    }
}
