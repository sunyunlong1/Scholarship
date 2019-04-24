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
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = df.format(new Date());
        String endString = md5(format);

//        Cookie cookie = null;
//        if(loginDto.getRole().equals("学生")){
//            cookie = new Cookie("t_student","student");
//        }else if(loginDto.getRole().equals("老师")){
//            cookie = new Cookie("t_teacher","teacher");
//        }else if(loginDto.getRole().equals("评委")){
//            cookie = new Cookie("t_judges","judges");
//        }else if(loginDto.getRole().equals("管理员")){
//            cookie = new Cookie("t_manager","manager");
//        }
        Cookie cookie = new Cookie("login_ticket",endString);
        cookie.setMaxAge(60*60*24);
        response.addCookie(cookie);
        LoginResponse login = studentService.login(loginDto);
        if(login != null || !login.getUserName().equals("")){
            //String login_ticket = login.getUserType();
            HttpSession session = httpRequest.getSession();
            session.setMaxInactiveInterval(60*60*24);
            if(endString!=null){
                session.setAttribute(endString,login);
            }else{
                session.setAttribute("123",login);
            }
            return JSON.toJSONString(new Result(200,"登陆成功",login));
        }else{
            return JSON.toJSONString(new Result(405,"密码错误",""));
        }
    }

    @RequestMapping("/checkLogin")
    @ResponseBody
    public String checkLogin(HttpServletRequest httpRequest){
        List<String> strings = ReadCookieMap(httpRequest);
        String value ="";
        Cookie[] cookies = httpRequest.getCookies();
        for (Cookie cookie : cookies){
            if(cookie.getName().equals("login_ticket")){
                value = cookie.getValue();
            }
        }
        HttpSession session = httpRequest.getSession();
        LoginResponse loginResponse = (LoginResponse) session.getAttribute(value);
        if(loginResponse!=null){
            return JSON.toJSONString(new Result(200,"-",loginResponse));
        }else{
            return JSON.toJSONString(new Result(405,"session过期,请重新登陆","-"));
        }
    }

    @RequestMapping("/exit")
    @ResponseBody
    public String exit(HttpServletRequest request,HttpServletResponse response){
        Cookie[] cookies = request.getCookies();
        for(Cookie subCookie : cookies){
            if(subCookie.getName().equals("login_ticket")){
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


    //写一个md5加密的方法
    public static String md5(String plainText) {
        //定义一个字节数组
        byte[] secretBytes = null;
        try {
            // 生成一个MD5加密计算摘要
            MessageDigest md = MessageDigest.getInstance("MD5");
            //对字符串进行加密
            md.update(plainText.getBytes());
            //获得加密后的数据
            secretBytes = md.digest();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("没有md5这个算法！");
        }
        //将加密后的数据转换为16进制数字
        String md5code = new BigInteger(1, secretBytes).toString(16);// 16进制数字
        // 如果生成数字未满32位，需要前面补0
        for (int i = 0; i < 32 - md5code.length(); i++) {
            md5code = "0" + md5code;
        }
        return md5code;
    }
}
