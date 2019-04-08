package com.scholarship.demo.dao;

import com.scholarship.demo.model.Student;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface studentApplyDao {

    /**
     * 在线申请，提交
     * @param student
     * @return
     */
    public Integer apply(Student student);
}
