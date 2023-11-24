package com.micro.training.msagreement.security;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class UserCredential {
    @NotEmpty
    private String username;
    @NotEmpty
    private String password;
}
