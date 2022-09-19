package com.example.mongo_pjt.service;

import com.example.mongo_pjt.domain.dto.IdListDto;
import com.example.mongo_pjt.domain.dto.QuotationDto;
import com.example.mongo_pjt.domain.dto.UserDto;
import com.example.mongo_pjt.domain.entity.QuotationEntity;

import java.util.List;

public interface QuotationService {

    // 요청서 id 로 요청서를 작성한 사용자의 이메일 확인 -> 견적서 데이터에 포함
    QuotationEntity savingQuo(QuotationDto quotationDto, String id);
    List<QuotationEntity> showingAllQuo(String id);  // 고수리스트
    QuotationDto showingQuotationDetail(String id);
    List<QuotationDto> showingQuoAccordingToStatus(Integer status, IdListDto idListDto);
    void statusStart(QuotationDto quotationDto);
    void statusFinish(QuotationDto quotationDto);
}
