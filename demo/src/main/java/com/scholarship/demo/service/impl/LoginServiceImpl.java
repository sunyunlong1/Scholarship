package com.scholarship.demo.service.impl;

import com.scholarship.demo.api.ChangeDto;
import com.scholarship.demo.api.LoginDto;
import com.scholarship.demo.api.LoginResponse;
import com.scholarship.demo.dao.StudentDao;
import com.scholarship.demo.model.Admin;
import com.scholarship.demo.model.Judges;
import com.scholarship.demo.model.Student;
import com.scholarship.demo.model.Teacher;
import com.scholarship.demo.service.LoginService;
import com.scholarship.demo.service.UtilsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class LoginServiceImpl implements LoginService {

    @Autowired
    StudentDao studentDao;
    @Autowired
    UtilsService utilsService;

    @Override
    public String sendCode(LoginDto loginDto) {
        if (loginDto.getRole().equals("学生")) {
            Student student = studentDao.selectBySid(loginDto.getAccount());
            String s = utilsService.sendMail(student.getEmail(), "验证码");
        } else if (loginDto.getRole().equals("老师")) {
            Teacher teacher = studentDao.selectByTid(loginDto.getAccount());
            String s = utilsService.sendMail(teacher.getEmail(), "验证码");
        } else if (loginDto.getRole().equals("评委")) {
            Judges judges = studentDao.selectByJid(loginDto.getAccount());
            String s = utilsService.sendMail(judges.getEmail(), "验证码");
        } else {
            Admin admin = studentDao.selectByAid(loginDto.getAccount());
            String s = utilsService.sendMail(admin.getEmail(), "验证码");
        }
        return "发送成功";
    }
}
