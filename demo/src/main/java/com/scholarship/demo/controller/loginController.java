package com.scholarship.demo.controller;

import com.scholarship.demo.model.Result;
import com.scholarship.demo.model.Student;
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
public class loginController {

    @Autowired
    StudentApplyService service;

    @RequestMapping("/login")
    @ResponseBody
    public String login(@RequestBody Student student, HttpRequest httpRequest){
        Map<String, String> readCookieMap = ReadCookieMap((HttpServletRequest) httpRequest);
        String login_ticket = readCookieMap.get("login_ticket");
        HttpSession session = ((HttpServletRequest) httpRequest).getSession();
        session.setAttribute(login_ticket, student.toString());
        return new Result(200,"-", student.toString()).toString();
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
