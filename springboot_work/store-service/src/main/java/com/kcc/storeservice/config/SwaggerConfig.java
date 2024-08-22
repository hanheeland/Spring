package com.kcc.storeservice.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;

@OpenAPIDefinition(
        info = @Info(title = "My Restaurant Service API 명세서",
                description = "Spring Boot를 개발하는 Rest API 명세서 입니다.",
                version = "v1.0.0" )
)

public class SwaggerConfig {
    @Bean
    public GroupedOpenApi customTestOpenApi() {
        String[] paths = {"/restaurant/**"};

        return GroupedOpenApi.builder()
                .group("일반사용자를 위한 API")
                .pathsToMatch(paths)
                .build();
    }
}
