package com.scholarship.demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.scholarship.demo.api.*;
import com.scholarship.demo.model.Judges;
import com.scholarship.demo.model.Result;
import com.scholarship.demo.service.JudgesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/judges")
public class JudgesController {

    @Autowired
    JudgesService judgesService;

    @RequestMapping("/approvalFind")
    @ResponseBody
    public String approval(@RequestBody JudgesDto judgesDto){
        List<JudgesResponseDto> result = judgesService.approvalFind(judgesDto);
        return JSON.toJSONString(new Result(200,"-",result));
    }

    @RequestMapping("/details")
    @ResponseBody
    public String details(@RequestBody JudgesDetailsDto  detailsDto){
        JudgesDetailsRep result = judgesService.details(detailsDto);
        return JSON.toJSONString(new Result(200,"-",result));
    }

    @RequestMapping("/approval")
    @ResponseBody
    public String approval(@RequestBody JudgesApprovalDto judgesApprovalDto){
        String result = judgesService.approval(judgesApprovalDto);
        return JSON.toJSONString(new Result(200,"-",result));
    }

    @RequestMapping("/notApproval")
    @ResponseBody
    public String notApproval(@RequestBody JudgesApprovalDto judgesApprovalDto){
        String result = judgesService.notApproval(judgesApprovalDto);
        return JSON.toJSONString(new Result(200,"-",result));
    }

    @RequestMapping("/myApproval")
    @ResponseBody
    public String myApproval(){
        List<JMyApprovalRep> result = judgesService.myApproval();
        if (result == null){
            return JSON.toJSONString(new Result(200,"-","当前没有已审批记录"));
        }else{
            return JSON.toJSONString(new Result(200,"-",result));

        }
    }
}
