package com.example.UserService.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.data.rest.configuration.SpringDataRestConfiguration;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    private static final String BASE_PACKAGE = "com.example.UserService";

    @Value("${server.swagger.url:localhost:8081}")
    private String serverURL;

    @Value("${server.swagger.enable:true}")
    private boolean swaggerEnable;

    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2).host(this.serverURL).enable(this.swaggerEnable).select()
                .apis(RequestHandlerSelectors.basePackage(BASE_PACKAGE))
                .paths(PathSelectors.any())
                .build();
    }
}
