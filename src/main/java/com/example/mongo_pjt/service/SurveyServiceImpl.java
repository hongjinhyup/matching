package com.example.mongo_pjt.service;

import com.example.mongo_pjt.domain.dto.SurveyDto;
import com.example.mongo_pjt.domain.dto.UserDto;
import com.example.mongo_pjt.domain.entity.SurveyEntity;
import com.example.mongo_pjt.domain.dto.SurveyOnlyDto;
import com.example.mongo_pjt.repo.SurveyRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Log4j2
@Transactional
@Service
@RequiredArgsConstructor
public class SurveyServiceImpl implements SurveyService {
    private final SurveyRepo surveyRepo;
    List<SurveyOnlyDto> surveyOnlyDtoList = new ArrayList<>();
    List surveyInfoList = new ArrayList();
    Map<String, Integer> map = new HashMap<>();

    @Override
    public List<SurveyOnlyDto> showingAllSurveys() {
        List<SurveyEntity> surveyAll = surveyRepo.findAll();
        surveyOnlyDtoList = new ArrayList<>();
        for (int i=0; i<surveyAll.size(); i++) {

            log.info(i + " 번째 : " + surveyAll.get(i).getCategory());
            surveyOnlyDtoList.add(surveyAll.get(i).toUserSurveyOnly());
        }
        log.info("alter dto : " + surveyOnlyDtoList);
        return surveyOnlyDtoList;
    }

    @Override
    public SurveyEntity savingSurvey(SurveyDto surveyDto) {
        String email = surveyDto.getEmail();
        String category = surveyDto.getCategory();

        if (surveyRepo.findByEmailAndCategory(email, category) != null) {
            log.info("이미 등록된 의뢰서입니다!");
            Map<String,String> duplicateSurvey = new HashMap<>();
            duplicateSurvey.put("survey status", "이미 등록된 의뢰서입니다!");
            log.info("not save : " + duplicateSurvey);

            return SurveyEntity.builder().build();

        } else {
            SurveyEntity surveyEntity = surveyRepo.save(surveyDto.toEntity());
            return surveyEntity;
        }
    }


    // String region, Integer age, String gender
    @Override
    public List<SurveyOnlyDto> showingProperUsersForExpert(UserDto expertInfo) {
        String expertAddress = expertInfo.getAddress();
        Integer expertAge = expertInfo.getAge();
        String expertGender = expertInfo.getGender();

        List<SurveyEntity> surveyList = surveyRepo.findSurveyEntitiesByRegionAndAgeAndGender(expertAddress, expertAge, expertGender);

        if (surveyList.size() != 0) {
            for (int i =0; i<surveyList.size(); i++) {
                SurveyOnlyDto newOne = surveyList.get(i).toUserSurveyOnly();
                surveyOnlyDtoList.add(newOne);
            }
            return surveyOnlyDtoList;
        }
        else {
            map.put("ListNull", 101);
            surveyInfoList.add(map);
            return surveyInfoList;
        }

    }
    /*------------------------------------------------------------*/

    @Override
    public SurveyDto showingSurveyDetail(String id, SurveyDto surveyDto) {

        String gosuname = surveyDto.getGosuName();
        Integer gosuage = surveyDto.getGosuAge();
        String gosugender = surveyDto.getGosuGender();
        String gosucategory = surveyDto.getGosuCategory();
        String gosuregion = surveyDto.getGosuRegion();
        Integer gosucareer = surveyDto.getGosuCareer();

        SurveyEntity specificSurvey = surveyRepo.findById(id).orElseThrow();

        SurveyDto surveyOne = specificSurvey.toDto();
        surveyOne.setGosuName(gosuname);
        surveyOne.setGosuAge(gosuage);
        surveyOne.setGosuGender(gosugender);
        surveyOne.setGosuCategory(gosucategory);
        surveyOne.setGosuRegion(gosuregion);
        surveyOne.setGosuCareer(gosucareer);
        return surveyOne;  // 최종 surveyDto + 고수 회원정보
    }
}
