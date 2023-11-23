package com.micro.training.msagreement;

import com.micro.training.mscommon.error.ErrorConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Import;

@EnableDiscoveryClient
@SpringBootApplication
@Import(ErrorConfig.class)
public class MsAgreementApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsAgreementApplication.class,
                              args);
    }

}
