package com.scholarship.demo.controller;

import com.alibaba.fastjson.JSON;
import com.scholarship.demo.api.LoginDto;
import com.scholarship.demo.api.MyApply;
import com.scholarship.demo.api.OnlineDto;
import com.scholarship.demo.api.ScoreQueryResponse;
import com.scholarship.demo.model.Result;
import com.scholarship.demo.service.StudentService;
import com.scholarship.demo.service.UtilsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentService studentService;

    @Autowired
    UtilsService utilsService;

    @RequestMapping("/applyType")
    @ResponseBody
    public String applyType(){
        List<String> resultList = studentService.applyType();
        if(resultList != null){
            return JSON.toJSONString(new Result(200,"-",resultList));
        }else{
            return JSON.toJSONString(new Result(405,"没有数据",resultList));
        }
    }

    @RequestMapping("/onlineApply")
    @ResponseBody
    public String onlineApply(@RequestBody OnlineDto onlineDto){
        String s = studentService.onlineApply(onlineDto);
        return JSON.toJSONString(new Result(200,"-",s));
    }

    @RequestMapping("/myApply")
    @ResponseBody
    public String myApply(@RequestBody LoginDto loginDto){
        List<MyApply> myApplies = studentService.myApply(loginDto.getAccount());
        return JSON.toJSONString(new Result(200,"-",myApplies));
    }

    @RequestMapping("/edit")
    @ResponseBody
    public String edit(@RequestBody MyApply myApply){
        OnlineDto onlineDto = studentService.edit(myApply.getKey());
        return JSON.toJSONString(new Result(200,"-",onlineDto));
    }

    @RequestMapping("/scoreQuery")
    @ResponseBody
    public String scoreQuery(@RequestBody LoginDto loginDto){
        List<ScoreQueryResponse> response = studentService.scoreQuery(loginDto);
        return JSON.toJSONString(new Result(200,"-",response));
    }


}
