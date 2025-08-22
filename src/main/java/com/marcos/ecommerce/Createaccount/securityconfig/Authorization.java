package com.marcos.ecommerce.createaccount.securityconfig;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class Authorization{

    @Bean 
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .authorizeHttpRequests(auth -> auth
                //.requestMatchers("/api/v1/creataccount/delete/**").permitAll()
                .requestMatchers("/api/v1/creataccount/new").permitAll()
                .anyRequest().authenticated()
            );

        return http.build();
    }
}