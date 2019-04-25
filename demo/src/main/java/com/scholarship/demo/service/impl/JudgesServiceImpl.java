package com.scholarship.demo.service.impl;

import com.scholarship.demo.api.*;
import com.scholarship.demo.dao.JudgesDao;
import com.scholarship.demo.model.Scholarship;
import com.scholarship.demo.model.Student;
import com.scholarship.demo.model.StudentApply;
import com.scholarship.demo.service.JudgesService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class JudgesServiceImpl implements JudgesService {

    @Autowired
    JudgesDao judgesDao;

    @Override
    public List<JudgesResponseDto> approvalFind(JudgesDto judgesDto) {
        SimpleDateFormat df2 = new SimpleDateFormat(("yyyy"));
        String year = df2.format(new Date());
        List<JudgesResponseDto> resultList = new ArrayList<>();
        List<Scholarship> scholarships = judgesDao.selectByJId(judgesDto.getType(), year, "初审通过");
        int index = 1;
        for (Scholarship scholarship : scholarships){
            JudgesResponseDto judgesResponse = new JudgesResponseDto();
            StudentApply student = judgesDao.selectById(scholarship.getStudentId());
            judgesResponse.setName("学生"+index);
            judgesResponse.setKey(scholarship.getStudentId()+"::"+scholarship.getType()+"::"+scholarship.getTime());
            judgesResponse.setTime(scholarship.getTime());
            if(scholarship.getType().equals("01")){
                judgesResponse.setType("一等奖学金");
            }else if(scholarship.getType().equals("02")){
                judgesResponse.setType("二等奖学金");
            }else if(scholarship.getType().equals("03")){
                judgesResponse.setType("三等奖学金");
            }else if(scholarship.getType().equals("04")){
                judgesResponse.setType("国家励志奖学金");
            }else if (scholarship.getType().equals("05")){
                judgesResponse.setType("国家助学金");
            }
            judgesResponse.setFgpa(student.getFGPA());
            judgesResponse.setSgpa(student.getSGPA());
            judgesResponse.setIntroduce(scholarship.getIntroduce());
            judgesResponse.setReason(scholarship.getReason());
            resultList.add(judgesResponse);
            index++;
        }
        return resultList;
    }

    @Override
    public JudgesDetailsRep details(JudgesDetailsDto detailsDto) {
//        String[] split = detailsDto.getKey().split("::");
//        Scholarship scholarship = judgesDao.selectBySIdAndType(split[0], split[1], split[2]);
//        Student student = judgesDao.selectById(scholarship.getStudentId());
//        JudgesDetailsRep result = new JudgesDetailsRep();
//        BeanUtils.copyProperties(student,result);
//        result.setApplyType(scholarship.getType());
//        result.setTime(scholarship.getTime());
        //return result;
        return null;
    }

    @Override
    public String approval(JudgesApprovalDto judgesApprovalDto) {
        String[] split = judgesApprovalDto.getKey().split("::");
        judgesDao.updateTwoApproval(split[0],split[1],split[2],"复审通过","初审通过");
        return "审批成功";
    }

    @Override
    public String notApproval(JudgesApprovalDto judgesApprovalDto) {
        String[] split = judgesApprovalDto.getKey().split("::");
        judgesDao.updateTwoApproval(split[0],split[1],split[2],"复审未通过","初审通过");
        return "审批成功";
    }

    @Override
    public List<JMyApprovalRep> myApproval() {
        SimpleDateFormat df2 = new SimpleDateFormat(("yyyy"));
        String year = df2.format(new Date());
        List<JMyApprovalRep> resultList = new ArrayList<>();
        List<Scholarship> scholarships = judgesDao.myApproval("初审通过", year);
        if (scholarships == null ||  scholarships.size() == 0){
            return null;
        }
        int index = 1;
        for (Scholarship scholarship : scholarships){
            StudentApply student = judgesDao.selectById(scholarship.getStudentId());
            JMyApprovalRep jMyApprovalRep = new JMyApprovalRep();
            jMyApprovalRep.setName("学生"+index);
            jMyApprovalRep.setTime(scholarship.getTime());
            jMyApprovalRep.setTwoApproval(scholarship.getTwoApproval());
            if(scholarship.getType().equals("01")){
                jMyApprovalRep.setType("一等奖学金");
            }else if(scholarship.getType().equals("02")){
                jMyApprovalRep.setType("二等奖学金");
            }else if(scholarship.getType().equals("03")){
                jMyApprovalRep.setType("三等奖学金");
            }else if(scholarship.getType().equals("04")){
                jMyApprovalRep.setType("国家励志奖学金");
            }else if (scholarship.getType().equals("05")){
                jMyApprovalRep.setType("国家助学金");
            }
            resultList.add(jMyApprovalRep);
            index++;
        }
        return resultList;
    }
}
