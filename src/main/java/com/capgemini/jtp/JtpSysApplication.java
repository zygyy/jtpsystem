package com.capgemini.jtp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.FileSystemResource;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.MultipartConfigElement;
import java.io.File;
import java.io.IOException;

@SpringBootApplication
@MapperScan("com.capgemini.jtp.mapper")
@EnableCaching
public class JtpSysApplication {

	public static void main(String[] args) {
		SpringApplication.run(JtpSysApplication.class, args);
	}
	@Bean
	MultipartConfigElement multipartConfigElement() {
		MultipartConfigFactory factory = new MultipartConfigFactory();
		String path1="D:\\MyOffice\\images\\temps";
		File f1=new File(path1);
		if(!f1.exists()){
			f1.mkdirs();
		}
		factory.setLocation("D:\\MyOffice\\images\\temps");

		return factory.createMultipartConfig();
	}
}
