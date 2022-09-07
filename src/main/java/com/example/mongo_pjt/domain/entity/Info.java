package com.example.mongo_pjt.domain.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
public class Info {

    private Long _idx;
    private String name;
    private Double count1;
    private Long count2;
    private Long count3;
    private Long count4;
    private String image;
}
