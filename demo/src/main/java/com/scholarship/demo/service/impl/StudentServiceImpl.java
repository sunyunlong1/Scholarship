package com.scholarship.demo.service.impl;

import com.scholarship.demo.api.*;
import com.scholarship.demo.dao.StudentDao;
import com.scholarship.demo.model.*;
import com.scholarship.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
        LoginResponse loginResponse = null;
        if (loginDto.getRole().equals("学生")) {
            Student student = studentDao.selectBySid(loginDto.getAccount());
            if(student!=null && student.getPassword().equals(loginDto.getPassword())){
                loginResponse = new LoginResponse();
                loginResponse.setUserName(student.getName());
                loginResponse.setUserType("student");
                loginResponse.setAccount(student.getStudentId());
            }
        } else if (loginDto.getRole().equals("教师")) {
            Teacher teacher = studentDao.selectByTid(loginDto.getAccount());
            if (teacher != null && teacher.getPassword().equals(loginDto.getPassword())){
                loginResponse = new LoginResponse();
                loginResponse.setUserName(teacher.getName());
                loginResponse.setUserType("teacher");
                loginResponse.setAccount(teacher.getTeacherId());
            }
        } else if (loginDto.getRole().equals("评委")) {
            Judges judges = studentDao.selectByJid(loginDto.getAccount());
            if(judges != null && judges.getPassword().equals(loginDto.getPassword())){
                loginResponse = new LoginResponse();
                loginResponse.setUserName(judges.getName());
                loginResponse.setUserType("judges");
                loginResponse.setAccount(judges.getJudgesId());
            }
        } else {
            Admin admin = studentDao.selectByAid(loginDto.getAccount());
            if (admin != null && admin.getPassword().equals(loginDto.getPassword())){
                loginResponse = new LoginResponse();
                loginResponse.setUserName(admin.getName());
                loginResponse.setUserType("manager");
                loginResponse.setAccount(admin.getAdminId());
            }
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
        Scholarship scholarship = studentDao.selectBySidAndApplyType(onlineDto.getStudentId(), onlineDto.getApplyType(), year);
        if (scholarship == null) {
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
            studentApply.setCollege(onlineDto.getCollege());
            studentApply.setPoliticalOutlook(onlineDto.getPoliticalOutlook());
            studentApply.setTelephoneNumber(onlineDto.getTelephoneNumber());
            studentApply.setFGPA(onlineDto.getFGPA());
            studentApply.setSGPA(onlineDto.getSGPA());
            StudentApply studentApply1 = studentDao.selectBySApplyid(onlineDto.getStudentId());
            if(studentApply1 == null){
                studentDao.insertInf(studentApply);
            }
            if(onlineDto.getApplyType().equals("一等奖学金")){
                onlineDto.setApplyType("01");
            }else if(onlineDto.getApplyType().equals("二等奖学金")){
                onlineDto.setApplyType("02");
            }else if(onlineDto.getApplyType().equals("三等奖学金")){
                onlineDto.setApplyType("03");
            }else if(onlineDto.getApplyType().equals("国家励志奖学金")){
                onlineDto.setApplyType("04");
            }else if (onlineDto.getApplyType().equals("国家助学金")){
                onlineDto.setApplyType("05");
            }

            Integer integer1 = studentDao.insertGrade(onlineDto.getApplyType(), onlineDto.getStudentId(), year);

            Integer integer = studentDao.insertScholarship(onlineDto.getApplyType(), onlineDto.getStudentId(), "已提交", onlineDto.getMajor(), onlineDto.getCollege(), time, onlineDto.getIntroduce(),onlineDto.getPath(),onlineDto.getFileName());
            if (integer > 0) {
                return "提交" + "成功";
            } else {
                return "提交" + "失败";
            }
        } else {
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
            studentApply.setCollege(onlineDto.getCollege());
            studentApply.setPoliticalOutlook(onlineDto.getPoliticalOutlook());
            studentApply.setTelephoneNumber(onlineDto.getTelephoneNumber());
            studentApply.setFGPA(onlineDto.getFGPA());
            studentApply.setSGPA(onlineDto.getSGPA());
            studentDao.updateInf(studentApply);
            if(onlineDto.getApplyType().equals("一等奖学金")){
                onlineDto.setApplyType("01");
            }else if(onlineDto.getApplyType().equals("二等奖学金")){
                onlineDto.setApplyType("02");
            }else if(onlineDto.getApplyType().equals("三等奖学金")){
                onlineDto.setApplyType("03");
            }else if(onlineDto.getApplyType().equals("国家励志奖学金")){
                onlineDto.setApplyType("04");
            }else if (onlineDto.getApplyType().equals("国家助学金")){
                onlineDto.setApplyType("05");
            }
            studentDao.updateScholarship(onlineDto.getApplyType(), onlineDto.getStudentId(), "已提交", onlineDto.getMajor(), onlineDto.getCollege(), time, onlineDto.getIntroduce(),onlineDto.getPath(),onlineDto.getFileName());
            return "提交" + "成功";
        }
    }


    @Override
    public List<MyApply> myApply(String studentId) {
        List<MyApply> resultList = new ArrayList<>();
        List<Scholarship> scholarships = studentDao.selectByStudentId(studentId);
        if (scholarships != null) {
            for (Scholarship scholarship : scholarships) {
                MyApply myApply = new MyApply();
                StudentApply student = studentDao.selectBySApplyid(scholarship.getStudentId());
                if (scholarship.getType().equals("01")) {
                    myApply.setApplyType("一等奖学金");
                } else if (scholarship.getType().equals("02")) {
                    myApply.setApplyType("二等奖学金");
                } else if (scholarship.getType().equals("03")) {
                    myApply.setApplyType("三等奖学金");
                } else if (scholarship.getType().equals("04")) {
                    myApply.setApplyType("国家励志奖学金");
                } else if (scholarship.getType().equals("05")) {
                    myApply.setApplyType("国家助学金");
                }
                if (student != null){
                    myApply.setMajor(student.getMajor());
                    myApply.setCollege(student.getCollege());
                    myApply.setName(student.getName());
                }
                myApply.setState(scholarship.getIsSave());
                myApply.setKey(scholarship.getStudentId() + "::" + scholarship.getType() + "::" + scholarship.getTime());
                resultList.add(myApply);
            }
        }
        return resultList;
    }

    @Override
    public OnlineDto details(String key) {
        String[] split = key.split("::");

        OnlineDto onlineDto = new OnlineDto();
        StudentApply student = studentDao.selectByName(split[0]);
        Scholarship scholarship = studentDao.selectBySidAndApplyType(student.getStudentId(), split[1], split[2]);
        if (scholarship != null) {
            onlineDto.setAddress(student.getAddress());
            if (scholarship.getType().equals("01")) {
                onlineDto.setApplyType("一等奖学金");
            } else if (scholarship.getType().equals("02")) {
                onlineDto.setApplyType("二等奖学金");
            } else if (scholarship.getType().equals("03")) {
                onlineDto.setApplyType("三等奖学金");
            } else if (scholarship.getType().equals("04")) {
                onlineDto.setApplyType("国家励志奖学金");
            } else if (scholarship.getType().equals("05")) {
                onlineDto.setApplyType("国家助学金");
            }
            if (student != null){
                onlineDto.setClassName(student.getClassName());
                onlineDto.setDateOfBirth(student.getDateOfBirth());
                onlineDto.setEmail(student.getEmail());
                onlineDto.setIdNumber(student.getIdNumber());
                onlineDto.setMajor(student.getMajor());
                onlineDto.setCollege(student.getCollege());
                onlineDto.setName(student.getName());
                onlineDto.setPoliticalOutlook(student.getPoliticalOutlook());
                onlineDto.setSex(student.getSex());
                onlineDto.setStudentId(student.getStudentId());
                onlineDto.setTelephoneNumber(student.getTelephoneNumber());
                onlineDto.setFGPA(student.getFGPA());
                onlineDto.setSGPA(student.getSGPA());
            }
            onlineDto.setIntroduce(scholarship.getIntroduce());
        }
        return onlineDto;
    }

    @Override
    public List<ScoreQueryResponse> scoreQuery(LoginDto loginDto) {
        List<Scholarship> scholarshipList = studentDao.findBySid(loginDto.getAccount());
        List<ScoreQueryResponse> responseList = new ArrayList<>();
        if (scholarshipList != null && scholarshipList.size() != 0 ) {
            for (Scholarship scholarship : scholarshipList) {
                ScoreQueryResponse response = new ScoreQueryResponse();
                if (scholarship.getType().equals("01")) {
                    response.setApplyType("一等奖学金");
                } else if (scholarship.getType().equals("02")) {
                    response.setApplyType("二等奖学金");
                } else if (scholarship.getType().equals("03")) {
                    response.setApplyType("三等奖学金");
                } else if (scholarship.getType().equals("04")) {
                    response.setApplyType("国家励志奖学金");
                } else if (scholarship.getType().equals("05")) {
                    response.setApplyType("国家助学金");
                }
                Student student = studentDao.selectBySid(scholarship.getStudentId());
                if (student != null){
                    response.setName(student.getName());
                }
                response.setOneApproval(scholarship.getOneApproval().equals("") ? "-" : scholarship.getOneApproval());
                response.setReason(scholarship.getReason().equals("") ? "-" : scholarship.getReason());
                response.setTwoApproval(scholarship.getTwoApproval().equals("") ? "-" : scholarship.getTwoApproval());
                responseList.add(response);
            }
        }
        return responseList;
    }
}
