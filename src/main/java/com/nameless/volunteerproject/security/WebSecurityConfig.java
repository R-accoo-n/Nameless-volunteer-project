package com.nameless.volunteerproject.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
class SecurityConfiguration {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests()
                // URL matching for accessibility
                .antMatchers("/", "/login/**","/register/**",  "/support/**","/feedback/**","/volunteer/**", "/donation/**",
                        "/volunteerRegistration/**", "/userRegistration/**","/militaryRegistration/**", "/register/save/**", "/user/**",
                        "/waiting/**","/home","/admin/**","/admin/block/**", "/admin/users/admin/block/**" ,"/military/**", "/admin/approve/**",
                        "/admin/block/**","/fundraisingOverview/**", "/registration/**", "/user/**","/register/save/**", "/save/**", "/request/**", "/request/save/**", "/fundraising/**", "/fundraising/save/**", "/statusFundraising","/user/**").permitAll()
                .antMatchers("/css/**", "/js/**", "/bootstrap/**", "/static/**", "/mixins/**", "/utilities/**").permitAll()
                .antMatchers("/admin/**").hasAnyAuthority("ADMINISTRATOR")
                .antMatchers("/user/**").hasAnyAuthority("USER")
                .antMatchers("/military/**").hasAnyAuthority("MILITARY")
                .antMatchers("/volunteer/**").hasAnyAuthority("VOLUNTEER")
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

//        http.authenticationProvider(authenticationProvider());
//        http.headers().frameOptions().sameOrigin();

        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().antMatchers("/mixins/**", "/webjars/**", "/css/**", "/static/**", "/js/**", "/images/**", "/static/js/**");
    }
}