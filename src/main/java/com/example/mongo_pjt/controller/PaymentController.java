package com.example.mongo_pjt.controller;

import com.example.mongo_pjt.domain.dto.PaymentDto;
import com.example.mongo_pjt.repo.PaymentRepo;
import com.example.mongo_pjt.service.PaymentServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/paymenthistory")
public class PaymentController {
    private final PaymentServiceImpl paymentService;

    @PostMapping("/")
    public ResponseEntity savePaymentHistory(@RequestBody PaymentDto paymentDto) {
        paymentService.savingPaymentHistory(paymentDto);
        Map<String,String> status = null;
        status.put("payment status","저장 완료");
        return ResponseEntity.ok().body(status);
    }

    @GetMapping("/expert/{name}")
    public ResponseEntity showExpertPaymentHistory(@PathVariable String expertName) {
        PaymentDto paymentDto = paymentService.showingExpertPaymentHistory(expertName);
        return ResponseEntity.ok().body(paymentDto);
    }

    @GetMapping("/user/{name}")
    public ResponseEntity showUserPaymentHistory(@PathVariable String userName) {
        PaymentDto paymentDto = paymentService.showingUserPaymentHistory(userName);
        return ResponseEntity.ok().body(paymentDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity changePaymentStatus(@PathVariable String id) {
        paymentService.changingPaymentStatus(id);
        Map<String,String> status = null;
        status.put("payment status","수정 완료");
        return ResponseEntity.ok().body(status);
    }
}
