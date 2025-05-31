package org.cws.database.management.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI openAPI(){
        Info info = new Info()
                .title("CWS Database Management API")
                .description("This API exposes endpoints for interacts with Database and Camunda")
                .version("1.0");

        return new OpenAPI().info(info);
    }
}