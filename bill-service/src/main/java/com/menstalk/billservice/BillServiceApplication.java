package com.menstalk.billservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class BillServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(BillServiceApplication.class, args);
    }
}
