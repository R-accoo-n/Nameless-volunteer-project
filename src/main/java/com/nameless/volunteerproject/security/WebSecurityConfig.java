package com.nameless.volunteerproject.security;

import com.nameless.volunteerproject.enums.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
class SecurityConfiguration {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests()
                // URL matching for accessibility
                .antMatchers("/", "/login", "/register", "/home", "/registration", "/register/save", "/save", "/request", "/request/save", "/fundraising", "/fundraising/save", "/statusFundraising", "/feedback").permitAll()
                .antMatchers("/css/**", "/js/**", "/bootstrap/**", "/static/**", "/mixins/**", "/utilities/**").permitAll()
                .antMatchers("/admin/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .csrf().disable().formLogin()
                .loginPage("/login")
                .failureUrl("/login?error=true")
                .usernameParameter("email")
                .passwordParameter("password")
                .and()
                .exceptionHandling()
                .accessDeniedPage("/access-denied");

        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().antMatchers("/mixins/**", "/webjars/**", "/css/**", "/static/**", "/js/**", "/images/**", "/static/js/**");
    }
}