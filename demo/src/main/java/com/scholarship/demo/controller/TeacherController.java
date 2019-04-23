package com.scholarship.demo.controller;

import com.alibaba.fastjson.JSON;
import com.scholarship.demo.api.*;
import com.scholarship.demo.model.Result;
import com.scholarship.demo.model.Student;
import com.scholarship.demo.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@RequestMapping("/teacher")
@Controller
public class TeacherController {

    @Autowired
    TeacherService teacherService;

    @RequestMapping("/applyManager")
    @ResponseBody
    public String applyManager(@RequestBody TeacherDto teacher){
        TeacherResponse result = teacherService.applyManager(teacher);
        return JSON.toJSONString(new Result(200,"-",result));
    }

    @RequestMapping("/details")
    @ResponseBody
    public String details(@RequestBody TeacherDetailsDto teacherDetailsDto){
        TeacherDetailsRep details = teacherService.details(teacherDetailsDto);
        return JSON.toJSONString(new Result(200,"-",details));
    }

    @RequestMapping("/approval")
    @ResponseBody
    public String approval(@RequestBody ApprovalDto approvalDto){
        String result = teacherService.approval(approvalDto);
        return JSON.toJSONString(new Result(200,"-",result));
    }

    @RequestMapping("/findInf")
    @ResponseBody
    public String findInf(@RequestBody TeacherFIndDto teacherFIndDto){
        List<Student> inf = teacherService.findInf(teacherFIndDto);
        return JSON.toJSONString(new Result(200,"-",inf));
    }
}
