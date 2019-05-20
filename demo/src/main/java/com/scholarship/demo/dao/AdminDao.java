package com.scholarship.demo.dao;

import com.scholarship.demo.model.Grade;
import com.scholarship.demo.model.Judges;
import com.scholarship.demo.model.Scholarship;
import com.scholarship.demo.model.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface AdminDao {

    @Select({"<script> select count(1) from scholarship where type = #{type} and time = #{year} and twoApproval = #{twoApproval} and oneApproval = #{oneApproval} </script>"})
    @ResultType(java.lang.Integer.class)
    Integer selectNotSum(String type,String year,String twoApproval,String oneApproval);

    @Select({"<script> select count(1) from scholarship where type = #{type} and time = #{year} and twoApproval != #{twoApproval} and oneApproval = #{oneApproval} </script>"})
    @ResultType(java.lang.Integer.class)
    Integer selectSum(String type,String year,String twoApproval,String oneApproval);

    @Select({"<script> select count(1) from scholarship where type = #{type} and time = #{year} and oneApproval = #{oneApproval} </script>"})
    @ResultType(java.lang.Integer.class)
    Integer selectALL(String type,String year,String oneApproval);

    @Select({"<script> select * from scholarship where type = #{type} and time like CONCAT('%',#{year},'%') and twoApproval != #{twoApproval}  </script>"})
    @ResultType(Scholarship.class)
    List<Scholarship> selectByTAndY(String type,String year,String twoApproval);

    @Select({"<script> select * from student where studentId = #{studentId}</script>"})
    @ResultType(Student.class)
    Student selectByAccount(String studentId);

    @Select({"<script> select * from judges  </script>"})
    @ResultType(Judges.class)
    List<Judges> selectByJAccount();

    @Select({"<script> select * from scholarship where type = #{type} and time = #{year} and oneApproval = #{oneApproval} and twoApproval != #{twoApproval} </script>"})
    @ResultType(Scholarship.class)
    List<Scholarship> selectByKey(String type,String year,String oneApproval,String twoApproval);

    @Select({"<script> select * from scholarship where type = #{type} and time = #{year} and oneApproval = #{oneApproval} and twoApproval = #{twoApproval} </script>"})
    @ResultType(Scholarship.class)
    List<Scholarship> selectByKey2(String type,String year,String oneApproval,String twoApproval);


    @Update({"<script> update scholarship <set> twoApproval = #{twoApproval} </set> where studentId = #{studentId} and type = #{type} and time = #{year} </script>"})
    void UpdateSTwoApproval(String studentId,String type,String year,String twoApproval);

    @Select({"<script> select * from grade where studentId = #{studentId} and applyType = #{applyType} and year = #{year} </script>"})
    @ResultType(Grade.class)
    Grade selectByGradeKey(String studentId,String applyType,String year);

    @Select({"<script> select * from judges where number = #{number} </script>"})
    @ResultType(Judges.class)
    Judges selectByNumber(String number);
}
