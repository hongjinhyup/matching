package com.example.mongo_pjt.domain.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data @Builder
public class SurveyOnlyDto {

    @ApiModelProperty(example = "surveyOnly id")
    private String id;
    @ApiModelProperty(example = "고객의 이름")
    private String name;
    @ApiModelProperty(example = "고객의 이메일")
    private String email;
    @ApiModelProperty(example = "고객이 작성 의뢰서 분야")
    private String category;
    @ApiModelProperty(example = "고객이 원하는 전문가의 성별")
    private String gender;
    @ApiModelProperty(example = "고객이 원하는 전문가의 나이")
    private Integer age;
    @ApiModelProperty(example = "고객이 원하는 전문가의 경력")
    private Integer career;
    @ApiModelProperty(example = "고객이 원하는 지역")
    private List region;
}
