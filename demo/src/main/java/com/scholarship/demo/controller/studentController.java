package com.scholarship.demo.controller;

import com.scholarship.demo.model.Result;
import com.scholarship.demo.model.Student;
import com.scholarship.demo.service.StudentApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/student")
public class studentController {

    @Autowired
    private StudentApplyService service;

    @RequestMapping("/apply")
    @ResponseBody
    public String apply(@RequestBody Student student){
        Integer apply = service.apply(student);
        if(apply>0){
            return new Result(200,"-",apply.toString()).toString();
        }else{
            return new Result(405,"提交失败","-").toString();
        }
    }
}
