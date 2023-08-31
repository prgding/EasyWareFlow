package me.ding.easywareflow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class EasyWareFlowApplication {
    public static void main(String[] args) {
        SpringApplication.run(EasyWareFlowApplication.class, args);
    }
}
