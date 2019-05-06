package com.scholarship.demo.api;

import com.scholarship.demo.model.Student;
import lombok.Data;

@Data
public class TeacherResponseDto implements Comparable<TeacherResponseDto>{
    private String name;
    private String time;
    private String studentId;
    private String type;
    private String key;
    private String fGPA;
    private String sGPA;

    @Override
    public int compareTo(TeacherResponseDto o) {
        Double fgpa = Double.valueOf(this.getFGPA());
        Double fgpa2 = Double.valueOf(o.getFGPA());
        if (!fgpa.equals(fgpa2)){
            if (fgpa > fgpa2){
                return -1;
            }else{
                return 1;
            }
        }else{
            Double sgpa = Double.valueOf(this.getSGPA());
            Double sgpa2 = Double.valueOf(o.getSGPA());
            if (sgpa > sgpa2){
                return -1;
            }else{
                return 1;
            }
        }
    }
}
