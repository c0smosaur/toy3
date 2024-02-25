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

    String root = "com.core.toy3.src";
    String[] paths = {
            ".travel.controller",
            ".trip.controller",
            ".member.controller",
            ".comment.controller",
            ".like.controller"
    };

    @Bean
    public GroupedOpenApi getEntireApi() {
        return GroupedOpenApi.builder()
                .group("entire")
                .packagesToScan(root)
                .build();
    }

    @Bean
    public GroupedOpenApi getTravelApi() {
        return GroupedOpenApi.builder()
                .group("travel & Trip")
                .packagesToScan(root + paths[0], root + paths[1])
                .build();
    }

    @Bean
    public GroupedOpenApi getMemberApi() {
        return GroupedOpenApi.builder()
                .group("member")
                .packagesToScan(root + paths[2])
                .build();
    }

    @Bean
    public GroupedOpenApi getCommentApi() {
        return GroupedOpenApi.builder()
                .group("comment")
                .packagesToScan(root + paths[3])
                .build();
    }

    @Bean
    public GroupedOpenApi getLikeApi() {
        return GroupedOpenApi.builder()
                .group("like")
                .packagesToScan(root + paths[4])
                .build();
    }
}
