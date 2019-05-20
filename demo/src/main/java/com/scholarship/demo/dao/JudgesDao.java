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

    @Select({"<script> select * from scholarship where studentId = #{studentId} and type = #{type} and time =  #{time} and oneApproval = #{oneApproval} </script>"})
    @ResultType(Scholarship.class)
    Scholarship selectBySIdAndType(String studentId,String type,String time,String oneApproval);

    @Update({"<script> update scholarship <set> twoApproval = #{twoApproval} </set> where studentId = #{studentId} and type = #{type} and time = #{time} and oneApproval = #{oneApproval} </script>"})
    void updateTwoApproval(String studentId,String type,String time,String twoApproval,String oneApproval);


    @Select({"<script> select * from scholarship where oneApproval = #{oneApproval} and time = #{year} and twoApproval != '' </script>"})
    @ResultType(Scholarship.class)
    List<Scholarship> myApproval(String oneApproval,String year);

    @Select({"<script> select * from judges where judgesId = #{judgesId} </script>"})
    @ResultType(Judges.class)
    Judges selectByJudgesId(String judgesId);

    @Select({"<script> select * from grade where oneGrade = #{oneGrade} and applyType = #{applyType} </script>"})
    @ResultType(Grade.class)
    List<Grade> selectByOneGrade(String oneGrade,String applyType);

    @Select({"<script> select * from grade where twoGrade = #{twoGrade} and applyType = #{applyType} </script>"})
    @ResultType(Grade.class)
    List<Grade> selectByTwoGrade(String twoGrade,String applyType);

    @Select({"<script> select * from grade where threeGrade = #{threeGrade} and applyType = #{applyType} </script>"})
    @ResultType(Grade.class)
    List<Grade> selectByThreeGrade(String threeGrade,String applyType);

    @Select({"<script> select * from grade where fourGrade = #{fourGrade} and applyType = #{applyType} </script>"})
    @ResultType(Grade.class)
    List<Grade> selectByFourGrade(String fourGrade,String applyType);

    @Select({"<script> select * from grade where fiveGrade = #{fiveGrade} and applyType = #{applyType} </script>"})
    @ResultType(Grade.class)
    List<Grade> selectByFiveGrade(String fiveGrade,String applyType);

    @Update({"<script> update grade <set> oneGrade = #{oneGrade} </set> where studentId = #{studentId} and applyType = #{type} and year = #{year} </script>"})
    void updateOneGrade(String studentId,String type,String year,String oneGrade);

    @Update({"<script> update grade <set> twoGrade = #{twoGrade} </set> where studentId = #{studentId} and applyType = #{type} and year = #{year} </script>"})
    void updateTwoGrade(String studentId,String type,String year,String twoGrade);

    @Update({"<script> update grade <set> threeGrade = #{threeGrade} </set> where studentId = #{studentId} and applyType = #{type} and year = #{year} </script>"})
    void updateThreeGrade(String studentId,String type,String year,String threeGrade);

    @Update({"<script> update grade <set> fourGrade = #{fourGrade} </set> where studentId = #{studentId} and applyType = #{type} and year = #{year} </script>"})
    void updateFourGrade(String studentId,String type,String year,String fourGrade);

    @Update({"<script> update grade <set> fiveGrade = #{fiveGrade} </set> where studentId = #{studentId} and applyType = #{type} and year = #{year} </script>"})
    void updateFiveGrade(String studentId,String type,String year,String fiveGrade);

    @Select({"<script> select * from grade where studentId = #{studentId} and applyType = #{applyType} and year = #{year} </script>"})
    @ResultType(Grade.class)
    Grade selectByKey(String studentId,String applyType,String year);

    @Select({"<script> select * from grade where oneGrade != #{oneGrade}  </script>"})
    @ResultType(Grade.class)
    List<Grade> selectOneGrade(String oneGrade);

    @Select({"<script> select * from grade where twoGrade != #{twoGrade}  </script>"})
    @ResultType(Grade.class)
    List<Grade> selectTwoGrade(String twoGrade);

    @Select({"<script> select * from grade where threeGrade != #{threeGrade}  </script>"})
    @ResultType(Grade.class)
    List<Grade> selectThreeGrade(String threeGrade);

    @Select({"<script> select * from grade where fourGrade != #{fourGrade} </script>"})
    @ResultType(Grade.class)
    List<Grade> selectFourGrade(String fourGrade);

    @Select({"<script> select * from grade where fiveGrade != #{fiveGrade}  </script>"})
    @ResultType(Grade.class)
    List<Grade> selectFiveGrade(String fiveGrade);


}
