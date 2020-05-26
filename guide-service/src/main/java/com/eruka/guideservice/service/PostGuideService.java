package com.eruka.guideservice.service;

import com.eruka.guideservice.model.Guide;
import com.eruka.guideservice.result.Result;

import java.util.List;

public interface PostGuideService {

    Result postGuide(Guide newGuide);
}
