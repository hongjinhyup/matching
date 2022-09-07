package com.example.mongo_pjt.controller;

import com.example.mongo_pjt.domain.dto.QuotationDto;
import com.example.mongo_pjt.domain.entity.QuotationEntity;
import com.example.mongo_pjt.repo.QuotationRepo;
import com.example.mongo_pjt.service.QuotationServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class QuotationController {

    private final QuotationServiceImpl quotationService;
    private final QuotationRepo quotationRepo;

    // 견적서 저장
    @PostMapping("/quotationSubmit/{id}")
    public ResponseEntity saveQuotation(@RequestBody QuotationDto quotationDto, @PathVariable String id) {
        QuotationEntity quotationEntity = quotationService.savingQuo(quotationDto, id);
        Map<String, String> map = new HashMap<>();
//        map.put("quotation save", "completed");
        return ResponseEntity.ok().body(quotationEntity);
    }

    // 수고리스트 조회
    @PostMapping("/matchedgosulist")
    public ResponseEntity matchedgosulist(@RequestBody String emailfrom) {

        return ResponseEntity.ok().body(quotationService.showingAllQuo(emailfrom));
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
