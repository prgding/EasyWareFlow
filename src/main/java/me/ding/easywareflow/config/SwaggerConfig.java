package me.ding.easywareflow.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@Configuration

public class SwaggerConfig {
    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI().info(new Info()
                .title("仓易通 接口文档")
                .description("仓易通 Swagger 接口文档")
                .version("v1.0")
                .license(new License()
                        .name("MIT")
                        .url("https://github.com/prgding/EasyWareFlow/blob/main/LICENSE")
                )
        );
    }
}