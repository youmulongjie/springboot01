/**
 * Copyright (C), 2015-2018
 * FileName: Swagger2
 * Author:   59458
 * Date:     2018/6/5 21:27
 * Description: Swagger2 配置类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.andy.demo.springboot01;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 〈一句话功能简述〉<br>
 * 〈Swagger2 配置类：在项目的启动类的同级目录下建立〉
 *
 * @author 59458
 * @create 2018/6/5
 * @since 1.0.0
 */
@Configuration
@EnableSwagger2 // 启动 Swagger2，也可以定义在 启动类上
public class Swagger2 {

    private String title = "springboot 使用 swagger2 构建RESTFul API文档";
    private String description = "API 描述";
    private String termsOfServiceUrl = "https://github.com/youmulongjie";
    private String version = "1.0";
    /**
     * swagger 扫描包
     */
    private String basePackage = "com.andy.demo.springboot01.controller";

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage(basePackage))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title(title).description(description)
                .contact(new Contact("Andy.wang", "https://github.com/youmulongjie", "594580820@qq.com"))
                .termsOfServiceUrl(termsOfServiceUrl).version(version).build();
    }
}