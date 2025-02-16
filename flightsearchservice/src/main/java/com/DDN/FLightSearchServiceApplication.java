package com.DDN;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@EnableFeignClients
@SpringBootApplication
@ComponentScan(basePackages = {"com.DDN","com.DDN.config", "com.DDN.component"})
public class FLightSearchServiceApplication {
    public static void main(String[] args) {
		SpringApplication.run(FLightSearchServiceApplication.class, args);
	}
}
