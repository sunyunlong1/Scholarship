package com.scholarship.demo.service.impl;

import com.scholarship.demo.api.*;
import com.scholarship.demo.dao.JudgesDao;
import com.scholarship.demo.model.Scholarship;
import com.scholarship.demo.model.Student;
import com.scholarship.demo.service.JudgesService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class JudgesServiceImpl implements JudgesService {

    @Autowired
    JudgesDao judgesDao;

    @Override
    public List<JudgesResponseDto> approvalFind(JudgesDto judgesDto) {
        List<JudgesResponseDto> resultList = new ArrayList<>();
        List<Scholarship> scholarships = judgesDao.selectByJId(judgesDto.getType(), judgesDto.getYear(), judgesDto.getAccount());
        for (Scholarship scholarship : scholarships){
            JudgesResponseDto judgesResponse = new JudgesResponseDto();
            Student student = judgesDao.selectById(scholarship.getStudentId());
            judgesResponse.setName(student.getName());
            judgesResponse.setStudentId(student.getStudentId());
            judgesResponse.setTime(scholarship.getTime());
            judgesResponse.setType(scholarship.getType());
            resultList.add(judgesResponse);
        }
        return resultList;
    }

    @Override
    public JudgesDetailsRep details(JudgesDetailsDto detailsDto) {
        Scholarship scholarship = judgesDao.selectBySIdAndType(detailsDto.getStudentId(), detailsDto.getType(), detailsDto.getTime());
        Student student = judgesDao.selectById(scholarship.getStudentId());
        JudgesDetailsRep result = new JudgesDetailsRep();
        BeanUtils.copyProperties(student,result);
        result.setApplyType(scholarship.getType());
        result.setTime(scholarship.getTime());
        return result;
    }

    @Override
    public String approval(JudgesApprovalDto judgesApprovalDto) {
        judgesDao.updateTwoApproval(judgesApprovalDto.getStudentId(),judgesApprovalDto.getApplyType(),judgesApprovalDto.getTime(),judgesApprovalDto.getTwoApproval());
        return "审批成功";
    }

    @Override
    public List<JMyApprovalRep> myApproval(JudgesDto judgesDto) {
        List<JMyApprovalRep> resultList = new ArrayList<>();
        List<Scholarship> scholarships = judgesDao.myApproval(judgesDto.getAccount(), judgesDto.getYear());
        if (scholarships == null ||  scholarships.size() == 0){
            return null;
        }
        for (Scholarship scholarship : scholarships){
            Student student = judgesDao.selectById(scholarship.getStudentId());
            JMyApprovalRep jMyApprovalRep = new JMyApprovalRep();
            jMyApprovalRep.setMajor(student.getMajor());
            jMyApprovalRep.setStudentId(scholarship.getStudentId());
            jMyApprovalRep.setTime(scholarship.getTime());
            jMyApprovalRep.setTwoApproval(scholarship.getTwoApproval());
            jMyApprovalRep.setType(scholarship.getType());
            resultList.add(jMyApprovalRep);
        }
        return resultList;
    }
}
