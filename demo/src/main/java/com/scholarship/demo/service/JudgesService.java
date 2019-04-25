package com.scholarship.demo.service;

import com.scholarship.demo.api.*;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public interface JudgesService {

    /**
     * 评定专家申请书复审查询界面
     * @param judgesDto
     * @return
     */
    List<JudgesResponseDto> approvalFind(JudgesDto judgesDto);

    /**
     * 评定专家申请书复审界面
     * @param detailsDto
     * @return
     */
    JudgesDetailsRep details(JudgesDetailsDto detailsDto);

    /**
     * 评定专家复审提交结果
     * @param judgesApprovalDto
     * @return
     */
    String approval(JudgesApprovalDto judgesApprovalDto);


    /**
     * 评定专家复审不通过提交结果
     * @param judgesApprovalDto
     * @return
     */
    String notApproval(JudgesApprovalDto judgesApprovalDto);

    /**
     * 评定专家我的审批
     * @param
     * @return
     */
    List<JMyApprovalRep> myApproval();
}
