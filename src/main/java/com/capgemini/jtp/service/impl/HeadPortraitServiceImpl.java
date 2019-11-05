package com.capgemini.jtp.service.impl;

import com.capgemini.jtp.mapper.UserMapper;
import com.capgemini.jtp.service.HeadPortraitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.*;
import static com.capgemini.jtp.utils.MarkUtils.markImageByText;

@Service
public class HeadPortraitServiceImpl implements HeadPortraitService {

    @Autowired
    UserMapper userMapper;

    /**
     * 上传头像 并用当前用户的userName 命名，
     * @param file
     * @param request
     * @return
     */
    @Override
    public int headUpload(MultipartFile file, HttpServletRequest request)  {
        String fileName=file.getOriginalFilename();
        System.out.println("原始文件名："+fileName);

        Object object = request.getSession().getAttribute("operationUserId");
        int userId;
        if(object != null){
            userId = Integer.valueOf(String.valueOf(object));
        }else userId=0;
        String userName = userMapper.getMassageById(userId).getUsername();

        String newFileName=userName+".jpg";
        String path = "d:/MyOffice/images/Users/";

        File f = new File(path);
        File files;
        int temps = (int) (1 + Math.random() * (99999 - 1 + 1));
        files = new File("d:/MyOffice/images/Users/" + temps + ".jpg");
        if(!f.exists())
            f.mkdirs();
        if(!file.isEmpty()){
            FileOutputStream fos=null;
            FileInputStream fis=null ;

            try{
                fos= new FileOutputStream(path+newFileName);
                InputStream in = file.getInputStream();
                file.transferTo(files);
                fis=new FileInputStream(files);
                byte [] b=new byte[2048];
                int len;
                while ((len=fis.read(b))!=-1){
                    fos.write(b,0,len);
                }

            }catch(Exception e){
                e.printStackTrace();
                return -1;
            }finally {
                if (fos!=null){
                    try {
                        fos.close();
                       // System.out.println("关闭fos");
                    }catch (IOException e){
                        e.printStackTrace();
                    }
                }
                if (fis!=null) {
                    try {
                        fis.close();
                        //System.out.println("关闭fis");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        }
        System.out.println("上传图片到："+path+newFileName);
        int i=markImageByText("我的office系统",path+newFileName,path+newFileName,1, Color.red,"JPG");
        if(files.exists())
        {
            if(files.delete())
            {
                System.out.println("临时文件删除成功");
            }else
                System.out.println("临时文件删除失败");
        }

        return i;
    }

    /**
     * 获得该用户的头像url里面包含get方法
     * @param userName
     * @param request
     * @return
     */
    @Override
   public String  getHeadUrl(String userName,HttpServletRequest request){

        String newFileName=userName+".jpg";
        String path = "d:/MyOffice/images/Users/";
        File file=new File(path+newFileName);
        if(file.exists())
        {
            return "http://localhost:8085/HeadPortrait/getImg?url="+path+newFileName;
        }else
       return null;
    }

    /**
     * 根据url通过流输出到浏览器
     * @param url
     * @param request
     * @param response
     * @throws IOException
     */
    @Override
    public void getImg(String url,HttpServletRequest request,
                HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("image/jpeg");
        File file = new File(url);
        ServletOutputStream outputStream = response.getOutputStream();
        FileInputStream fileInputStream = new FileInputStream(file);
        byte[] bytes = new byte[1024];
        int len;
        while ((len=fileInputStream.read(bytes)) != -1){
            outputStream.write(bytes,0,len);
        }

        outputStream.flush();
        fileInputStream.close();
        outputStream.close();

    }

    /**
     * 通过流输出当前登录用户的头像
     * @param request
     * @param response
     * @throws IOException
     */
    @Override
    public void getImgs(HttpServletRequest request,
                 HttpServletResponse response) throws IOException{
        request.setCharacterEncoding("utf-8");
        Object object = request.getSession().getAttribute("operationUserId");
        int userId;
        if(object != null){
            userId = Integer.valueOf(String.valueOf(object));
        }else userId=0;
        String userName = userMapper.getMassageById(userId).getUsername();
        String newFileName=userName+".jpg";
        String path = "d:/MyOffice/images/Users/";

        response.setContentType("image/jpeg");
        File file = new File(path+newFileName);
        ServletOutputStream outputStream = response.getOutputStream();
        FileInputStream fileInputStream = new FileInputStream(file);
        byte[] bytes = new byte[1024];

        int len;
        while ((len=fileInputStream.read(bytes)) != -1){
            outputStream.write(bytes,0,len);
        }

        outputStream.flush();
        fileInputStream.close();
        outputStream.close();
    }
}
