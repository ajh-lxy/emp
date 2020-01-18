package com.fh.utils.imgUtil;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;


public class CopyFile {

    public static String copyFile(MultipartFile photo, String mkdirName){
        //临时文件重命名
        String oldName=photo.getOriginalFilename();//获取老的文件名.后缀
        Long time=System.currentTimeMillis();
        //123.png
        String suffix=oldName.substring(oldName.lastIndexOf("."));
        String newFileName=time+suffix;
        //拼接保存文件的绝对路径
       HttpServletRequest request=((ServletRequestAttributes)(RequestContextHolder.currentRequestAttributes())).getRequest();
       //获取项目在你电脑硬盘的绝对路径
        //d://tomcat/ssm/
       String realPath=request.getServletContext().getRealPath("/");
        realPath= realPath+"commons/"+mkdirName;

        //检查文件路径是否存在不存在进行创建
        File file=new File(realPath);
        if(!file.exists()){
            file.mkdirs();
        }
        //copy文件
        try {
            photo.transferTo(new File(realPath+"/"+newFileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //返回相对路径
        return "/commons/"+mkdirName+"/"+newFileName;
    }
}
