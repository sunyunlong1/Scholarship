package com.scholarship.demo.controller;

import com.alibaba.fastjson.JSON;
import com.scholarship.demo.api.LoginDto;
import com.scholarship.demo.api.LoginResponse;
import com.scholarship.demo.model.Result;
import com.scholarship.demo.service.StudentApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * 统一登陆
 */
@Controller
public class LoginController {

    @Autowired
    StudentApplyService service;

    @RequestMapping("/login")
    @ResponseBody
    public String login(@RequestBody LoginDto loginDto, HttpRequest httpRequest){

        LoginResponse login =
                service.login(loginDto);
        if(login !=null || !login.getUserName().equals("")){
            Map<String, String> readCookieMap = ReadCookieMap((HttpServletRequest) httpRequest);
            String login_ticket = readCookieMap.get("login_ticket");
            HttpSession session = ((HttpServletRequest) httpRequest).getSession();
            session.setAttribute(login_ticket, loginDto.toString());
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
    public String checkLogin(HttpRequest httpRequest){
        Map<String, String> readCookieMap = ReadCookieMap((HttpServletRequest) httpRequest);
        String login_ticket = readCookieMap.get("login_ticket");
        HttpSession session = ((HttpServletRequest) httpRequest).getSession();
        String attribute = (String) session.getAttribute(login_ticket);
        if(attribute!=null){
            return new Result(200,"-",attribute).toString();
        }else{
            return new Result(400,"session过期,请重新登陆","-").toString();
        }
    }
    /**
     * 将cookie封装到Map里面
     *
     * @param request
     * @return
     */
    private static Map<String, String> ReadCookieMap(HttpServletRequest request) {
        Map<String, String> cookieMap = new HashMap<String, String>();
        Cookie[] cookies = request.getCookies();
        if (null != cookies) {
            for (Cookie cookie : cookies) {
                cookieMap.put(cookie.getName(), cookie.getValue());
            }
        }
        return cookieMap;
    }

}
