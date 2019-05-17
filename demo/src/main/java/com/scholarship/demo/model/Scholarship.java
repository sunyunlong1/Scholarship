package com.scholarship.demo.model;

import lombok.Data;

@Data
public class Scholarship implements Comparable<Scholarship>{

    private int id;
    private String type;
    private String studentId;
    private String state;
    private String oneApproval;
    private String introduce;
    private String reason;
    private String twoApproval;
    private String major;
    private String college;
    private String isSave;
    private String isLand;
    private String time;
    private String path;
    private String fileName;

    @Override
    public int compareTo(Scholarship o) {
        Double avg = Double.valueOf(this.getTwoApproval());
        Double fAvg = Double.valueOf(o.getTwoApproval());
        if (avg > fAvg){
            return -1;
        }else{
            return 1;
        }
    }
}
