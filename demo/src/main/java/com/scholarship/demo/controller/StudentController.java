package com.scholarship.demo.controller;

import com.alibaba.fastjson.JSON;
import com.scholarship.demo.api.LoginDto;
import com.scholarship.demo.api.MyApply;
import com.scholarship.demo.api.OnlineDto;
import com.scholarship.demo.model.Result;
import com.scholarship.demo.model.Student;
import com.scholarship.demo.service.StudentApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentApplyService service;

    @RequestMapping("/applyType")
    @ResponseBody
    public String applyType(HttpServletRequest request){
        List<String> resultList = service.applyType();
        return JSON.toJSONString(new Result(200,"-",resultList));
    }

    @RequestMapping("/onlineApply")
    @ResponseBody
    public String onlineApply(@RequestBody OnlineDto onlineDto){
        String s = service.onlineApply(onlineDto);
        return JSON.toJSONString(new Result(200,"-",s));
    }

    @RequestMapping("/myApply")
    @ResponseBody
    public String myApply(@RequestBody LoginDto loginDto){
        List<MyApply> myApplies = service.myApply(loginDto.getAccount());
        return JSON.toJSONString(new Result(200,"-",myApplies));
    }

    @RequestMapping("/edit")
    @ResponseBody
    public String edit(@RequestBody MyApply myApply){
        OnlineDto onlineDto = service.edit(myApply.getName(), myApply.getApplyType());
        return JSON.toJSONString(new Result(200,"-",onlineDto));
    }
}
