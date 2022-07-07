package com.engagementMusic.engagementMusic.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfiguration {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.httpBasic();

        http.authorizeRequests()
                .mvcMatchers(HttpMethod.DELETE, "/band/delete_by_id").hasRole("BAND")
                .mvcMatchers(HttpMethod.POST, "/band/**").hasRole("BAND")
                .mvcMatchers(HttpMethod.GET, "/band/**").hasRole("BAND")
                .mvcMatchers(HttpMethod.DELETE, "/band/**").hasRole("BAND")
                .mvcMatchers(HttpMethod.PUT, "/group/**").hasRole("GROUP")
                .mvcMatchers(HttpMethod.GET, "/agent/**").hasRole("AGENT")
                .mvcMatchers(HttpMethod.POST, "/agent/**").hasRole("AGENT")
                .mvcMatchers(HttpMethod.PUT, "/agent/**").hasRole("AGENT")
                .anyRequest().permitAll();


        http.csrf().disable();
        return http.build();


    }
}
