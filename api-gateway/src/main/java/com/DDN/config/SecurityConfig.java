package com.DDN.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests()  // Thay thế authorizeRequests() để sử dụng DSL mới
                .requestMatchers("/**").permitAll() // Cho phép truy cập không xác thực
                .anyRequest().authenticated()  // Cần xác thực cho các yêu cầu còn lại
            .and()
            .formLogin().disable();  // Tắt form login mặc định

        return http.build();
    }
}
