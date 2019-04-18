package com.scholarship.demo.dao;

import com.scholarship.demo.model.Scholarship;
import com.scholarship.demo.model.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface TeacherDao {

    @Select({"<script> select * from student where major = #{major} </script>"})
    @ResultType(Student.class)
    List<Student> selectByMajor(String major);


    @Select({"<script> select * from scholarship where studentId = #{studentId} and type = #{type} and time like CONCAT('%',#{year},'%') </script>"})
    @ResultType(Scholarship.class)
    Scholarship selectBySIdAndType(String studentId,String type,String year);

    @Update({"<script> update scholarship <set> oneApproval = #{oneApproval},reason = #{reason} </set> where studentId = #{studentId} and type = #{type} and time = #{time} </script>"})
    void updateOneApproval(String studentId,String type,String time,String oneApproval,String reason);


    @Select({"<script> select * from student where major = #{major}  <if test = 'studentId != null '> and studentId = #{studentId} </if> <if test = 'name != null'> and name = #{name} </if> </script>"})
    @ResultType(Student.class)
    List<Student> findInf(String studentId,String name,String major);
}
