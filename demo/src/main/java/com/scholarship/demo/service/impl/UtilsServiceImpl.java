package com.scholarship.demo.service.impl;

import com.scholarship.demo.api.ChangeDto;
import com.scholarship.demo.dao.StudentApplyDao;
import com.scholarship.demo.model.Student;
import com.scholarship.demo.service.UtilsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UtilsServiceImpl implements UtilsService {

    @Autowired
    StudentApplyDao studentDao;

    @Override
    public String changePassword(ChangeDto changeDto) {
        if(changeDto.getRole().equals("学生")){
            Student student = studentDao.selectBySid(changeDto.getAccount());
            if(student.getPassword().equals(changeDto.getOldPassword())){

            }else{
                return "旧密码输入错误";
            }
        }else if(changeDto.getRole().equals("老师")){

        }else if(changeDto.getRole().equals("评委")){

        }else if(changeDto.getRole().equals("管理员")){

        }

        return null;
    }
}
