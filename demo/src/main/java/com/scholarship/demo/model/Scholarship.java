package com.scholarship.demo.model;

import lombok.Data;

@Data
public class Scholarship {

    private int id;
    private String type;
    private String studentId;
    private String state;
    private String teacherId;
    private String judgesId;
    private String oneApproval;
    private String twoApproval;
    private String major;
    private String isSave;
    private String isLand;

}
