package com.scholarship.demo.service.impl;

import com.scholarship.demo.api.*;
import com.scholarship.demo.dao.StudentDao;
import com.scholarship.demo.model.*;
import com.scholarship.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class StudentServiceImpl implements StudentService {
    @Autowired
    StudentDao studentDao;

    @Override
    public LoginResponse login(LoginDto loginDto) {
        LoginResponse loginResponse = new LoginResponse();
        if (loginDto.getRole().equals("学生")) {
            Student student = studentDao.selectBySid(loginDto.getAccount());
            loginResponse.setUserName(student.getName());
            loginResponse.setUserType("student");
        } else if (loginDto.getRole().equals("老师")) {
            Teacher teacher = studentDao.selectByTid(loginDto.getAccount());
            loginResponse.setUserName(teacher.getName());
            loginResponse.setUserType("teacher");
        } else if (loginDto.getRole().equals("评委")) {
            Judges judges = studentDao.selectByJid(loginDto.getAccount());
            loginResponse.setUserName(judges.getName());
            loginResponse.setUserType("judges");
        } else {
            Admin admin = studentDao.selectByAid(loginDto.getAccount());
            loginResponse.setUserName(admin.getName());
            loginResponse.setUserType("manager");
        }
        return loginResponse;
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

        Scholarship scholarship = studentDao.selectBySidAndApplyType(onlineDto.getStudentId(), onlineDto.getApplyType(),onlineDto.getYear());
        if(scholarship == null){
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
            student.setFGPA(onlineDto.getFGPA());
            student.setSGPA(onlineDto.getSGPA());
            studentDao.updateInf(student);
            Integer integer = studentDao.insertScholarship(onlineDto.getApplyType(), onlineDto.getStudentId(), onlineDto.getIsSave(),onlineDto.getMajor());
            if (integer > 0) {
                return onlineDto.getIsSave() + "成功";
            } else {
                return onlineDto.getIsSave() + "失败";
            }
        }else {
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
            student.setFGPA(onlineDto.getFGPA());
            student.setSGPA(onlineDto.getSGPA());
            studentDao.updateInf(student);
            studentDao.updateScholarship(onlineDto.getApplyType(), onlineDto.getStudentId(), onlineDto.getIsSave(),onlineDto.getMajor());
            return onlineDto.getIsSave() + "成功";
        }
    }

    @Override
    public List<MyApply> myApply(String studentId) {
        List<MyApply> resultList = new ArrayList<>();
        List<Scholarship> scholarships = studentDao.selectByStudentId(studentId);
        for (Scholarship scholarship : scholarships) {
            MyApply myApply = new MyApply();
            Student student = studentDao.selectBySid(scholarship.getStudentId());
            myApply.setApplyType(scholarship.getType());
            myApply.setMajor(student.getMajor());
            myApply.setName(student.getName());
            myApply.setState(scholarship.getState());
            resultList.add(myApply);
        }
        return resultList;
    }

    @Override
    public OnlineDto edit(String name, String applyType,String year) {
        OnlineDto onlineDto = new OnlineDto();
        Student student = studentDao.selectByName(name);
        Scholarship scholarship = studentDao.selectBySidAndApplyType(student.getStudentId(), applyType,year);
        onlineDto.setAddress(student.getAddress());
        onlineDto.setApplyType(scholarship.getType());
        onlineDto.setClassName(student.getClassName());
        onlineDto.setDateOfBirth(student.getDateOfBirth());
        onlineDto.setEmail(student.getEmail());
        onlineDto.setIdNumber(student.getIdNumber());
        onlineDto.setMajor(student.getMajor());
        onlineDto.setName(student.getName());
        onlineDto.setPoliticalOutlook(student.getPoliticalOutlook());
        onlineDto.setSex(student.getSex());
        onlineDto.setStudentId(student.getStudentId());
        onlineDto.setIsSave(scholarship.getIsSave());
        return onlineDto;
    }

    @Override
    public List<ScoreQueryResponse> scoreQuery(LoginDto loginDto) {
        List<Scholarship> scholarshipList = studentDao.findBySid(loginDto.getAccount());
        List<ScoreQueryResponse> responseList = new ArrayList<>();
        for(Scholarship scholarship : scholarshipList) {
            ScoreQueryResponse response = new ScoreQueryResponse();
            response.setApplyType(scholarship.getType());
            response.setReason(scholarship.getReason());
            response.setAvg(scholarship.getIsLand());
            Student student = studentDao.selectBySid(scholarship.getStudentId());
            response.setName(student.getName());
            responseList.add(response);
        }
        return responseList;
    }
}
