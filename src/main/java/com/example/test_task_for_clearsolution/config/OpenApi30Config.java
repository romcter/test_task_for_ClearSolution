package com.example.test_task_for_clearsolution.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApi30Config {

    @Bean
    public OpenAPI customOpenAPI() {
        final String apiTitle = "Test task for ClearSolution";
        return new OpenAPI()
                .components(
                        new Components()
                )
                .info(new Info().title(apiTitle).version("V1"));
    }
}