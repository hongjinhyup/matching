package com.example.mongo_pjt.domain.dto;

import com.example.mongo_pjt.domain.entity.QuotationEntity;
import lombok.*;

@Getter @Setter
@RequiredArgsConstructor
public class QuotationOnlyDto {
    private String id;
    private String gosuName;
    private Integer gosuAge;
    private String gosuGender;
    private String gosuCategory;
    private Integer gosuCareer;
    private String gosuRegion;

    public QuotationEntity toEntity() {
        QuotationEntity building = QuotationEntity.builder()
                .id(id)
                .gosuName(gosuName)
                .gosuAge(gosuAge)
                .gosuGender(gosuGender)
                .gosuCategory(gosuCategory)
                .gosuCareer(gosuCareer)
                .gosuRegion(gosuRegion)
                .build();
        return building;
    }
}
