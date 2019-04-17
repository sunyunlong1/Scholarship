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

import java.util.ArrayList;
import java.util.List;

@Repository
public class AdminServiceImpl implements AdminService {

    @Autowired
    AdminDao adminDao;
    @Autowired
    UtilsService utilsService;

    @Override
    public AdminDtoResponse release(AdminDto adminDto) {
        AdminDtoResponse result = new AdminDtoResponse();
        List<AdminTable> adminTables = new ArrayList<>();
        Integer integer = adminDao.selectSum(adminDto.getType(), adminDto.getYear(),"已审批");
        AdminTable adminTable = new AdminTable();
        adminTable.setState("已审批");
        adminTable.setNum(integer.toString());
        adminTable.setYear(adminDto.getYear());
        Integer sum = adminDao.selectSum(adminDto.getType(), adminDto.getYear(), "");
        AdminTable table = new AdminTable();
        table.setState("未审批");
        table.setNum(sum.toString());
        table.setYear(adminDto.getYear());
        adminTables.add(adminTable);
        adminTables.add(table);

        result.setAdminTableList(adminTables);
        Integer total = integer+sum;
        result.setSum(total.toString());
        return result;
    }

    @Override
    public String submission(AdminSubmissionDto adminSubmissionDto) {
        Integer sum = adminDao.selectSum(adminSubmissionDto.getType(), adminSubmissionDto.getYear(), "");
        if(sum>0){
            return "还有未评价的记录，请提醒评委老师尽快评价";
        }else{
            List<Scholarship> scholarships = adminDao.selectByTAndY(adminSubmissionDto.getType(), adminSubmissionDto.getYear());
            for (Scholarship scholarship : scholarships){
                Student student = adminDao.selectByAccount(scholarship.getStudentId());
                String s = utilsService.sendMail(student.getEmail(), "发布");
            }
            return "发送成功";
        }
    }

    @Override
    public String remind(AdminSubmissionDto adminSubmissionDto) {
        if(adminSubmissionDto.getNum().equals("0")){
            return "已经全部审批完成，请尽快发布成绩";
        }else{
            List<Scholarship> scholarships = adminDao.selectByTAndY(adminSubmissionDto.getType(), adminSubmissionDto.getYear());
            for (Scholarship scholarship : scholarships){
                Judges judges = adminDao.selectByJAccount(scholarship.getJudgesId());
                String s = utilsService.sendMail(judges.getEmail(), "提醒");
            }
            return "发送成功";
        }
    }
}
