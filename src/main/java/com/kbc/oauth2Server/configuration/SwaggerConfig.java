package com.kbc.oauth2Server.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo())
                .useDefaultResponseMessages(false)
                .globalResponseMessage(RequestMethod.GET, createGlobalResponseMessages());
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "Oauth2 Authorization Server API",
                "API for REST calls",
                "API 1.0",
                null, null, null, null, Collections.emptyList());
    }

    private List<ResponseMessage> createGlobalResponseMessages() {
        List<ResponseMessage> responseMessageList = new ArrayList<>();

        responseMessageList.add(
                new ResponseMessageBuilder()
                        .code(500)
                        .message("Internal server error")
                        .responseModel(new ModelRef("Error"))
                        .build()
        );
        responseMessageList.add(
                new ResponseMessageBuilder()
                        .code(401)
                        .message("Unauthorized")
                        .build()
        );
        responseMessageList.add(
                new ResponseMessageBuilder()
                        .code(403)
                        .message("Forbidden")
                        .build()
        );

        return responseMessageList;
    }

}
