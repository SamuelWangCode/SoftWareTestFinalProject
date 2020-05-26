package com.eruka.guideservice.service;

import com.eruka.guideservice.model.Guide;
import com.eruka.guideservice.result.Result;

import java.util.List;

public interface GuideService {

    Result searchGuideByTitle(String title);
    Result searchGuideByUid(String uID);
    Result searchGuideByLikeNum();
    Result searchGuideBygID(Integer gID);

    Result getPreferGuideByuID(Result result);

    Result addlikeNumBygID(Integer gID);

    Result getItem();
}
