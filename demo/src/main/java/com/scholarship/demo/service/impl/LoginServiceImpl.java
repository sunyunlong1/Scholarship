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
        String s = "";
        if (loginDto.getRole().equals("student")) {
            Student student = studentDao.selectBySid(loginDto.getAccount());
             s = utilsService.sendMail(student.getEmail(), "验证码");
        } else if (loginDto.getRole().equals("teacher")) {
            Teacher teacher = studentDao.selectByTid(loginDto.getAccount());
             s = utilsService.sendMail(teacher.getEmail(), "验证码");
        } else if (loginDto.getRole().equals("judges")) {
            Judges judges = studentDao.selectByJid(loginDto.getAccount());
             s = utilsService.sendMail(judges.getEmail(), "验证码");
        } else {
            Admin admin = studentDao.selectByAid(loginDto.getAccount());
             s = utilsService.sendMail(admin.getEmail(), "验证码");
        }
        return s;
    }

    @Override
    public String upload() {
        return null;
    }

    @Override
    public String download(String path) {
        return null;
    }
}
