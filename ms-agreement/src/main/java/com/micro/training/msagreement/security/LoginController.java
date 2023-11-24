package com.micro.training.msagreement.security;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/sec/v1/security")
@RequiredArgsConstructor
public class LoginController
{
    private final JWTService jwtService;
    private final MyUserDetailService detailService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public String login(@Valid @RequestBody UserCredential uc){
        Authentication authenticateLoc = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(uc.getUsername(),
                                                                                                                    uc.getPassword()));
        return jwtService.generateJWT((UserDetails) authenticateLoc.getPrincipal());
    }
}
