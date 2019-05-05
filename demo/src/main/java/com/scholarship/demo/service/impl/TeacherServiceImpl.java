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
        if (teacher == null){
            return null;
        }
        List<Scholarship> scholarships = teacherDao.selectByMajor(teacher.getMajor(),teacherDto.getType(),year,"");
        List<TeacherResponseDto> resultList = new ArrayList<>();
        int isPass = 0;
        List<Scholarship> scholarshipList1 = teacherDao.selectByTMajor(teacher.getMajor(), teacherDto.getType(), year);
        int sum = 0;
        sum = scholarshipList1.size();
        if(scholarships != null && scholarships.size()!=0) {
            for (Scholarship scholarship : scholarships) {
                TeacherResponseDto response = new TeacherResponseDto();
                StudentApply studentApply = teacherDao.selectById(scholarship.getStudentId());
                response.setName(studentApply.getName());
                response.setStudentId(studentApply.getStudentId());
                if (scholarship.getType().equals("01")) {
                    response.setType("一等奖学金");
                } else if (scholarship.getType().equals("02")) {
                    response.setType("二等奖学金");
                } else if (scholarship.getType().equals("03")) {
                    response.setType("三等奖学金");
                } else if (scholarship.getType().equals("04")) {
                    response.setType("国家励志奖学金");
                } else if (scholarship.getType().equals("05")) {
                    response.setType("国家助学金");
                }
                response.setTime(scholarship.getTime());
                response.setKey(studentApply.getStudentId() + "::" + scholarship.getType() + "::" + scholarship.getTime());
                resultList.add(response);
            }
        }
            List<Scholarship> scholarshipList = teacherDao.selectByMajor(teacher.getMajor(),teacherDto.getType(),year,"初审通过");
            isPass = scholarshipList.size();
            result.setResponseDtoList(resultList);
            result.setIsPass(isPass+"");
            result.setSum(sum+"");

        return result;
    }

    @Override
    public TeacherDetailsRep details(TeacherDetailsDto teacherDetailsDto) {
        String[] split = teacherDetailsDto.getKey().split("::");
        TeacherDetailsRep result = new TeacherDetailsRep();
        Scholarship scholarship = teacherDao.selectBySIdAndType(split[0], split[1], split[2]);
        if (scholarship != null){
            StudentApply studentApply = studentDao.selectBySApplyid(scholarship.getStudentId());
            if (studentApply != null){
                BeanUtils.copyProperties(studentApply,result);
                result.setApplyType(scholarship.getType());
                result.setTime(scholarship.getTime());
                result.setIntroduce(scholarship.getIntroduce());
            }
        }

        return result;
    }

    @Override
    public String approval(ApprovalDto approvalDto) {
        String[] split = approvalDto.getKey().split("::");
        teacherDao.updateOneApproval(split[0],split[1],split[2],"初审通过",approvalDto.getReason());
        return "审批成功";
    }


    @Override
    public String notApproval(List<ApprovalDto> approvalDtoList) {
        if (approvalDtoList != null && approvalDtoList.size()!=0){
            for (ApprovalDto approvalDto : approvalDtoList){
                String[] split = approvalDto.getKey().split("::");
                teacherDao.updateOneApproval(split[0],split[1],split[2],"初审未通过",approvalDto.getReason());
            }
            return "审批成功";
        }else{
            return "审批失败";
        }

    }

    @Override
    public List<Student> findInf(TeacherFIndDto teacherFIndDto) {
        List<Student> inf = null;
        Teacher teacher = studentDao.selectByTid(teacherFIndDto.getAccount());
        if (teacher !=null){
            if(teacherFIndDto.getStudentId() == null || teacherFIndDto.getStudentId().equals("")){
                teacherFIndDto.setStudentId(null);
            }
            if(teacherFIndDto.getName() == null || teacherFIndDto.getName().equals("")){
                teacherFIndDto.setName(null);
            }
            if(teacherFIndDto.getCollege() == null || teacherFIndDto.getCollege().equals("")){
                teacherFIndDto.setCollege(null);
            }
           inf = teacherDao.findInf(teacherFIndDto.getStudentId(), teacherFIndDto.getName(), teacher.getMajor(),teacherFIndDto.getCollege());
        }
        return inf;
    }
}
