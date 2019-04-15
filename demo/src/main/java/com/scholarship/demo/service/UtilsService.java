package com.scholarship.demo.service;

import com.scholarship.demo.api.ChangeDto;
import com.scholarship.demo.api.LoginDto;
import org.springframework.stereotype.Service;

@Service
public interface UtilsService {

    /**
     * 修改密码
     * @param changeDto
     * @return
     */
    String changePassword(ChangeDto changeDto);
}
