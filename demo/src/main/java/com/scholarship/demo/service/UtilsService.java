package com.scholarship.demo.service;

import com.scholarship.demo.api.ChangeDto;
import org.springframework.stereotype.Service;

@Service
public interface UtilsService {

    /**
     * 修改密码
     * @param changeDto
     * @return
     */
    String changePassword(ChangeDto changeDto);

    /**
     * 发送邮件
     * @param email
     * @param content
     * @return
     */
    String sendMail(String email,String content);
}
