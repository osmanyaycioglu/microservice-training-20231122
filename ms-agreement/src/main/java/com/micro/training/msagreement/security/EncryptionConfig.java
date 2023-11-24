package com.micro.training.msagreement.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableMethodSecurity(prePostEnabled = true)
public class EncryptionConfig {

    @Bean
    public BCryptPasswordEncoder passEncoder() {
        return new BCryptPasswordEncoder();
    }

}
