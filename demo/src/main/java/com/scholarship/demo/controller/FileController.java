package com.scholarship.demo.controller;

import com.alibaba.fastjson.JSON;
import com.scholarship.demo.api.Download;
import com.scholarship.demo.api.TeacherDetailsDto;
import com.scholarship.demo.dao.TeacherDao;
import com.scholarship.demo.model.Result;
import com.scholarship.demo.model.Scholarship;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class FileController {

    @Autowired
    TeacherDao teacherDao;

    private static final Logger log = LoggerFactory.getLogger(FileController.class);

    @RequestMapping(value = "/upload")
    @ResponseBody
    public String upload(@RequestParam("file") MultipartFile file) {
        try {
            Download download = new Download();
            if (file.isEmpty()) {
                return "文件为空";
            }
            // 获取文件名
            String fileName = file.getOriginalFilename();
            log.info("上传的文件名为：" + fileName);
            // 获取文件的后缀名
            String suffixName = fileName.substring(fileName.lastIndexOf("."));
            log.info("文件的后缀名为：" + suffixName);
            File directory = new File("");//设定为当前文件夹
            // 设置文件存储路径

            //String filePath = "/Users/dalaoyang/Downloads/";
            String filePath = directory.getAbsolutePath();

            SimpleDateFormat simpleDateFormat =new SimpleDateFormat("yyyyMMddHHmmssSSS");
            String timeStamp=simpleDateFormat.format(new Date());

            fileName = timeStamp+suffixName;

            String path = filePath + "/" + fileName;
            File dest = new File(path);
            // 检测是否存在目录
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();// 新建文件夹
            }
            file.transferTo(dest);// 文件写入
            // /Users/syl/Public/Scholarship/demo
            // /Users/syl/Public/Scholarship/demo/测试.zip
            download.setPath(filePath);
            download.setFileName(fileName);
            System.out.println(filePath);
            System.out.println(path);
            return JSON.toJSONString(new Result(200,"-",download));
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return JSON.toJSONString(new Result(405,"-","上传失败"));
    }


    //实现Spring Boot 的文件下载功能，映射网址为/download
    @GetMapping("/download")
    @ResponseBody
    public String downloadFile(@RequestParam String key, HttpServletRequest request,
                               HttpServletResponse response) throws UnsupportedEncodingException {
//        String path = download.getPath();
//        String[] split = path.split("/");
        String[] split = key.split("::");
        Scholarship scholarship = teacherDao.selectBySIdAndType(split[0], split[1], split[2]);

        // 获取指定目录下的第一个文件
        File scFileDir = new File(scholarship.getPath());
        File TrxFiles[] = scFileDir.listFiles();
        //System.out.println(TrxFiles[0]);
        String fileName = scholarship.getFileName(); //下载的文件名

        // 如果文件名不为空，则进行下载
        if (fileName != null) {
            //设置文件路径
            String realPath = scholarship.getPath()+"/";
            File file = new File(realPath, fileName);

            // 如果文件名存在，则进行下载
            if (file.exists()) {

                // 配置文件下载
                response.setHeader("content-type", "application/octet-stream");
                response.setContentType("application/octet-stream");
                // 下载文件能正常显示中文
                response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));

                // 实现文件下载
                byte[] buffer = new byte[1024];
                FileInputStream fis = null;
                BufferedInputStream bis = null;
                try {
                    fis = new FileInputStream(file);
                    bis = new BufferedInputStream(fis);
                    OutputStream os = response.getOutputStream();
                    int i = bis.read(buffer);
                    while (i != -1) {
                        os.write(buffer, 0, i);
                        i = bis.read(buffer);
                    }
                    System.out.println("Download the song successfully!");
                }
                catch (Exception e) {
                    System.out.println("Download the song failed!");
                }
                finally {
                    if (bis != null) {
                        try {
                            bis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return null;
    }
}
