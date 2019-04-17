package com.scholarship.demo.controller;

import com.alibaba.fastjson.JSON;
import com.scholarship.demo.api.ChangeDto;
import com.scholarship.demo.api.LoginDto;
import com.scholarship.demo.api.LoginResponse;
import com.scholarship.demo.model.Result;
import com.scholarship.demo.service.LoginService;
import com.scholarship.demo.service.StudentService;
import com.scholarship.demo.service.UtilsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * 统一登陆
 */
@Controller
public class LoginController {

    @Autowired
    StudentService studentService;
    @Autowired
    UtilsService utilsService;
    @Autowired
    LoginService loginService;

    @RequestMapping("/login")
    @ResponseBody
    public String login(@RequestBody LoginDto loginDto, HttpServletRequest httpRequest, HttpServletResponse response){

        Cookie cookie = null;
        if(loginDto.getRole().equals("学生")){
            cookie = new Cookie("t_student","student");
        }else if(loginDto.getRole().equals("老师")){
            cookie = new Cookie("t_teacher","teacher");
        }else if(loginDto.getRole().equals("评委")){
            cookie = new Cookie("t_judges","judges");
        }else if(loginDto.getRole().equals("管理员")){
            cookie = new Cookie("t_manager","manager");
        }
        cookie.setMaxAge(60*60*24);
        response.addCookie(cookie);
        LoginResponse login = studentService.login(loginDto);
        if(login != null || !login.getUserName().equals("")){
            String login_ticket = login.getUserType();
            HttpSession session = httpRequest.getSession();
            session.setMaxInactiveInterval(60*60*24);
            if(login_ticket!=null){
                session.setAttribute(login_ticket,login);
            }else{
                session.setAttribute("123",login);
            }
            return JSON.toJSONString(new Result(200,"登陆成功",login));
        }else{
            return JSON.toJSONString(new Result(405,"登陆失败",""));
        }
    }

    @RequestMapping("/checkLogin")
    @ResponseBody
    public String checkLogin(HttpServletRequest httpRequest){
        List<String> strings = ReadCookieMap(httpRequest);
        String login_ticket = "";
        for (String cookieName :strings){
            if (cookieName.contains("t_")){
                login_ticket = cookieName;
            }
        }
        HttpSession session = httpRequest.getSession();
        LoginResponse loginResponse = (LoginResponse) session.getAttribute(login_ticket);
        if(loginResponse!=null){
            return JSON.toJSONString(new Result(200,"-",loginResponse));
        }else{
            return JSON.toJSONString(new Result(405,"session过期,请重新登陆","-"));
        }
    }

    @RequestMapping("/exit")
    @ResponseBody
    public String exit(@RequestParam LoginDto loginDto, HttpServletRequest request,HttpServletResponse response){
        Cookie[] cookies = request.getCookies();
        for(Cookie subCookie : cookies){
            if(subCookie.getName().contains("t_")){
                subCookie.setMaxAge(0);
                subCookie.setPath("/");
                response.addCookie(subCookie);
            }
        }
        return JSON.toJSONString(new Result(200,"成功退出","-"));
    }
    /**
     * 将cookie封装到Map里面
     *
     * @param request
     * @return
     */
    private static List<String> ReadCookieMap(HttpServletRequest request) {
        List<String>  cookieName = new ArrayList<>();

        Cookie[] cookies = request.getCookies();
        if (null != cookies) {
            for (Cookie cookie : cookies) {
                cookieName.add(cookie.getName());
            }
        }
        return cookieName;
    }


    @RequestMapping("/changePassword")
    @ResponseBody
    public String changePassword(@RequestBody ChangeDto changeDto){
        String s = utilsService.changePassword(changeDto);
        return JSON.toJSONString(new Result(200,"-",s));
    }

    @RequestMapping("/sendCode")
    @ResponseBody
    public String sendCode(@RequestBody LoginDto loginDto){
        String s = loginService.sendCode(loginDto);
        return JSON.toJSONString(new Result(200,"-",s));
    }
}
