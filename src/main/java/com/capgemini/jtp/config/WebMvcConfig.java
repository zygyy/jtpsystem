//package com.capgemini.jtp.config;
//
//import com.capgemini.jtp.common.DateConverter;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.format.FormatterRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//
///**
// * @Description: TODO
// * @Classname : WebMvcConfig
// * @author: Jason Jin
// * @date: 2019/5/19 11:47 PM
// */
//@Configuration
//public class WebMvcConfig implements WebMvcConfigurer {
//
//    @Override
//    public void addFormatters(FormatterRegistry registry) {
//        registry.addConverter(new DateConverter());
//    }
//
//    @Bean
//    public ExecutorService executorService() {
//        return Executors.newCachedThreadPool();
//    }
//}
