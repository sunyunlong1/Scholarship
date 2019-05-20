package com.scholarship.demo.service.impl;

import com.scholarship.demo.api.AdminDto;
import com.scholarship.demo.api.AdminSubmissionDto;
import com.scholarship.demo.api.AdminTable;
import com.scholarship.demo.dao.AdminDao;
import com.scholarship.demo.model.Grade;
import com.scholarship.demo.model.Judges;
import com.scholarship.demo.model.Scholarship;
import com.scholarship.demo.model.Student;
import com.scholarship.demo.service.AdminService;
import com.scholarship.demo.service.UtilsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Repository
public class AdminServiceImpl implements AdminService {

    @Autowired
    AdminDao adminDao;
    @Autowired
    UtilsService utilsService;

    @Override
    public List<AdminTable> release(AdminDto adminDto) {

        SimpleDateFormat df2 = new SimpleDateFormat(("yyyy"));
        String year = df2.format(new Date());
        // AdminDtoResponse result = new AdminDtoResponse();
        List<AdminTable> adminTables = new ArrayList<>();
        Integer integer = adminDao.selectSum(adminDto.getType(), year, "","初审通过");
        Integer sum = adminDao.selectALL(adminDto.getType(), year,"初审通过");

        AdminTable adminTable = new AdminTable();
        adminTable.setState("已审批");
        adminTable.setNum(integer == 0 ? "0" : integer.toString());
        adminTable.setSum(sum == 0 ? "0" : sum.toString());
        adminTable.setYear(year);
        if (adminDto.getType().equals("01")) {
            adminTable.setType("一等奖学金");
        } else if (adminDto.getType().equals("02")) {
            adminTable.setType("二等奖学金");
        } else if (adminDto.getType().equals("03")) {
            adminTable.setType("三等奖学金");
        } else if (adminDto.getType().equals("04")) {
            adminTable.setType("国家励志奖学金");
        } else if (adminDto.getType().equals("05")) {
            adminTable.setType("国家助学金");
        }
        adminTable.setKey(adminDto.getType() + "::" + year);
        Integer sumno = adminDao.selectNotSum(adminDto.getType(), year, "","初审通过");
        AdminTable table = new AdminTable();
        table.setState("未审批");
        table.setNum(sumno == 0 ? "0" : sumno.toString());
        table.setSum(sum == 0 ? "0" : sum.toString());
        table.setYear(year);
        if (adminDto.getType().equals("01")) {
            table.setType("一等奖学金");
        } else if (adminDto.getType().equals("02")) {
            table.setType("二等奖学金");
        } else if (adminDto.getType().equals("03")) {
            table.setType("三等奖学金");
        } else if (adminDto.getType().equals("04")) {
            table.setType("国家励志奖学金");
        } else if (adminDto.getType().equals("05")) {
            table.setType("国家助学金");
        }
        table.setKey(adminDto.getType() + "::" + year);
        adminTables.add(adminTable);
        adminTables.add(table);

//        result.setAdminTableList(adminTables);
        Integer total = integer + sum;
        return adminTables;
    }

    @Override
    public String submission(AdminSubmissionDto adminSubmissionDto) {
        String[] split = adminSubmissionDto.getKey().split("::");
        Integer sum = adminDao.selectSum(split[0], split[1], "","初审通过");
            List<Scholarship> scholarshipList = adminDao.selectByKey(split[0], split[1], "初审通过", "");
            Collections.sort(scholarshipList);
            //int length = scholarshipList.size()>=5 ? 5 : scholarshipList.size();
            if (scholarshipList.size()<=5){
                for (int i = 0; i < scholarshipList.size(); i++) {
                    adminDao.UpdateSTwoApproval(scholarshipList.get(i).getStudentId(),scholarshipList.get(i).getType(),scholarshipList.get(i).getTime(),"复审通过");
                }
            }else{
                for (int i = 5; i < scholarshipList.size() ; i++) {
                    adminDao.UpdateSTwoApproval(scholarshipList.get(i).getStudentId(),scholarshipList.get(i).getType(),scholarshipList.get(i).getTime(),"复审未通过");
                }
            }
            List<Scholarship> scholarships = adminDao.selectByTAndY(split[0], split[1],"");
            for (Scholarship scholarship : scholarships) {
                Student student = adminDao.selectByAccount(scholarship.getStudentId());
                String s = utilsService.sendMail(student.getEmail(), "发布");
                if (s.equals("未连接到互联网，请稍后重试!")){
                    return s;
                }
            }
            return "发送成功";
        }


    @Override
    public String remind(AdminSubmissionDto adminSubmissionDto) {
        String[] split = adminSubmissionDto.getKey().split("::");
        String type = split[0];
        String year = split[1];
        List<Scholarship> scholarshipList = adminDao.selectByKey2(split[0], split[1], "初审通过", "");
        if (scholarshipList ==  null || scholarshipList.size() == 0){
            return "发送失败";
        }
        for (Scholarship scholarship : scholarshipList){
            Grade grade = adminDao.selectByGradeKey(scholarship.getStudentId(), scholarship.getType(), scholarship.getTime());
            String number = "";
            if (grade.getOneGrade().equals("")){
                number = "one";
                Judges judges = adminDao.selectByNumber(number);
                if (judges!=null){
                    String s = utilsService.sendMail(judges.getEmail(), "提醒");
                }
            }
            if(grade.getTwoGrade().equals("")){
                number = "two";
                Judges judges = adminDao.selectByNumber(number);
                if (judges!=null){
                    String s = utilsService.sendMail(judges.getEmail(), "提醒");
                }
            }
            if(grade.getThreeGrade().equals("")){
                number = "three";
                Judges judges = adminDao.selectByNumber(number);
                if (judges!=null){
                    String s = utilsService.sendMail(judges.getEmail(), "提醒");
                }
            }
            if(grade.getFourGrade().equals("")){
                number = "four";
                Judges judges = adminDao.selectByNumber(number);
                if (judges!=null){
                    String s = utilsService.sendMail(judges.getEmail(), "提醒");
                }
            }
            if(grade.getFiveGrade().equals("")){
                number = "five";
                Judges judges = adminDao.selectByNumber(number);
                if (judges!=null){
                    String s = utilsService.sendMail(judges.getEmail(), "提醒");
                }
            }

        }
        return "发送成功";
    }
}
