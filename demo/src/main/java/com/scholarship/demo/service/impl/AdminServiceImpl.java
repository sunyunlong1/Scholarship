package com.scholarship.demo.service.impl;

import com.scholarship.demo.api.AdminDto;
import com.scholarship.demo.api.AdminDtoResponse;
import com.scholarship.demo.api.AdminSubmissionDto;
import com.scholarship.demo.api.AdminTable;
import com.scholarship.demo.dao.AdminDao;
import com.scholarship.demo.model.Judges;
import com.scholarship.demo.model.Scholarship;
import com.scholarship.demo.model.Student;
import com.scholarship.demo.service.AdminService;
import com.scholarship.demo.service.UtilsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class AdminServiceImpl implements AdminService {

    @Autowired
    AdminDao adminDao;
    @Autowired
    UtilsService utilsService;

    @Override
    public AdminDtoResponse release(AdminDto adminDto) {

        SimpleDateFormat df2 = new SimpleDateFormat(("yyyy"));
        String year = df2.format(new Date());
        AdminDtoResponse result = new AdminDtoResponse();
        List<AdminTable> adminTables = new ArrayList<>();
        Integer integer = adminDao.selectSum(adminDto.getType(), year,"通过");
        AdminTable adminTable = new AdminTable();
        adminTable.setState("已审批");
        adminTable.setNum(integer.toString());
        adminTable.setYear(year);
        if(adminDto.getType().equals("01")){
            adminTable.setType("一等奖学金");
        }else if(adminDto.getType().equals("02")){
            adminTable.setType("二等奖学金");
        }else if(adminDto.getType().equals("03")){
            adminTable.setType("三等奖学金");
        }else if(adminDto.getType().equals("04")){
            adminTable.setType("国家励志奖学金");
        }else if (adminDto.getType().equals("05")){
            adminTable.setType("国家助学金");
        }
        adminTable.setKey(adminDto.getType()+"::"+year);
        Integer sum = adminDao.selectSum(adminDto.getType(), year, "");
        AdminTable table = new AdminTable();
        table.setState("未审批");
        table.setNum(sum.toString());
        table.setYear(year);
        if(adminDto.getType().equals("01")){
            table.setType("一等奖学金");
        }else if(adminDto.getType().equals("02")){
            table.setType("二等奖学金");
        }else if(adminDto.getType().equals("03")){
            table.setType("三等奖学金");
        }else if(adminDto.getType().equals("04")){
            table.setType("国家励志奖学金");
        }else if (adminDto.getType().equals("05")){
            table.setType("国家助学金");
        }
        table.setKey(adminDto.getType()+"::"+year);
        adminTables.add(adminTable);
        adminTables.add(table);

        result.setAdminTableList(adminTables);
        Integer total = integer+sum;
        result.setSum(total.toString());
        return result;
    }

    @Override
    public String submission(AdminSubmissionDto adminSubmissionDto) {
        String[] split = adminSubmissionDto.getKey().split("::");
        Integer sum = adminDao.selectSum(split[0], split[1], "");
        if(sum>0){
            return "还有未评价的记录，请提醒评委老师尽快评价";
        }else{
            List<Scholarship> scholarships = adminDao.selectByTAndY(split[0], split[1]);
            for (Scholarship scholarship : scholarships){
                Student student = adminDao.selectByAccount(scholarship.getStudentId());
                String s = utilsService.sendMail(student.getEmail(), "发布");
            }
            return "发送成功";
        }
    }

    @Override
    public String remind(AdminSubmissionDto adminSubmissionDto) {
        String[] split = adminSubmissionDto.getKey().split("::");
        List<Judges> judgesList = adminDao.selectByJAccount();
        for (Judges judges : judgesList){
            String s = utilsService.sendMail(judges.getEmail(), "提醒");
        }
        return "发送成功";
    }
}
