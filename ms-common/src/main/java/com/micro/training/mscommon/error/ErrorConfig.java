package com.micro.training.mscommon.error;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ErrorConfig {

    @Bean
    public ErrorAdvice errorAdvice(){
        return new ErrorAdvice();
    }

}
