// package com.marcos.ecommerce.createaccount.securityconfig;


// //import org.springframework.context.annotation.Bean;
// //import org.springframework.context.annotation.Configuration;
// //import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// //import org.springframework.security.web.SecurityFilterChain;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.web.SecurityFilterChain;
// import org.springframework.security.config.http.SessionCreationPolicy;


// @Configuration
// public class Authorization {

//     @Bean
//     public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//         http
//             .csrf(csrf -> csrf.disable())
//             .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // 🔑 aqui!
//             .authorizeHttpRequests(auth -> auth
//                 .requestMatchers("/api/v1/creataccount/new").permitAll()
//                 .requestMatchers("/api/v1/auth/**").permitAll()
//                 .anyRequest().authenticated()
//             );

//         return http.build();
//     }
// }
