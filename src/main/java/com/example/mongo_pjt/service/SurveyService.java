package com.example.mongo_pjt.service;

import com.example.mongo_pjt.domain.dto.*;
import com.example.mongo_pjt.domain.entity.SurveyEntity;

import java.util.List;

public interface SurveyService {
    SurveyEntity savingSurvey(SurveyDto surveyDto);
    List<SurveyOnlyDto> showingAllSurveys();
    SurveyDto showingSurveyDetail(String id,  SurveyDto surveyDto);
    List<SurveyOnlyDto> showingProperUsersForExpert(UserDto experInfo);
    void changingStatus(String id);
    List<SurveyDto> showingSurveysAccordingToStatus(Integer status, IdListDto idListDto);
    void statusStart(QuotationDto quotationDto);
    void statusFinish(String surveyId);
}
