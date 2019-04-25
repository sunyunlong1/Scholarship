package com.scholarship.demo.service;

import com.scholarship.demo.api.AdminDto;
import com.scholarship.demo.api.AdminDtoResponse;
import com.scholarship.demo.api.AdminSubmissionDto;
import com.scholarship.demo.api.AdminTable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AdminService {

    /**
     * 管理员发布公告
     * @param adminDto
     * @return
     */
    List<AdminTable> release(AdminDto adminDto);

    /**
     * 发布公告
     * @param adminSubmissionDto
     * @return
     */
    String submission(AdminSubmissionDto adminSubmissionDto);

    /**
     * 提醒
     * @param adminSubmissionDto
     * @return
     */
    String remind(AdminSubmissionDto adminSubmissionDto);
}
