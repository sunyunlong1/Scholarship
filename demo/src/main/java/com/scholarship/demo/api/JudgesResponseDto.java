package com.scholarship.demo.api;

import lombok.Data;

@Data
public class JudgesResponseDto {
    private String name;
    private String time;
    private String type;
    private String key;
    private String fgpa;
    private String sgpa;
    private String introduce;
    private String reason;
}
