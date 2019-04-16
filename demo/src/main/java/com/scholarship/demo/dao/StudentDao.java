package com.scholarship.demo.dao;

import com.scholarship.demo.model.*;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface StudentDao {

    @Update({"<script> " +
            "update student " +
            "<set> " +
            "major = #{s.major},className = #{s.className},idNumber = {s.idNumber},email = #{s.email},telephoneNumber = #{s.telephoneNumber},politicalOutlook = #{s.politicalOutlook},sex = #{s.sex},address = #{s.address},dateOfBirth = #{dateOfBirth},fGPA = #{s.fGPA},sGPA = #{s.sGPA}  " +
            "</set> " +
            "where studentId = #{s.studentId} " +
            "</script>"})
    void updateInf(@Param("s") Student student);

    @Insert({"<script> insert into scholarship (type,studentId,isSave,major) values (#{type},#{studentId},#{isSave},#{major})</script>"})
    @ResultType(java.lang.Integer.class)
    Integer insertScholarship(String type,String studentId,String isSave,String major);


    @Update({"<script> update scholarship <set> isSave = #{isSave},major = #{major} </set> where type = #{type} and studentId = #{studentId} </script>"})
    void updateScholarship(String type,String studentId,String isSave,String major);

    @Select({"<script> select * from student where studentId = #{studentId} </script>"})
    @ResultType(Student.class)
    Student selectBySid(String studentId);

    @Select({"<script> select * from teacher where teacherId = #{teacherId} </script>"})
    @ResultType(Teacher.class)
    Teacher selectByTid(String teacherId);

    @Select({"<script> select * from judges where judgesId = #{judgesId} </script>"})
    @ResultType(Judges.class)
    Judges selectByJid(String judgesId);

    @Select({"<script> select * from admin where adminId = #{adminId} </script>"})
    @ResultType(Admin.class)
    Admin selectByAid(String adminId);

    @Select({"<script> select * from scholarship where studentId = #{studentId} </script>"})
    @ResultType(Scholarship.class)
    List<Scholarship> selectByStudentId(String studentId);

    @Select({"<script> select * from scholarship where studentId = #{studentId} and type = #{applyType} and time '%'||#{year}||'%' </script>"})
    @ResultType(Scholarship.class)
    Scholarship selectBySidAndApplyType(String studentId,String applyType,String year);

    @Select({"<script> select * from student where name = #{userName} </script>"})
    @ResultType(Student.class)
    Student selectByName(String userName);


    @Select({"<script> select * from scholarship where studentId = #{studentId} </script>"})
    @ResultType(Scholarship.class)
    Scholarship findBySid(String studentId);

    @Update({"<script> update student <set> password = #{password} </set> where studentId = #{account} </script>"})
    void updateSPassword(String account,String password);

    @Update({"<script> update teacher <set> password = #{password} </set> where teacherId = #{account} </script>"})
    void updateTPassword(String account,String password);

    @Update({"<script> update admin <set> password = #{password} </set> where adminId = #{account} </script>"})
    void updateAPassword(String account,String password);

    @Update({"<script> update judges <set> password = #{password} </set> where judgesId = #{account} </script>"})
    void updateJPassword(String account,String password);
}
