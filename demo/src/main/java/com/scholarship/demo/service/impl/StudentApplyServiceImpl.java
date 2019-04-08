package com.scholarship.demo.service.impl;

import com.scholarship.demo.dao.studentApplyDao;
import com.scholarship.demo.model.Student;
import com.scholarship.demo.service.StudentApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class StudentApplyServiceImpl implements StudentApplyService {
@Autowired
private studentApplyDao studentApplyDao;

    @Override
    public Integer apply(Student student) {
        Integer apply = studentApplyDao.apply(student);
        return apply;
    }
}
