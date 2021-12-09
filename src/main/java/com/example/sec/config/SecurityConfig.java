package com.example.sec.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception
    {
        auth
                .inMemoryAuthentication()
                .withUser("admin").password(passwordEncoder().encode("admin")).roles("ADMIN").authorities("ACCESS_ADMIN_WRITE","ACCESS_ADMIN_READ","ACCESS_READ")
                .and()
                .withUser("user").password(passwordEncoder().encode("user")).roles("USER").authorities("ACCESS_READ");

    }

    @Override
    protected void configure(HttpSecurity http)  throws Exception
    {
        http
                .authorizeRequests()
                .antMatchers("/v1/private/users").hasAnyAuthority("ACCESS_READ")
                .antMatchers("/v1/private/admin").hasAnyAuthority("ACCESS_ADMIN_READ")
                .antMatchers("/v1/private/admin/update").hasAnyAuthority("ACCESS_ADMIN_WRITE")

                .anyRequest().permitAll()
                .and()
                .httpBasic();
    }

    @Bean
    PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }






}
