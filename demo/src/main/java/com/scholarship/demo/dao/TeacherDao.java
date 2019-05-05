package com.scholarship.demo.dao;

import com.scholarship.demo.model.Scholarship;
import com.scholarship.demo.model.Student;
import com.scholarship.demo.model.StudentApply;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface TeacherDao {

    @Select({"<script> select * from Scholarship where major = #{major} and type = #{type} and time = #{year} and oneApproval = #{oneApproval} </script>"})
    @ResultType(Scholarship.class)
    List<Scholarship> selectByMajor(String major,String type,String year,String oneApproval);


    @Select({"<script> select * from scholarship where studentId = #{studentId} and type = #{type} and time = #{time} </script>"})
    @ResultType(Scholarship.class)
    Scholarship selectBySIdAndType(String studentId,String type,String time);

    @Update({"<script> update scholarship <set> oneApproval = #{oneApproval},reason = #{reason} </set> where studentId = #{studentId} and type = #{type} and time = #{time} </script>"})
    void updateOneApproval(String studentId,String type,String time,String oneApproval,String reason);


    @Select({"<script> select * from student where major = #{major}  <if test = 'studentId != null '> and studentId = #{studentId} </if> <if test = 'name != null'> and name = #{name} </if>  <if test = 'college != null'> and college = #{college} </if> </script>"})
    @ResultType(Student.class)
    List<Student> findInf(String studentId,String name,String major,String college);

    @Select({"<script> select * from studentApply where studentId = #{studentId} </script>"})
    @ResultType(StudentApply.class)
    StudentApply selectById(String studentId);
}
