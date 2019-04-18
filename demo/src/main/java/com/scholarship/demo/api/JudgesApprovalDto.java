package com.scholarship.demo.api;

import lombok.Data;

@Data
public class JudgesApprovalDto {
    private String studentId;
    private String applyType;
    private String time;
    private String twoApproval;
}
