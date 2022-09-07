package com.example.mongo_pjt.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private String id;
    private String name;
    private Integer age;
    private Integer career;
    private String email;
    private String category;
    private String password;
    private String gender;
    private String address;
    private String role;
}
