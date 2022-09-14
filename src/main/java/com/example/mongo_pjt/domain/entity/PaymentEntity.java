package com.example.mongo_pjt.domain.entity;

import com.example.mongo_pjt.domain.dto.PaymentDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import nonapi.io.github.classgraph.json.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document("paymentHistory")
@Getter @Builder
public class PaymentEntity {

    @Id
    private String id;
    private String userName;
    private String expertName;
    private Integer price;
    private Integer paymentStatus; // 0 -> 결제 취소(환불) 상태   1 -> 결제 완료 상태
    private LocalDateTime localDateTime;

    public PaymentDto toDto() {
        PaymentDto result = PaymentDto.builder()
                .id(id)
                .userName(userName)
                .expertName(expertName)
                .price(price)
                .localDateTime(localDateTime)
                .paymentStatus(paymentStatus)
                .build();
        return result;
    }
}
