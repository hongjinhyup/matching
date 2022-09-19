package com.example.mongo_pjt.controller;

import com.example.mongo_pjt.domain.dto.IdListDto;
import com.example.mongo_pjt.domain.dto.QuotationDto;
import com.example.mongo_pjt.domain.dto.UserDto;
import com.example.mongo_pjt.domain.entity.QuotationEntity;
import com.example.mongo_pjt.repo.QuotationRepo;
import com.example.mongo_pjt.service.QuotationServiceImpl;
import com.example.mongo_pjt.service.SurveyServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class QuotationController {

    private final QuotationServiceImpl quotationService;

    private final QuotationRepo quotationRepo;

    private final SurveyServiceImpl surveyService;

    // 견적서 저장
    @PostMapping("/quotationSubmit/{id}")
    public ResponseEntity saveQuotation(@RequestBody QuotationDto quotationDto, @PathVariable String id) {
        QuotationEntity quotationEntity = quotationService.savingQuo(quotationDto, id);
        return ResponseEntity.ok().body(quotationEntity);
    }

    // 사용자가 전문가로부터 온 견적서 확인
    @PostMapping("/matchedgosulist")
    public ResponseEntity matchedgosulist(@RequestBody String emailFrom) {
        return ResponseEntity.ok().body(quotationService.showingAllQuo(emailFrom));
    }

    @PostMapping("/matchedgosulist/status/{status}")
    public ResponseEntity matchedgosulist(@PathVariable Integer status, @RequestBody IdListDto idListDto) {
        List result = quotationService.showingQuoAccordingToStatus(status, idListDto);
        return ResponseEntity.ok().body(result);
    }

    @PutMapping("/matchedstart")
    public void matchedstart(@RequestBody QuotationDto users){
        quotationService.statusStart(users);
        surveyService.statusStart(users);
    }
    @PutMapping("/matchedfinish")
    public void matchedfinish(@RequestBody QuotationDto users){
        quotationService.statusFinish(users);
        surveyService.statusFinish(users);
    }

    @PostMapping("matchedgosulist/{id}")
    // 고수 견적서 세부사항 확인
    public ResponseEntity quotationDetail(@PathVariable String id) {
        return ResponseEntity.ok().body(quotationService.showingQuotationDetail(id));
    }

    @DeleteMapping("/quotation/delete")
    public ResponseEntity quotationDelete() {
        quotationRepo.deleteAll();
        Map<String, String> map = new HashMap<>();
        map.put("deleteAll", "deleted All Quotations");
        return ResponseEntity.ok().body(map);
    }
}
