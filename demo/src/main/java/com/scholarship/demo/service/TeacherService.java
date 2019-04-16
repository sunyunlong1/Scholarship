package com.scholarship.demo.service;

import com.scholarship.demo.api.*;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public interface TeacherService {

    /**
     * 辅导员申请书管理查询界面
     */
    List<TeacherResponse> applyManager(TeacherDto teacher);

    /**
     * 辅导员申请书管理查看详情
     * @param teacherDetailsDto
     * @return
     */
    TeacherDetailsRep details(TeacherDetailsDto teacherDetailsDto);

    /**
     * 辅导员审批
     * @param approvalDto
     * @return
     */
    String approval(ApprovalDto approvalDto);
}
