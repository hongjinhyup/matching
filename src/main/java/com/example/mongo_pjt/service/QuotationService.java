package com.example.mongo_pjt.service;

import com.example.mongo_pjt.domain.dto.QuotationDto;
import com.example.mongo_pjt.domain.entity.QuotationEntity;
import com.example.mongo_pjt.domain.entity.SurveyEntity;

import java.util.List;

public interface QuotationService {

    QuotationEntity savingQuo(QuotationDto quotationDto, String id);
    List<QuotationEntity> showingAllQuo(String id);  // 고수리스트
    QuotationDto showingQuotationDetail(String id);
}
