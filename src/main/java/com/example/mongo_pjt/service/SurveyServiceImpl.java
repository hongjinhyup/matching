package com.example.mongo_pjt.service;

import com.example.mongo_pjt.domain.dto.*;
import com.example.mongo_pjt.domain.entity.SurveyEntity;
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
    
    List surveyInfoList = new ArrayList();
    Map<String, Integer> map = new HashMap<>();
    private final DataPreproccessing dataPreproccessing;

    @Override
    public List<SurveyOnlyDto> showingAllSurveys() {
        List<SurveyOnlyDto> surveyOnlyDtoList = new ArrayList<>();
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
            log.info("not saved : " + duplicateSurvey);

            return SurveyEntity.builder().build();

        } else {
            // 거래 진행 전 -> 0, 중 -> 1, 후 -> 2
            surveyDto.setStatus(0);
            SurveyEntity surveyEntity = surveyRepo.save(surveyDto.toEntity());
            System.out.println("result : " + surveyEntity);
            return surveyEntity;
        }
    }

    @Override
    public void changingStatus(String id) {
        SurveyDto surveyDto = surveyRepo.findById(id).orElseThrow().toDto();
        surveyDto.setStatus(1);
    }

    @Override
    public List<SurveyOnlyDto> showingSurveysAccordingToStatus(Integer status, IdListDto idListDto) {

        List surveyInfoAccordingToStatus = new ArrayList();

        try {

            log.info("from controlelr : "  + status + " and list : " + idListDto.getId());

            List ids = idListDto.getId();


            for (int i=0; i< ids.size(); i++) {
                String id = ids.get(i).toString();
                SurveyOnlyDto surveyOnlyDto = surveyRepo.findAllByIdAndStatus(id, status).toEntity().toUserSurveyOnly();

                surveyInfoAccordingToStatus.add(surveyOnlyDto);
            }

//            return surveyInfoAccordingToStatus;

        } catch (NullPointerException npe) {
            Map<String,Integer> map = new HashMap<>();
            map.put("ListNull",101);
            surveyInfoAccordingToStatus.add(map);
            return surveyInfoAccordingToStatus;
        }

        return surveyInfoAccordingToStatus;

    }

    @Override
    public List<SurveyOnlyDto> showingProperUsersForExpert(UserDto expertInfo) {
        List<SurveyOnlyDto> surveyOnlyDtoList = new ArrayList<>();
        String expertAddress = expertInfo.getAddress();

        List expertAddrsList = dataPreproccessing.splitString(expertAddress);
        Integer expertAge = expertInfo.getAge();
        String expertGender = expertInfo.getGender();

        List<SurveyEntity> surveyList = null;

        for (int i=0; i<expertAddrsList.size(); i++) {
//            String ex = expertAddrsList.get(i).toString();
            surveyList = surveyRepo.findSurveyEntitiesByRegionAndAgeAndGender(expertAddrsList.get(i).toString(), expertAge, expertGender);
        }

        if (surveyList.size() != 0) {
            for (int i =0; i<surveyList.size(); i++) {
                SurveyOnlyDto newOne = surveyList.get(i).toUserSurveyOnly();
                surveyOnlyDtoList.add(newOne);
            }

            return surveyOnlyDtoList;
        }
        else {
            map.put("ListNull", 101); // list가 null 일 경우 해당 JSON 타입 리턴
            surveyInfoList.add(map);
            return surveyInfoList;
         }

    }
    /*------------------------------------------------------------*/

    @Override
    public SurveyDto showingSurveyDetail(String id, SurveyDto surveyDto) {

        SurveyDto specificSurvey = surveyRepo.findById(id).orElseThrow().toDto();

        specificSurvey.setGosuName(surveyDto.getGosuName());
        specificSurvey.setGosuAge(surveyDto.getGosuAge());
        specificSurvey.setGosuGender(surveyDto.getGosuGender());
        specificSurvey.setGosuCategory(surveyDto.getGosuCategory());
        specificSurvey.setGosuRegion(surveyDto.getGosuRegion());
        specificSurvey.setGosuCareer(surveyDto.getGosuCareer());
        return specificSurvey;  // 최종 surveyDto + 고수 회원정보
    }

    @Override
    public void statusStart(QuotationDto quotationDto) {
        try{

            String surveyId = quotationDto.getSurveyId();

            SurveyEntity target = surveyRepo.findById(surveyId).orElseThrow();

            SurveyDto result = target.toDto();

            result.setStatus(1);

            surveyRepo.save(result.toEntity());
        } catch (Exception e){
            throw new RuntimeException("No Survey");
        }
    }

    @Override
    public void statusFinish(QuotationDto quotationDto) {
        try{
            String userEmail = quotationDto.getUserEmail();
            String gosuEmail = quotationDto.getGosuEmail();

//            SurveyDto target = surveyRepo.findByEmailAndGosuEmail(userEmail, gosuEmail);
//
//            target.setStatus(2);
//
//            surveyRepo.save(target.toEntity());

        } catch (Exception e){
            throw new RuntimeException("No Survey");
        }
    }
}
