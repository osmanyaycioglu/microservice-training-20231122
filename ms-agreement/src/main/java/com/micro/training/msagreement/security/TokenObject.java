package com.micro.training.msagreement.security;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TokenObject {
    private String token;
    private LocalDateTime expirationDate;
}
