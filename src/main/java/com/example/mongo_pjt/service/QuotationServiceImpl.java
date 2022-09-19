package com.example.mongo_pjt.service;

import com.example.mongo_pjt.domain.dto.IdListDto;
import com.example.mongo_pjt.domain.dto.QuotationDto;
import com.example.mongo_pjt.domain.dto.QuotationOnlyDto;
import com.example.mongo_pjt.domain.dto.UserDto;
import com.example.mongo_pjt.domain.entity.QuotationEntity;
import com.example.mongo_pjt.domain.entity.SurveyEntity;
import com.example.mongo_pjt.repo.QuotationRepo;
import com.example.mongo_pjt.repo.SurveyRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Log4j2
@Service
@Transactional
@RequiredArgsConstructor
public class QuotationServiceImpl implements QuotationService {

    private final QuotationRepo quotationRepo;

    private final SurveyRepo surveyRepo;
    @Override
    public QuotationEntity savingQuo(QuotationDto quotationDto, String id) {
        log.info("gosuEmail information from controller : " + quotationDto.getGosuEmail());
        SurveyEntity surveyInfo = surveyRepo.findById(id).orElseThrow();
        String userEmailInfo = surveyInfo.getEmail();
        quotationDto.setUserEmail(userEmailInfo);
        //만들때 진행 전 부터 시작
        quotationDto.setStatus(0);
        QuotationEntity quotationEntity = quotationRepo.save(quotationDto.toEntity());


        return quotationEntity;
    }

    @Override
    public List<QuotationEntity> showingAllQuo(String email) {
        List<QuotationEntity> quotationList = quotationRepo.findByUserEmail(email);
        List lists = new ArrayList<>();
        QuotationOnlyDto quotationOnlyDto = new QuotationOnlyDto();

        if (!quotationList.isEmpty()) {
            for (int i=0; i<quotationList.size(); i++) {
                quotationOnlyDto.setId(quotationList.get(i).getId());
                quotationOnlyDto.setGosuName(quotationList.get(i).getGosuName());
                quotationOnlyDto.setGosuAge(quotationList.get(i).getGosuAge());
                quotationOnlyDto.setGosuCareer(quotationList.get(i).getGosuCareer());
                quotationOnlyDto.setGosuGender(quotationList.get(i).getGosuGender());
                quotationOnlyDto.setGosuCategory(quotationList.get(i).getGosuCategory());
                quotationOnlyDto.setGosuRegion(quotationList.get(i).getGosuRegion());
                quotationOnlyDto.setStatus(quotationList.get(i).getStatus());
                lists.add(quotationOnlyDto);
            }

            return lists;

        } else {
            Map<String,Integer> map = new HashMap<>();
            map.put("ListNull", 101); // list가 null 일 경우 해당 JSON 타입 리턴
            lists.add(map);
        }
//
        return lists;
    }

    // 고수 특정 견적서 확인
    @Override
    public QuotationDto showingQuotationDetail(String id) {
        QuotationEntity quotationList = quotationRepo.findById(id).orElseThrow();
        QuotationDto quotationDto = quotationList.toDto();
        return quotationDto;
    }

    @Override
    public List showingQuoAccordingToStatus(Integer status, IdListDto idListDto) {
        log.info("status from ct : " + status + " / " + idListDto);
        Map<String,Integer> map = new HashMap<>();
        List quotationDtoList = new ArrayList<>();

        try {

            log.info("from controller : "  + status + " and list : " + idListDto.getId());

            List ids = idListDto.getId();

            for (int i=0; i< ids.size(); i++) {
                String id = ids.get(i).toString();
                QuotationDto quotationDto = quotationRepo.findAllByIdAndStatus(id, status);
                if (!quotationDto.equals("null")) {
                    quotationDtoList.add(quotationDto);
                }

            }

        } catch (NullPointerException e) {
            map.put("ListNull", 101);
            quotationDtoList.add(map);
            return quotationDtoList;
        }

        log.info("what's insdie the list?   " + quotationDtoList);

        return quotationDtoList;

    }

    @Override
    public void statusStart(QuotationDto quotationDto) {
        try{
            String userEmail = quotationDto.getUserEmail();
            String gosuEmail = quotationDto.getGosuEmail();

            QuotationDto target = quotationRepo.findByUserEmailAndGosuEmail(userEmail, gosuEmail);

            target.setStatus(1);

            quotationRepo.save(target.toEntity());

        } catch (Exception e){
            throw new RuntimeException("No Quotation");
        }
    }

    @Override
    public void statusFinish(QuotationDto quotationDto) {
        try{
            String userEmail = quotationDto.getUserEmail();
            String gosuEmail = quotationDto.getGosuEmail();

            QuotationDto target = quotationRepo.findByUserEmailAndGosuEmail(userEmail, gosuEmail);

            target.setStatus(2);

            quotationRepo.save(target.toEntity());

        } catch (Exception e){
            throw new RuntimeException("No Quotation");
        }
    }
}
