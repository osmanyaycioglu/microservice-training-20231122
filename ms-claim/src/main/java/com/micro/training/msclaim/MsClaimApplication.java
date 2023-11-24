package com.micro.training.msclaim;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class MsClaimApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsClaimApplication.class,
                              args);
    }

}
