package com.scholarship.demo.controller;

import com.alibaba.fastjson.JSON;
import com.scholarship.demo.api.OnlineDto;
import com.scholarship.demo.model.Result;
import com.scholarship.demo.model.Student;
import com.scholarship.demo.service.StudentApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/student")
public class studentController {

    @Autowired
    private StudentApplyService service;

    @RequestMapping("/applyType")
    @ResponseBody
    public String applyType(){
        List<String> resultList = service.applyType();
        return JSON.toJSONString(new Result(200,"-",resultList));
    }

    @RequestMapping("/onlineApply")
    @ResponseBody
    public String onlineApply(OnlineDto onlineDto){
        return "";
    }
}
