package com.scholarship.demo.api;

import lombok.Data;

import java.util.List;

@Data
public class TeacherResponse {

    private List<TeacherResponseDto> responseDtoList;
    private String isPass;
    private String sum;
}
