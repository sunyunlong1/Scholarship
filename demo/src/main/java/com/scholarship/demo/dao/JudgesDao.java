package com.scholarship.demo.dao;

import com.scholarship.demo.model.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface JudgesDao {


    @Select({"<script> select * from scholarship where type = #{type} and oneApproval = #{oneApproval} and time like CONCAT('%',#{year},'%') and twoApproval = #{twoApproval} </script>"})
    @ResultType(Scholarship.class)
    List<Scholarship> selectByJId(String type, String year, String oneApproval,String twoApproval);


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

    @Select({"<script> select * from judges where judgesId = #{judgesId} </script>"})
    @ResultType(Judges.class)
    Judges selectByJudgesId(String judgesId);

    @Select({"<script> select * from grade where oneGrade = #{oneGrade} </script>"})
    @ResultType(Grade.class)
    List<Grade> selectByOneGrade(String oneGrade);

    @Select({"<script> select * from grade where twoGrade = #{twoGrade} </script>"})
    @ResultType(Grade.class)
    List<Grade> selectByTwoGrade(String twoGrade);

    @Select({"<script> select * from grade where threeGrade = #{threeGrade} </script>"})
    @ResultType(Grade.class)
    List<Grade> selectByThreeGrade(String threeGrade);

    @Select({"<script> select * from grade where fourGrade = #{fourGrade} </script>"})
    @ResultType(Grade.class)
    List<Grade> selectByFourGrade(String fourGrade);

    @Select({"<script> select * from grade where fiveGrade = #{fiveGrade} </script>"})
    @ResultType(Grade.class)
    List<Grade> selectByFiveGrade(String fiveGrade);

}
