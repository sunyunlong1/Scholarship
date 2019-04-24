package com.scholarship.demo.service.impl;

import com.scholarship.demo.api.*;
import com.scholarship.demo.dao.StudentDao;
import com.scholarship.demo.dao.TeacherDao;
import com.scholarship.demo.model.Scholarship;
import com.scholarship.demo.model.Student;
import com.scholarship.demo.model.StudentApply;
import com.scholarship.demo.model.Teacher;
import com.scholarship.demo.service.TeacherService;
import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    TeacherDao teacherDao;
    @Autowired
    StudentDao studentDao;

    @Override
    public TeacherResponse applyManager(TeacherDto teacherDto) {
        SimpleDateFormat df2 = new SimpleDateFormat(("yyyy"));
        String year = df2.format(new Date());
        TeacherResponse result = new TeacherResponse();
        Teacher teacher = studentDao.selectByTid(teacherDto.getAccount());
        List<StudentApply> students = teacherDao.selectByMajor(teacher.getMajor());
        List<TeacherResponseDto> resultList = new ArrayList<>();
        int isPass = 0;
        int notPass = 0;
        for(StudentApply student : students){
            TeacherResponseDto response = new TeacherResponseDto();
            Scholarship scholarship = teacherDao.selectBySIdAndType(student.getStudentId(), teacherDto.getType(),year);
            if(scholarship.getOneApproval().equals("通过")){
                isPass++;
            }else if(scholarship.getOneApproval().equals("回绝")){
                notPass++;
            }
            response.setName(student.getName());
            response.setStudentId(student.getStudentId());
            response.setType(scholarship.getType());
            response.setTime(scholarship.getTime());
            response.setKey(student.getStudentId()+"::"+scholarship.getType()+"::"+scholarship.getTime());
            resultList.add(response);
        }
        result.setResponseDtoList(resultList);
        result.setIsPassSum(isPass+"");
        result.setNoPassSum(notPass+"");
        return result;
    }

    @Override
    public TeacherDetailsRep details(TeacherDetailsDto teacherDetailsDto) {
        String[] split = teacherDetailsDto.getKey().split("::");
        Scholarship scholarship = teacherDao.selectBySIdAndType(split[0], split[1], split[2]);
        StudentApply studentApply = studentDao.selectBySApplyid(scholarship.getStudentId());
        TeacherDetailsRep result = new TeacherDetailsRep();
        BeanUtils.copyProperties(studentApply,result);
        result.setApplyType(scholarship.getType());
        result.setTime(scholarship.getTime());
        return result;
    }

    @Override
    public String approval(ApprovalDto approvalDto) {
        String[] split = approvalDto.getKey().split("::");
        teacherDao.updateOneApproval(split[0],split[1],split[2],approvalDto.getIsPass(),approvalDto.getReason());
        return "审批成功";
    }

    @Override
    public List<Student> findInf(TeacherFIndDto teacherFIndDto) {
        Teacher teacher = studentDao.selectByTid(teacherFIndDto.getAccount());
        List<Student> inf = teacherDao.findInf(teacherFIndDto.getStudentId(), teacherFIndDto.getName(), teacher.getMajor());
        return inf;
    }
}
