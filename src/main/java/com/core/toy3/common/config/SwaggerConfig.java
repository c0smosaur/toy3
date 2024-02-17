package com.core.toy3.common.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "ToyProject3 API 명세서",
                description = "Java, Spring 기반 토이 프로젝트",
                version = "v1"
        )
)
@RequiredArgsConstructor
public class SwaggerConfig {

    String[] paths = {""};

    @Bean
    public GroupedOpenApi getTodoApi() {
        return GroupedOpenApi.builder()
                .group("")
                .pathsToMatch(paths[0] + "/**")
                .build();
    }
}
