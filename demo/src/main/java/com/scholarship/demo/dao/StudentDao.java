package com.scholarship.demo.dao;

import com.scholarship.demo.model.*;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface StudentDao {

    @Insert({"<script> " +
            "insert into studentApply (name,studentId,major,college,className,idNumber,email,telephoneNumber,politicalOutlook,sex,address,dateOfBirth,fGPA,sGPA)  " +
            "values(#{s.name},#{s.studentId},#{s.major},#{s.college},#{s.className},#{s.idNumber},#{s.email},#{s.telephoneNumber},#{s.politicalOutlook},#{s.sex},#{s.address},#{s.dateOfBirth},#{s.fGPA},#{s.sGPA})"+
            "</script>"})
    @ResultType(java.lang.Integer.class)
    Integer insertInf(@Param("s") StudentApply student);


    @Update({"<script> update studentApply " +
            "<set> " +
            "major = #{s.major},college = #{s.college},name = #{s.name},className = #{s.className},idNumber = #{s.idNumber},email = #{s.email},telephoneNumber = #{s.telephoneNumber},politicalOutlook = #{s.politicalOutlook},sex = #{s.sex},address = #{s.address},dateOfBirth = #{s.dateOfBirth},fGPA = #{s.fGPA},sGPA = #{s.sGPA} " +
            "</set> " +
            "where studentId = #{s.studentId} </script>"})
    void updateInf(@Param("s") StudentApply student);

    @Insert({"<script> insert into scholarship (type,studentId,isSave,major,college,time,introduce) values (#{type},#{studentId},#{isSave},#{major},#{college},#{time},#{introduce}) </script>"})
    @ResultType(java.lang.Integer.class)
    Integer insertScholarship(String type,String studentId,String isSave,String major,String college,String time,String introduce);

    @Insert({"<script> insert into grade (applyType,studentId,year) values (#{type},#{studentId},#{year}) </script>"})
    @ResultType(java.lang.Integer.class)
    Integer insertGrade(String type,String studentId,String year);

    @Update({"<script> update scholarship <set> isSave = #{isSave},major = #{major},college = #{college},time = #{time},introduce = #{introduce} </set> where type = #{type} and studentId = #{studentId} </script>"})
    void updateScholarship(String type,String studentId,String isSave,String major,String college,String time,String introduce);


    @Select({"<script> select * from student where studentId = #{studentId} </script>"})
    @ResultType(Student.class)
    Student selectBySid(String studentId);



    @Select({"<script> select * from studentApply where studentId = #{studentId} </script>"})
    @ResultType(StudentApply.class)
    StudentApply selectBySApplyid(String studentId);

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

    @Select({"<script> select * from scholarship where studentId = #{studentId} and type = #{applyType} and time like CONCAT('%',#{year},'%') </script>"})
    @ResultType(Scholarship.class)
    Scholarship selectBySidAndApplyType(String studentId,String applyType,String year);

    @Select({"<script> select * from studentApply where studentId = #{studentId} </script>"})
    @ResultType(StudentApply.class)
    StudentApply selectByName(String studentId);


    @Select({"<script> select * from scholarship where studentId = #{studentId} </script>"})
    @ResultType(Scholarship.class)
    List<Scholarship> findBySid(String studentId);

    @Update({"<script> update student <set> password = #{password} </set> where studentId = #{account} </script>"})
    void updateSPassword(String account,String password);

    @Update({"<script> update teacher <set> password = #{password} </set> where teacherId = #{account} </script>"})
    void updateTPassword(String account,String password);

    @Update({"<script> update admin <set> password = #{password} </set> where adminId = #{account} </script>"})
    void updateAPassword(String account,String password);

    @Update({"<script> update judges <set> password = #{password} </set> where judgesId = #{account} </script>"})
    void updateJPassword(String account,String password);
}
