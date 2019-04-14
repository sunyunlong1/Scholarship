package com.scholarship.demo.model;

import lombok.Data;

@Data
public class Grade {

    private int id;
    private String scholarshipId;
    private String studentId;
    private String grade;
    private String scholarshipType;
    private String judgesId;
}
