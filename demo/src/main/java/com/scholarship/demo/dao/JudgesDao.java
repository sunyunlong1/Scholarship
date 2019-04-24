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
public interface JudgesDao {


    @Select({"<script> select * from scholarship where type = #{type} and oneApproval = #{oneApproval} and time like CONCAT('%',#{year},'%')  </script>"})
    @ResultType(Scholarship.class)
    List<Scholarship> selectByJId(String type, String year, String oneApproval);


    @Select({"<script> select * from studentApply where studentId = #{studentId} </script>"})
    @ResultType(StudentApply.class)
    StudentApply selectById(String studentId);

    @Select({"<script> select * from scholarship where studentId = #{studentId} and type = #{type} and time =  #{time} </script>"})
    @ResultType(Scholarship.class)
    Scholarship selectBySIdAndType(String studentId,String type,String time);

    @Update({"<script> update scholarship <set> twoApproval = #{twoApproval} </set> where studentId = #{studentId} and type = #{type} and time = #{time} and oneApproval = #{oneApproval} </script>"})
    void updateTwoApproval(String studentId,String type,String time,String twoApproval,String oneApproval);


    @Select({"<script> select * from scholarship where oneApproval = #{oneApproval} and time = #{year} and twoApproval != '' </script>"})
    @ResultType(Scholarship.class)
    List<Scholarship> myApproval(String oneApproval,String year);

}
