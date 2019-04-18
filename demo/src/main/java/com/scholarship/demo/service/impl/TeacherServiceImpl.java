package com.scholarship.demo.service.impl;

import com.scholarship.demo.api.*;
import com.scholarship.demo.dao.StudentDao;
import com.scholarship.demo.dao.TeacherDao;
import com.scholarship.demo.model.Scholarship;
import com.scholarship.demo.model.Student;
import com.scholarship.demo.model.Teacher;
import com.scholarship.demo.service.TeacherService;
import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    TeacherDao teacherDao;
    @Autowired
    StudentDao studentDao;

    @Override
    public TeacherResponse applyManager(TeacherDto teacherDto) {
        TeacherResponse result = new TeacherResponse();
        Teacher teacher = studentDao.selectByTid(teacherDto.getAccount());
        List<Student> students = teacherDao.selectByMajor(teacher.getMajor());
        List<TeacherResponseDto> resultList = new ArrayList<>();
        int isPass = 0;
        int notPass = 0;
        for(Student student : students){
            TeacherResponseDto response = new TeacherResponseDto();
            Scholarship scholarship = teacherDao.selectBySIdAndType(student.getStudentId(), teacherDto.getType(),teacherDto.getYear());
            if(scholarship.getOneApproval().equals("通过")){
                isPass++;
            }else if(scholarship.getOneApproval().equals("回绝")){
                notPass++;
            }
            response.setName(student.getName());
            response.setStudentId(student.getStudentId());
            response.setType(scholarship.getType());
            response.setTime(scholarship.getTime());
            resultList.add(response);
        }
        result.setResponseDtoList(resultList);
        result.setIsPassSum(isPass+"");
        result.setNoPassSum(notPass+"");
        return result;
    }

    @Override
    public TeacherDetailsRep details(TeacherDetailsDto teacherDetailsDto) {
        Scholarship scholarship = teacherDao.selectBySIdAndType(teacherDetailsDto.getStudentId(), teacherDetailsDto.getType(), teacherDetailsDto.getTime());
        Student student = studentDao.selectBySid(scholarship.getStudentId());
        TeacherDetailsRep result = new TeacherDetailsRep();
        BeanUtils.copyProperties(student,result);
        result.setApplyType(scholarship.getType());
        result.setTime(scholarship.getTime());
        return result;
    }

    @Override
    public String approval(ApprovalDto approvalDto) {
        teacherDao.updateOneApproval(approvalDto.getStudentId(),approvalDto.getApplyType(),approvalDto.getTime(),approvalDto.getIsPass(),approvalDto.getReason());
        return "审批成功";
    }

    @Override
    public List<Student> findInf(TeacherFIndDto teacherFIndDto) {
        Teacher teacher = studentDao.selectByTid(teacherFIndDto.getAccount());
        List<Student> inf = teacherDao.findInf(teacherFIndDto.getStudentId(), teacherFIndDto.getName(), teacher.getMajor());
        for (Student student : inf){
            student.setPassword("");
        }
        return inf;
    }
}
