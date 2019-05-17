package com.scholarship.demo.service;

import com.scholarship.demo.api.ChangeDto;
import com.scholarship.demo.api.LoginDto;
import org.springframework.stereotype.Service;

@Service
public interface LoginService {

    /**
     * 发送验证码
     * @param loginDto
     * @return
     */
    String sendCode(LoginDto loginDto);

    /**
     * 上传
     * @return
     */
    String upload();

    /**
     * 下载
     * @param path
     * @return
     */
    String download(String path);
}
