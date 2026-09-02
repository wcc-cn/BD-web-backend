package com.cc.behaviordetectionbackend.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName SwaggerConfig
 * @Description swagger 配置类
 * @Author cc
 * @Date 2025/11/27 22:04
 * @Version 1.0.0
 */
@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("接口文档")
                        .description("SpringBoot3 集成 Swagger3接口文档")
                        .version("v1"))
                .components(components())
                // 2. 再在这里添加上Swagger要使用的安全策略
                // addList()中写上对应的key
                .addSecurityItem(new SecurityRequirement().addList("satoken"));
    }

    // 1. 先在组件中注册安全策略
    private Components components(){
        return new Components()
                // 第一个参数是key值，后面是初始化一个安全策略的参数
                .addSecuritySchemes("satoken", new SecurityScheme().type(SecurityScheme.Type.APIKEY).in(SecurityScheme.In.HEADER).name("satoken"));
    }
}
