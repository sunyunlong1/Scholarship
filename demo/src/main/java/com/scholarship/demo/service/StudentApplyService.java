package com.scholarship.demo.service;

import com.scholarship.demo.api.ApplyDto;
import com.scholarship.demo.api.LoginDto;
import com.scholarship.demo.api.LoginResponse;
import com.scholarship.demo.api.OnlineDto;
import com.scholarship.demo.model.Student;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StudentApplyService {

    /**
     * 统一登陆管理
     * @param loginDto
     * @return
     */
    LoginResponse login(LoginDto loginDto);
    /**
     * 在线申请类型下拉框接口
     * @return
     */
    List<String> applyType();

    /**
     * 在线申请接口
     * @param onlineDto
     * @return
     */
    String onlineApply(OnlineDto onlineDto);

}
