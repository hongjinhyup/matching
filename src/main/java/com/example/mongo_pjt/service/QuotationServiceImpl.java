package com.example.mongo_pjt.service;

import com.example.mongo_pjt.domain.dto.QuotationDto;
import com.example.mongo_pjt.domain.dto.QuotationOnlyDto;
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
        quotationDto.setStatus(1);
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
                lists.add(quotationOnlyDto);
            }

            return lists;

        } else {
            Map<String,Integer> map = new HashMap<>();
            map.put("ListNull", 101); // list가 null 일 경우 해당 JSON 타입 리턴
            lists.add(map);
        }

        return lists;
    }

    // 고수 특정 견적서 확인
    @Override
    public QuotationDto showingQuotationDetail(String id) {
        QuotationEntity quotationList = quotationRepo.findById(id).orElseThrow();
        QuotationDto quotationDto = quotationList.toDto();
        return quotationDto;
    }
}
