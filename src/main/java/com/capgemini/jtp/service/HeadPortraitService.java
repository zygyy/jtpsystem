package com.capgemini.jtp.service;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public interface HeadPortraitService {
    int headUpload(MultipartFile file, HttpServletRequest request) throws IOException;
    String getHeadUrl(String userName,HttpServletRequest request);
    void getImg(String url,HttpServletRequest request,
                HttpServletResponse response)  throws IOException;
    void getImgs(HttpServletRequest request,
                 HttpServletResponse response) throws IOException;
}
