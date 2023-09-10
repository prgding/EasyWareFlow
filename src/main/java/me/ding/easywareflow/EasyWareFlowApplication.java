package me.ding.easywareflow;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@Slf4j
@SpringBootApplication
public class EasyWareFlowApplication {
    public static void main(String[] args) {
        SpringApplication.run(EasyWareFlowApplication.class, args);
        log.info("Swagger UI: http://localhost:9999/easywareflow/swagger-ui/index.html");
    }
}
