package me.ding.easywareflow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class EasyWareFlowApplication {
    public static void main(String[] args) {
        SpringApplication.run(EasyWareFlowApplication.class, args);
    }

    @Bean
    public void test() {
        System.out.println("http://localhost:9999/easywareflow/hello");
    }
}
