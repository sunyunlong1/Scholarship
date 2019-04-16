package com.scholarship.demo.api;

import lombok.Data;

@Data
public class ApprovalDto {
    private String studentId;
    private String applyType;
    private String time;
    private String isPass;
    private String reason;
}
