package com.scholarship.demo.service.impl;

import com.scholarship.demo.api.*;
import com.scholarship.demo.dao.JudgesDao;
import com.scholarship.demo.model.Grade;
import com.scholarship.demo.model.Judges;
import com.scholarship.demo.model.Scholarship;
import com.scholarship.demo.model.StudentApply;
import com.scholarship.demo.service.JudgesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.zip.DataFormatException;

@Repository
public class JudgesServiceImpl implements JudgesService {

    @Autowired
    JudgesDao judgesDao;

    @Override
    public List<JudgesResponseDto> approvalFind(JudgesDto judgesDto) {
        SimpleDateFormat df2 = new SimpleDateFormat(("yyyy"));
        String year = df2.format(new Date());
        List<JudgesResponseDto> resultList = new ArrayList<>();
        List<Grade> grades = null;
        Judges judges = judgesDao.selectByJudgesId(judgesDto.getAccount());
        if (judges.getNumber().equals("one")) {
            grades = judgesDao.selectByOneGrade("");
        } else if (judges.getNumber().equals("two")) {
            grades = judgesDao.selectByTwoGrade("");
        } else if (judges.getNumber().equals("three")) {
            grades = judgesDao.selectByThreeGrade("");
        } else if (judges.getNumber().equals("four")) {
            grades = judgesDao.selectByFourGrade("");
        } else if (judges.getNumber().equals("five")) {
            grades = judgesDao.selectByFiveGrade("");
        }
        //List<Scholarship> scholarships = judgesDao.selectByJId(judgesDto.getType(), year, "初审通过","");
        int index = 1;
        if (grades != null && grades.size() != 0) {

            for (Grade grade : grades) {

                Scholarship scholarship = judgesDao.selectBySIdAndType(grade.getStudentId(), grade.getApplyType(), grade.getYear(),"初审通过");

                JudgesResponseDto judgesResponse = new JudgesResponseDto();
                StudentApply student = judgesDao.selectById(scholarship.getStudentId());
                judgesResponse.setName("学生" + index);
                judgesResponse.setKey(scholarship.getStudentId() + "::" + scholarship.getType() + "::" + scholarship.getTime());
                judgesResponse.setTime(scholarship.getTime());
                if (scholarship.getType().equals("01")) {
                    judgesResponse.setType("一等奖学金");
                } else if (scholarship.getType().equals("02")) {
                    judgesResponse.setType("二等奖学金");
                } else if (scholarship.getType().equals("03")) {
                    judgesResponse.setType("三等奖学金");
                } else if (scholarship.getType().equals("04")) {
                    judgesResponse.setType("国家励志奖学金");
                } else if (scholarship.getType().equals("05")) {
                    judgesResponse.setType("国家助学金");
                }
                if (student != null) {
                    judgesResponse.setFgpa(student.getFGPA());
                    judgesResponse.setSgpa(student.getSGPA());
                }
                judgesResponse.setIntroduce(scholarship.getIntroduce());
                judgesResponse.setReason(scholarship.getReason());
                resultList.add(judgesResponse);
                index++;
            }
        }

        return resultList;
    }

    @Override
    public JudgesDetailsRep details(JudgesDetailsDto detailsDto) {
//        String[] split = detailsDto.getKey().split("::");
//        Scholarship scholarship = judgesDao.selectBySIdAndType(split[0], split[1], split[2]);
//        Student student = judgesDao.selectById(scholarship.getStudentId());
//        JudgesDetailsRep result = new JudgesDetailsRep();
//        BeanUtils.copyProperties(student,result);
//        result.setApplyType(scholarship.getType());
//        result.setTime(scholarship.getTime());
        //return result;
        return null;
    }

    @Override
    public String approval(JudgesApprovalDto judgesApprovalDto) {
        String[] split = judgesApprovalDto.getKey().split("::");
        judgesDao.updateTwoApproval(split[0], split[1], split[2], "复审通过", "初审通过");
        return "审批成功";
    }

    @Override
    public String notApproval(JudgesApprovalDto judgesApprovalDto) {
        DecimalFormat df = new DecimalFormat("0.00");
        String[] split = judgesApprovalDto.getKey().split("::");
        Judges judges = judgesDao.selectByJudgesId(judgesApprovalDto.getAccount());
        if (judges.getNumber().equals("one")) {
            judgesDao.updateOneGrade(split[0], split[1], split[2], judgesApprovalDto.getData());
        } else if (judges.getNumber().equals("two")) {
            judgesDao.updateTwoGrade(split[0], split[1], split[2], judgesApprovalDto.getData());
        } else if (judges.getNumber().equals("three")) {
            judgesDao.updateThreeGrade(split[0], split[1], split[2], judgesApprovalDto.getData());
        } else if (judges.getNumber().equals("four")) {
            judgesDao.updateFourGrade(split[0], split[1], split[2], judgesApprovalDto.getData());
        } else if (judges.getNumber().equals("five")) {
            judgesDao.updateFiveGrade(split[0], split[1], split[2], judgesApprovalDto.getData());
        }

        Grade grade = judgesDao.selectByKey(split[0], split[1], split[2]);
        if (!grade.getOneGrade().equals("")
                && !grade.getTwoGrade().equals("")
                && !grade.getThreeGrade().equals("")
                && !grade.getFourGrade().equals("")
                && !grade.getFiveGrade().equals("")) {
            Integer one = Integer.valueOf(grade.getOneGrade());
            Integer two = Integer.valueOf(grade.getTwoGrade());
            Integer three = Integer.valueOf(grade.getThreeGrade());
            Integer four = Integer.valueOf(grade.getFourGrade());
            Integer five = Integer.valueOf(grade.getFiveGrade());
            Double avg = Double.valueOf(df.format((one * 1.0 + two * 1.0 + three * 1.0 + four * 1.0 + five * 1.0) / 5));
            judgesDao.updateTwoApproval(split[0], split[1], split[2], avg.toString(), "初审通过");
        }
        return "提交成功";
    }

    @Override
    public List<JMyApprovalRep> myApproval(JudgesApprovalDto judgesApprovalDto) {
        SimpleDateFormat df2 = new SimpleDateFormat(("yyyy"));
        String year = df2.format(new Date());
        List<JMyApprovalRep> resultList = new ArrayList<>();
        //查出评委
        Judges judges = judgesDao.selectByJudgesId(judgesApprovalDto.getAccount());
        List<Grade> gradeList = null;
        if (judges.getNumber().equals("one")) {
            gradeList = judgesDao.selectOneGrade("");
        } else if (judges.getNumber().equals("two")) {
            gradeList = judgesDao.selectTwoGrade("");
        } else if (judges.getNumber().equals("three")) {
            gradeList = judgesDao.selectThreeGrade("");
        } else if (judges.getNumber().equals("four")) {
            gradeList = judgesDao.selectFourGrade("");
        } else if (judges.getNumber().equals("five")) {
            gradeList = judgesDao.selectFiveGrade("");
        }


        //List<Scholarship> scholarships = judgesDao.myApproval("初审通过", year);
        if (gradeList == null || gradeList.size() == 0) {
            return null;
        }
        int index = 1;
        for (Grade grade : gradeList) {
            JMyApprovalRep jMyApprovalRep = new JMyApprovalRep();

            if (judges.getNumber().equals("one")) {
            jMyApprovalRep.setTwoApproval(grade.getOneGrade());
            } else if (judges.getNumber().equals("two")) {
                jMyApprovalRep.setTwoApproval(grade.getTwoGrade());

            } else if (judges.getNumber().equals("three")) {
                jMyApprovalRep.setTwoApproval(grade.getThreeGrade());
            } else if (judges.getNumber().equals("four")) {
                jMyApprovalRep.setTwoApproval(grade.getFourGrade());
            } else if (judges.getNumber().equals("five")) {
                jMyApprovalRep.setTwoApproval(grade.getFiveGrade());
            }


            Scholarship scholarship = judgesDao.selectBySIdAndType(grade.getStudentId(), grade.getApplyType(), grade.getYear(),"初审通过");

            jMyApprovalRep.setName("学生" + index);
            jMyApprovalRep.setTime(scholarship.getTime());
//            jMyApprovalRep.setTwoApproval(scholarship.getTwoApproval());
            if (scholarship.getType().equals("01")) {

                jMyApprovalRep.setType("一等奖学金");
            } else if (scholarship.getType().equals("02")) {

                jMyApprovalRep.setType("二等奖学金");
            } else if (scholarship.getType().equals("03")) {

                jMyApprovalRep.setType("三等奖学金");
            } else if (scholarship.getType().equals("04")) {

                jMyApprovalRep.setType("国家励志奖学金");
            } else if (scholarship.getType().equals("05")) {

                jMyApprovalRep.setType("国家助学金");
            }
            resultList.add(jMyApprovalRep);
            index++;
        }
        return resultList;
    }
}
