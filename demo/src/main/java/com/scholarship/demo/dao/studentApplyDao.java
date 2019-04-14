package com.scholarship.demo.dao;

import com.scholarship.demo.model.Student;
import org.apache.ibatis.annotations.*;

@Mapper
public interface studentApplyDao {

    @Update({"<script> " +
            "update student " +
            "<set> " +
            "major = #{s.major},className = #{s.className},idNumber = {s.idNumber},email = #{s.email},telephoneNumber = #{s.telephoneNumber},politicalOutlook = #{s.politicalOutlook},sex = #{s.sex},address = #{s.address},dateOfBirth = #{dateOfBirth}  " +
            "</set> " +
            "where studentId = #{s.studentId} " +
            "</script>"})
    void updateInf(@Param("s") Student student);

    @Insert({"<script> insert into scholarship (type,studentId,isSave) values (#{type},#{studentId}),#{isSave} </script>"})
    @ResultType(java.lang.Integer.class)
    Integer insertScholarship(String type,String studentId,String isSave);

}
