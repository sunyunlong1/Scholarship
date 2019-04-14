package com.scholarship.demo.service.impl;

import com.scholarship.demo.api.ApplyDto;
import com.scholarship.demo.api.LoginDto;
import com.scholarship.demo.api.LoginResponse;
import com.scholarship.demo.api.OnlineDto;
import com.scholarship.demo.dao.studentApplyDao;
import com.scholarship.demo.model.Student;
import com.scholarship.demo.service.StudentApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class StudentApplyServiceImpl implements StudentApplyService {
    @Autowired
    studentApplyDao studentApplyDao;

    @Override
    public LoginResponse login(LoginDto loginDto) {

        return null;
    }

    @Override
    public List<String> applyType() {
        List<String> resultList = new ArrayList<>();
        resultList.add("一等奖学金");
        resultList.add("二等奖学金");
        resultList.add("三等奖学金");
        resultList.add("国家励志奖学金");
        resultList.add("国家助学金");
        //本系统共有：一等奖学金，二等奖学金，三等奖学金，国家励志奖学金，国家助学金
        return resultList;
    }

    @Override
    public String onlineApply(OnlineDto onlineDto) {
        Student student = new Student();
        student.setAddress(onlineDto.getAddress());
        student.setEmail(onlineDto.getEmail());
        student.setSex(onlineDto.getSex());
        student.setClassName(onlineDto.getClassName());
        student.setDateOfBirth(onlineDto.getDateOfBirth());
        student.setIdNumber(onlineDto.getIdNumber());
        student.setMajor(onlineDto.getMajor());
        student.setPoliticalOutlook(onlineDto.getPoliticalOutlook());
        student.setTelephoneNUmb(onlineDto.getTelephoneNumber());
        studentApplyDao.updateInf(student);
        Integer integer = studentApplyDao.insertScholarship(onlineDto.getApplyType(), onlineDto.getStudentId(), onlineDto.getIsSave());
        if(integer>0){
            return onlineDto.getIsSave()+"成功";
        }else{
            return onlineDto.getIsSave()+"失败";
        }
    }
}
