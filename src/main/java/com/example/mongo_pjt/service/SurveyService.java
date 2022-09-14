package com.example.mongo_pjt.service;

import com.example.mongo_pjt.domain.dto.SurveyDto;
import com.example.mongo_pjt.domain.dto.SurveyIdListDto;
import com.example.mongo_pjt.domain.dto.UserDto;
import com.example.mongo_pjt.domain.entity.SurveyEntity;
import com.example.mongo_pjt.domain.dto.SurveyOnlyDto;
import org.json.simple.JSONArray;

import java.util.List;
import java.util.Map;

public interface SurveyService {
    SurveyEntity savingSurvey(SurveyDto surveyDto);
    List<SurveyOnlyDto> showingAllSurveys();
    SurveyDto showingSurveyDetail(String id,  SurveyDto surveyDto);
    List<SurveyOnlyDto> showingProperUsersForExpert(UserDto experInfo);
    void changingStatus(String id);
    List<SurveyOnlyDto> showingSurveysAccordingToStatus(Integer status, SurveyIdListDto surveyIdListDto);
}
