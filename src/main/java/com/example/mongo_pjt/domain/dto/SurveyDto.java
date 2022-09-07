package com.example.mongo_pjt.domain.dto;

import com.example.mongo_pjt.domain.entity.SurveyEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

import java.util.List;

@Log4j2
@Getter@Setter
@Builder
public class SurveyDto {

    private String id;
    private String name;
    private String email;
    private String category;
    private String gender;
    private Integer age;
    private Integer career;
    private List region;

/*--------------------------------*/
    private String gosuName;
    private Integer gosuAge;
    private String gosuGender;
    private String gosuCategory;
    private String gosuRegion;
    private Integer gosuCareer;

    public SurveyEntity toEntity() {
        SurveyEntity a = SurveyEntity.builder()
                .id(id)
                .name(name)
                .email(email)
                .category(category)
                .gender(gender)
                .age(age)
                .career(career)
                .region(region)
                .gosuName(gosuName)
                .gosuAge(gosuAge)
                .gosuGender(gosuGender)
                .gosuCategory(gosuCategory)
                .gosuRegion(gosuRegion)
                .build();
        log.info("email 정보 " + email);
        return a;
    }
}
