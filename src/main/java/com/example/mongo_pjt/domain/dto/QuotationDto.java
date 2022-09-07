package com.example.mongo_pjt.domain.dto;

import com.example.mongo_pjt.domain.entity.QuotationEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter@Setter
@Builder
public class QuotationDto {

    private String id;
    private String userEmail;  // survey id를 통해 userEmail 확인할거임
    private String gosuEmail;  // 프론트에서 넘겨받아야 함 (프로젝트 구조적 한계로 토큰에서 하는것이 오히려 비효율적)
    private String gosuName;  // gosuEmail을 통한 Spring server 에서 추가 할 것
    private Integer gosuAge;  // 위와 동일
    private String gosuGender;  // 위와 동일
    private String gosuCategory; // 위와 동일
    private Integer gosuCareer;
    private String gosuRegion; // 위와 동일
    private Long quotationPrice; // 프론트에서 받아올 것
    private String introduction;  // 프론트에서 받아올 것

    public QuotationEntity toEntity() {
        QuotationEntity quotationEntity = QuotationEntity.builder()
                .id(id)
                .userEmail(userEmail)
                .gosuEmail(gosuEmail)
                .gosuName(gosuName)
                .gosuAge(gosuAge)
                .gosuCareer(gosuCareer)
                .gosuGender(gosuGender)
                .gosuCategory(gosuCategory)
                .gosuRegion(gosuRegion)
                .quotationPrice(quotationPrice)
                .introduction(introduction)
                .build();
        return quotationEntity;
    }
}
