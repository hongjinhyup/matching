package com.example.mongo_pjt.domain.entity;

import com.example.mongo_pjt.domain.dto.SurveyDto;
import com.example.mongo_pjt.domain.dto.SurveyOnlyDto;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Builder @Getter
@Document(collection = "survey")
public class SurveyEntity {

    @Id
    private String id;
    private String name;
    private String email;  // email 정보를 jwt spring server에서 보내줘야하는데...
    private String category;
    private String gender;
    private Integer age;
    private Integer career;
    private List region;
    private Integer status;

    /*--------------------------*/
    private String gosuEmail;
    private String gosuName;
    private Integer gosuAge;
    private String gosuGender;
    private String gosuCategory;
    private String gosuRegion;

    public SurveyDto toDto() {

        SurveyDto a = SurveyDto.builder()
                .id(id)
                .name(name)
                .email(email)
                .category(category)
                .gender(gender)
                .age(age)
                .career(career)
                .region(region)
                .gosuEmail(gosuEmail)
                .gosuName(gosuName)
                .gosuAge(gosuAge)
                .gosuGender(gosuGender)
                .gosuCategory(gosuCategory)
                .gosuRegion(gosuRegion)
                .status(status)
                .build();
        return a;
    }

    public SurveyOnlyDto toUserSurveyOnly() {
        SurveyOnlyDto originalSurveyInfo = SurveyOnlyDto.builder()
                .id(id)
                .name(name)
                .email(email)
                .category(category)
                .gender(gender)
                .age(age)
                .career(career)
                .region(region)
                .status(status)
                .build();
        return originalSurveyInfo;
    }
}
