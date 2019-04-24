package com.scholarship.demo.service.impl;

import com.scholarship.demo.api.*;
import com.scholarship.demo.dao.StudentDao;
import com.scholarship.demo.model.*;
import com.scholarship.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
        SimpleDateFormat df = new SimpleDateFormat("yyyy");
        String time = df.format(new Date());
        SimpleDateFormat df2 = new SimpleDateFormat(("yyyy"));
        String year = df2.format(new Date());
        Scholarship scholarship = studentDao.selectBySidAndApplyType(onlineDto.getStudentId(), onlineDto.getApplyType(),year);
        if(scholarship == null){
            StudentApply studentApply = new StudentApply();
            studentApply.setName(onlineDto.getName());
            studentApply.setStudentId(onlineDto.getStudentId());
            studentApply.setAddress(onlineDto.getAddress());
            studentApply.setEmail(onlineDto.getEmail());
            studentApply.setSex(onlineDto.getSex());
            studentApply.setClassName(onlineDto.getClassName());
            studentApply.setDateOfBirth(onlineDto.getDateOfBirth());
            studentApply.setIdNumber(onlineDto.getIdNumber());
            studentApply.setMajor(onlineDto.getMajor());
            studentApply.setPoliticalOutlook(onlineDto.getPoliticalOutlook());
            studentApply.setTelephoneNumber(onlineDto.getTelephoneNumber());
            studentApply.setFGPA(onlineDto.getFgpa());
            studentApply.setSGPA(onlineDto.getSgpa());
            studentDao.insertInf(studentApply);
            Integer integer = studentDao.insertScholarship(onlineDto.getApplyType(), onlineDto.getStudentId(), onlineDto.getIsSave(),onlineDto.getMajor(),time,onlineDto.getIntroduce());
            if (integer > 0) {
                return onlineDto.getIsSave() + "成功";
            } else {
                return onlineDto.getIsSave() + "失败";
            }
        }else {
            StudentApply studentApply = new StudentApply();
            studentApply.setName(onlineDto.getName());
            studentApply.setStudentId(onlineDto.getStudentId());
            studentApply.setAddress(onlineDto.getAddress());
            studentApply.setEmail(onlineDto.getEmail());
            studentApply.setSex(onlineDto.getSex());
            studentApply.setClassName(onlineDto.getClassName());
            studentApply.setDateOfBirth(onlineDto.getDateOfBirth());
            studentApply.setIdNumber(onlineDto.getIdNumber());
            studentApply.setMajor(onlineDto.getMajor());
            studentApply.setPoliticalOutlook(onlineDto.getPoliticalOutlook());
            studentApply.setTelephoneNumber(onlineDto.getTelephoneNumber());
            studentApply.setFGPA(onlineDto.getFgpa());
            studentApply.setSGPA(onlineDto.getSgpa());
            studentDao.updateInf(studentApply);
            studentDao.updateScholarship(onlineDto.getApplyType(), onlineDto.getStudentId(), onlineDto.getIsSave(),onlineDto.getMajor(),time,onlineDto.getIntroduce());
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
            if(scholarship.getType().equals("01")){
                myApply.setApplyType("一等奖学金");
            }else if(scholarship.getType().equals("02")){
                myApply.setApplyType("二等奖学金");
            }else if(scholarship.getType().equals("03")){
                myApply.setApplyType("三等奖学金");
            }else if(scholarship.getType().equals("04")){
                myApply.setApplyType("国家励志奖学金");
            }else if (scholarship.getType().equals("05")){
                myApply.setApplyType("国家助学金");
            }
            myApply.setMajor(student.getMajor());
            myApply.setName(student.getName());
            myApply.setState(scholarship.getIsSave());
            myApply.setKey(scholarship.getStudentId()+"::"+scholarship.getType()+"::"+scholarship.getTime());
            resultList.add(myApply);
        }
        return resultList;
    }

    @Override
    public OnlineDto edit(String key) {
        String[] split = key.split("::");

        OnlineDto onlineDto = new OnlineDto();
        StudentApply student = studentDao.selectByName(split[0]);
        Scholarship scholarship = studentDao.selectBySidAndApplyType(student.getStudentId(), split[1],split[2]);
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
            if(scholarship.getType().equals("01")){
                response.setApplyType("一等奖学金");
            }else if(scholarship.getType().equals("02")){
                response.setApplyType("二等奖学金");
            }else if(scholarship.getType().equals("03")){
                response.setApplyType("三等奖学金");
            }else if(scholarship.getType().equals("04")){
                response.setApplyType("国家励志奖学金");
            }else if (scholarship.getType().equals("05")){
                response.setApplyType("国家助学金");
            }
            response.setReason(scholarship.getReason());
            response.setAvg(scholarship.getIsLand());
            Student student = studentDao.selectBySid(scholarship.getStudentId());
            response.setName(student.getName());
            responseList.add(response);
        }
        return responseList;
    }
}
