package com.scholarship.demo.service.impl;

import com.scholarship.demo.api.ChangeDto;
import com.scholarship.demo.dao.StudentDao;
import com.scholarship.demo.model.Admin;
import com.scholarship.demo.model.Judges;
import com.scholarship.demo.model.Student;
import com.scholarship.demo.model.Teacher;
import com.scholarship.demo.service.UtilsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

@Repository
public class UtilsServiceImpl implements UtilsService {

    // 发件人 账号和密码
    public static final String MY_EMAIL_ACCOUNT = "syl9798@163.com";
    public static final String MY_EMAIL_PASSWORD = "Syl13124556738";// 密码,是你自己的设置的授权码

    // SMTP服务器(这里用的163 SMTP服务器)
    public static final String MEAIL_163_SMTP_HOST = "smtp.163.com";
    public static final String SMTP_163_PORT = "25";// 端口号,这个是163使用到的;QQ的应该是465或者875


    @Autowired
    StudentDao studentDao;

    @Override
    public String changePassword(ChangeDto changeDto) {

        if (changeDto.getRole().equals("student")) {
            Student student = studentDao.selectBySid(changeDto.getAccount());
            if (student.getPassword().equals(changeDto.getOldPassword())) {
                studentDao.updateSPassword(changeDto.getAccount(), changeDto.getNewPassword());
            } else {
                return "旧密码输入错误";
            }
        } else if (changeDto.getRole().equals("teacher")) {
            Teacher teacher = studentDao.selectByTid(changeDto.getAccount());
            if (teacher.getPassword().equals(changeDto.getOldPassword())) {
                studentDao.updateTPassword(changeDto.getAccount(), changeDto.getNewPassword());
            } else {
                return "旧密码输入错误";
            }
        } else if (changeDto.getRole().equals("judges")) {
            Judges judges = studentDao.selectByJid(changeDto.getAccount());
            if (judges.getPassword().equals(changeDto.getOldPassword())) {
                studentDao.updateJPassword(changeDto.getAccount(), changeDto.getNewPassword());
            } else {
                return "旧密码输入错误";
            }
        } else if (changeDto.getRole().equals("manager")) {
            Admin admin = studentDao.selectByAid(changeDto.getAccount());
            if (admin.getPassword().equals(changeDto.getOldPassword())) {
                studentDao.updateAPassword(changeDto.getAccount(), changeDto.getNewPassword());
            } else {
                return "旧密码输入错误";
            }
        }
        return "修改密码成功";
    }

    @Override
    public String sendMail(String email, String content) {
        Properties p = new Properties();
        p.setProperty("mail.smtp.host", MEAIL_163_SMTP_HOST);
        p.setProperty("mail.smtp.port", SMTP_163_PORT);
        p.setProperty("mail.smtp.socketFactory.port", SMTP_163_PORT);
        p.setProperty("mail.smtp.auth", "true");
        p.setProperty("mail.smtp.socketFactory.class", "SSL_FACTORY");
        String newRandomCode = "";
        Session session = Session.getInstance(p, new Authenticator() {
            // 设置认证账户信息
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(MY_EMAIL_ACCOUNT, MY_EMAIL_PASSWORD);
            }
        });
        session.setDebug(true);
        MimeMessage message = new MimeMessage(session);
        // 发件人
        try {
            message.setFrom(new InternetAddress(MY_EMAIL_ACCOUNT));
            // 收件人和抄送人
            message.setRecipients(Message.RecipientType.TO, email);
//		message.setRecipients(Message.RecipientType.CC, MY_EMAIL_ACCOUNT);
            //newRandomCode = getNewRandomCode(6);
            newRandomCode = getNewRandomCode(6);
            // 内容(这个内容还不能乱写,有可能会被SMTP拒绝掉;多试几次吧)
            if (content.equals("验证码")) {
                message.setSubject("东北农业大学奖学金评分系统密码修改");
                message.setContent("<h1>" + "您的验证码为：" + newRandomCode + "请勿告诉他人" + "</h1>", "text/html;charset=UTF-8");
            } else if (content.equals("发布")) {
                message.setSubject("东北农业大学奖学金评分系统成绩查询提醒");
                message.setContent("<h1>" + "同学您好，您的奖学金评分结果已经公布，请去官网查看！" + "</h1>", "text/html;charset=UTF-8");
            } else if (content.equals("提醒")) {
                message.setSubject("东北农业大学奖学金评分系统评分提醒");
                message.setContent("<h1>" + "评委老师您好，您还有待审批的记录，请尽快审批，谢谢！" + "</h1>", "text/html;charset=UTF-8");
            }
            message.setSentDate(new Date());
            message.saveChanges();
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return newRandomCode;
    }

    public static String getNewRandomCode(int codeLen) {
        java.util.Random randomCode = new java.util.Random();
        String strCode = "";
        while (codeLen > 0) {
            int charCode = randomCode.nextInt(9);
            strCode += charCode;
            codeLen--;
        }
        return strCode;
    }
}

