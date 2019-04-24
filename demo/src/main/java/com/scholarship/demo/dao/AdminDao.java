package com.scholarship.demo.dao;

import com.scholarship.demo.model.Judges;
import com.scholarship.demo.model.Scholarship;
import com.scholarship.demo.model.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AdminDao {

    @Select({"<script> select count(1) from scholarship where type = #{type} and time = #{year} and twoApproval = #{twoApproval} </script>"})
    @ResultType(java.lang.Integer.class)
    Integer selectSum(String type,String year,String twoApproval);

    @Select({"<script> select * from scholarship where type = #{type} and time like CONCAT('%',#{year},'%') </script>"})
    @ResultType(Scholarship.class)
    List<Scholarship> selectByTAndY(String type,String year);

    @Select({"<script> select * from student where studentId = #{studentId}</script>"})
    @ResultType(Student.class)
    Student selectByAccount(String studentId);

    @Select({"<script> select * from judges  </script>"})
    @ResultType(Judges.class)
    List<Judges> selectByJAccount();

}
