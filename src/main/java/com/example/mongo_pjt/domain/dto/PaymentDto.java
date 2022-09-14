package com.example.mongo_pjt.domain.dto;

import com.example.mongo_pjt.domain.entity.PaymentEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter @Builder
public class PaymentDto {

    private String id;
    private String userName;
    private String expertName;
    private Integer price;
    private Integer paymentStatus;
    private LocalDateTime localDateTime;

    public PaymentEntity toEntity() {
        PaymentEntity result = PaymentEntity.builder()
                .id(id)
                .userName(userName)
                .expertName(expertName)
                .price(price)
                .paymentStatus(paymentStatus)
                .localDateTime(localDateTime)
                .build();
        return result;
    }
}
