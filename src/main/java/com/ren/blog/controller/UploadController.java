package com.ren.blog.controller;
/*
 *@Author:WuRen
 *@Description:
 *@date: 21:15 2018/11/18
 */

import com.ren.blog.util.UuidUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Controller
public class UploadController {

//    @Value("${img.location}")
//    private String location;

    private final ResourceLoader resourceLoader;

//    public static final String ROOT = "C:\\Users\\wuren\\IdeaProjects\\ren-blog\\src\\main\\resources\\static\\upload";

    @Value("${img.location}")
    private String ROOT;
    @Autowired
    public UploadController(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }
//    @RequestMapping(value="/uploadfile",method= RequestMethod.POST)
//    public void hello(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "editormd-image-file", required = false) MultipartFile attach){
//        try {
//            request.setCharacterEncoding( "utf-8" );
//            response.setHeader( "Content-Type" , "text/html" );
//            String rootPath = request.getSession().getServletContext().getRealPath("/upload/");
//            System.out.println(rootPath);
//
////            String rootPath = "..//..//..//..//static//image";
////            System.out.println(ResourceUtils.getURL("classpath:").getPath());;
////            System.out.println(location);
//            /**
//             *
//             * 文件路径不存在则需要创建文件路径
//             */
//            File filePath=new File(rootPath);
//            if(!filePath.exists()){
//                filePath.mkdirs();
//            }
//
//            //最终文件名
//            File realFile=new File(rootPath+File.separator+attach.getOriginalFilename());
//            realFile.createNewFile();
//            FileOutputStream fout = new FileOutputStream(realFile);
//            fout.write(attach.getBytes());
//            fout.close();
//            //下面response返回的json格式是editor.md所限制的，规范输出就OK
//            response.getWriter().write( "{\"success\": 1, \"message\":\"上传成功\",\"url\":\"/upload/" + attach.getOriginalFilename() + "\"}" );
//        } catch (Exception e) {
//            try {
//                response.getWriter().write( "{\"success\":0}" );
//            } catch (IOException e1) {
//                e1.printStackTrace();
//            }
//        }
//    }


    //显示图片的方法关键 匹配路径像 localhost:8080/b7c76eb3-5a67-4d41-ae5c-1642af3f8746.png
    @RequestMapping(method = RequestMethod.GET, value = "/{filename:.+}")
    public ResponseEntity<?> getFile(@PathVariable String filename) {

        try {
            return ResponseEntity.ok(resourceLoader.getResource("file:" + Paths.get(ROOT, filename).toString()));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }


    @RequestMapping(value="/uploadfile",method= RequestMethod.POST)
    public void hello(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "editormd-image-file", required = false) MultipartFile attach){
        try {
            request.setCharacterEncoding( "utf-8" );
            response.setHeader( "Content-Type" , "text/html" );

//            String rootPath = request.getSession().getServletContext().getRealPath("/upload/");
//            System.out.println(rootPath);

//            String rootPath = "..//..//..//..//static//image";
//            System.out.println(ResourceUtils.getURL("classpath:").getPath());;
//            System.out.println(location);
//            /**
//             *
//             * 文件路径不存在则需要创建文件路径
//             */
//            File filePath=new File(rootPath);
//            if(!filePath.exists()){
//                filePath.mkdirs();
//            }

            //最终文件名
//            File realFile=new File(rootPath+File.separator+attach.getOriginalFilename());
//            realFile.createNewFile();
//            FileOutputStream fout = new FileOutputStream(realFile);
//            fout.write(attach.getBytes());
//            fout.close();

            String fileName = attach.getOriginalFilename();
            int pot = fileName.lastIndexOf(".");
            String exName = "";
            if(pot != -1){
                exName = fileName.substring(pot);
            }
            String newName = UuidUtils.getUUID() + exName;

            Files.copy(attach.getInputStream(), Paths.get(ROOT, newName));
            //下面response返回的json格式是editor.md所限制的，规范输出就OK
            response.getWriter().write( "{\"success\": 1, \"message\":\"上传成功\",\"url\":\"/" + newName + "\"}" );
        } catch (Exception e) {
            try {
                response.getWriter().write( "{\"success\":0}" );
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }
}
