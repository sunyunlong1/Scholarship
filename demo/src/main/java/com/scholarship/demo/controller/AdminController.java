package com.scholarship.demo.controller;

import com.alibaba.fastjson.JSON;
import com.scholarship.demo.api.AdminDto;
import com.scholarship.demo.api.AdminDtoResponse;
import com.scholarship.demo.api.AdminSubmissionDto;
import com.scholarship.demo.api.AdminTable;
import com.scholarship.demo.model.Result;
import com.scholarship.demo.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@RequestMapping("/admin")
@Controller
public class AdminController {

    @Autowired
    AdminService adminService;

    @RequestMapping("/release")
    @ResponseBody
    public String release(@RequestBody AdminDto adminDto){
        List<AdminTable> release = adminService.release(adminDto);
        return JSON.toJSONString(new Result(200,"-",release));
    }

    @RequestMapping("/submission")
    @ResponseBody
    public String submission(@RequestBody AdminSubmissionDto adminSubmissionDto){
        String result = adminService.submission(adminSubmissionDto);
        return JSON.toJSONString(new Result(200,"-",result));
    }

    @RequestMapping("/remind")
    @ResponseBody
    public String remind(@RequestBody AdminSubmissionDto adminSubmissionDto){
        String result = adminService.remind(adminSubmissionDto);
        return JSON.toJSONString(new Result(200,"-",result));
    }
}
