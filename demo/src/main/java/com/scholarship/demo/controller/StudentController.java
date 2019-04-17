package com.scholarship.demo.controller;

import com.alibaba.fastjson.JSON;
import com.scholarship.demo.api.*;
import com.scholarship.demo.model.Result;
import com.scholarship.demo.service.StudentService;
import com.scholarship.demo.service.UtilsService;
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
    StudentService service;
    @Autowired
    UtilsService utilsService;

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
        OnlineDto onlineDto = service.edit(myApply.getName(), myApply.getApplyType(),myApply.getYear());
        return JSON.toJSONString(new Result(200,"-",onlineDto));
    }

    @RequestMapping("/scoreQuery")
    @ResponseBody
    public String scoreQuery(@RequestBody LoginDto loginDto){
        List<ScoreQueryResponse> response = service.scoreQuery(loginDto);
        return JSON.toJSONString(new Result(200,"-",response));
    }


}
