package com.example.mongo_pjt.service;

import com.example.mongo_pjt.domain.dto.PaymentDto;
import com.example.mongo_pjt.repo.PaymentRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepo paymentRepo;

    @Override
    public void savingPaymentHistory(PaymentDto paymentDto) {
        paymentDto.setPaymentStatus(1);
        paymentRepo.save(paymentDto.toEntity());
    }

    @Override
    public PaymentDto showingExpertPaymentHistory(String expertName) {
        PaymentDto paymentDto = paymentRepo.findByExpertName(expertName);
        return paymentDto;
    }

    @Override
    public PaymentDto showingUserPaymentHistory(String userName) {
        PaymentDto paymentDto = paymentRepo.findByUserName(userName);
        return paymentDto;
    }

    @Override
    public void changingPaymentStatus(String id) {
        PaymentDto paymentDto = paymentRepo.findById(id).orElseThrow().toDto();
        paymentDto.setPaymentStatus(0);
    }
}
