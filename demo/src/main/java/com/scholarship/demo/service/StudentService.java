package com.scholarship.demo.service;

import com.scholarship.demo.api.*;
import com.scholarship.demo.model.Student;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StudentService {

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

    /**
     * 我的申请查询接口
     * @param studentId
     * @return
     */
    List<MyApply> myApply(String studentId);

    /**
     * 编辑接口
     * @param name
     * @return
     */
    OnlineDto edit(String name,String applyType,String year);

    /**
     * 成绩查询
     * @param loginDto
     * @return
     */
    List<ScoreQueryResponse> scoreQuery(LoginDto loginDto);

}
