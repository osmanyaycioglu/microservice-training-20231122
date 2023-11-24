package com.micro.training.msagreement.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.userdetails.DaoAuthenticationConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {
    private final BCryptPasswordEncoder passwordEncoder;

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity httpSecurityParam,
                                                       MyUserDetailService userDetailServiceParam) throws Exception {
        DaoAuthenticationConfigurer<AuthenticationManagerBuilder, MyUserDetailService> configurerLoc = new DaoAuthenticationConfigurer<>(userDetailServiceParam);
        configurerLoc.passwordEncoder(passwordEncoder);

        AuthenticationManagerBuilder sharedObjectLoc = httpSecurityParam.getSharedObject(AuthenticationManagerBuilder.class);

        return sharedObjectLoc.apply(configurerLoc)
                              .and()
                              .build();
    }

    @Bean
    public JWTFilter jwtFilter() {
        return new JWTFilter();
    }

    @Bean
    public SecurityFilterChain configureSec(HttpSecurity httpSecurityParam) throws Exception {
        return httpSecurityParam.authorizeHttpRequests(a ->
                                                               a.requestMatchers("/actuator/**",
                                                                                 "/sec/**")
                                                                .anonymous()
                                                                .requestMatchers(new AntPathRequestMatcher("/api/**"))
                                                                .hasRole("ADMIN")
                                                                .anyRequest()
                                                                .authenticated()
                                )
                                .cors(AbstractHttpConfigurer::disable)
                                .csrf(AbstractHttpConfigurer::disable)
                                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                                .addFilterBefore(this.jwtFilter(),
                                                 UsernamePasswordAuthenticationFilter.class)
                                .build();

    }

    @Bean
    public JWTService jwtService() {
        return new JWTService();
    }

}
