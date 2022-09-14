package com.example.mongo_pjt.service;

import com.example.mongo_pjt.domain.dto.PaymentDto;

public interface PaymentService {

    // 프론트 : 결제 성공 -> 사용자, 전문가 이름(닉네임), 결제 가격, 결제 시간 바디에 실어서 서버로 전달 -> 내역 저장
    void savingPaymentHistory(PaymentDto paymentDto);

    // 전문가 입장에서 unique한 name 값으로 조회 -> 해당하는 내역 모두 조회
    PaymentDto showingExpertPaymentHistory(String expertName);

    // 사용자 입장에서 unique한 name 값으로 조회 -> 본인이 결제한 내역 모두 조회
    PaymentDto showingUserPaymentHistory(String userName);

    // 프론트로 결제 내역들이 모두 전달됐다는 가정 -> 프론트는 최종 환불된 내역의 id 정보를 서버로 전달 -> 서버는 paymentStatus 를 0 으로 수정 후 저장
    void changingPaymentStatus(String id);

}
