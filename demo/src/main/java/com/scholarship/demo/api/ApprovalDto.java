package com.scholarship.demo.api;

import lombok.Data;

@Data
public class ApprovalDto {
    private String key;
    private String isPass;
    private String reason;
}
