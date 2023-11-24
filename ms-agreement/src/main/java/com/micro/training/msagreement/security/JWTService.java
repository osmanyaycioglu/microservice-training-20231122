package com.micro.training.msagreement.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Key;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class JWTService {
    private static final Logger logger = LoggerFactory.getLogger(JWTService.class);
    private              Key    key;

    public JWTService() {
        key = Keys.hmacShaKeyFor("JWTDenemeYanilmaTrialTestlasjkdklaksdjbasdkjasdkjashdklajsd".getBytes());
    }

    public String generateJWT(UserDetails userDetailsParam) {
        return Jwts.builder()
                   .subject(userDetailsParam.getUsername())
                   .issuedAt(new Date())
                   .expiration(new Date(System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(30)))
                   .claim("xyz",
                          "deneme")
                   .signWith(key)
                   .compact();
    }

    public Jws<Claims> validate(String token) {
        JwtParser jwtParserLoc = Jwts.parser()
                                     .setSigningKey(key)
                                     .build();
        Jws<Claims> claimsJws = null;
        try {
            claimsJws = jwtParserLoc.parseClaimsJws(token);
            return claimsJws;
        } catch (Exception eParam) {
            logger.error("[JWTService][validate]-> *Error* : " + eParam.getMessage(),
                         eParam);
            return null;
        }
    }


}
