package com.capgemini.jtp;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: jtpsystem
 * @description: SwaggerClient
 * @author: Jason Jin
 * @create: 2019-06-02 12:02
 **/
@Configuration
@EnableSwagger2
public class Swagger2 {
    @Bean
    public Docket createRestApi() {
        //添加head参数Cookie
        ParameterBuilder tokenPar = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<Parameter>();
        tokenPar.name("Cookie").description("Cookie认证").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
        pars.add(tokenPar.build());

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.capgemini.jtp"))
                .paths(Predicates.or(PathSelectors.regex("/.*")))
                .build()
                .globalOperationParameters(pars);
    }
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("MyOffice")
                .description("办公系统")
                .contact("Team.Two")
                .version("1.0")
                .build();
    }
}