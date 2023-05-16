package com.nameless.volunteerproject.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.InMemoryOAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
@EnableWebSecurity
@PropertySource("classpath:application.properties")
class SecurityConfiguration {
    @Autowired
    private Environment env;
    private static List<String> clients = Arrays.asList("google", "facebook");
    private static String CLIENT_PROPERTY_KEY
            = "spring.security.oauth2.client.registration.";
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public ClientRegistrationRepository clientRegistrationRepository() {
        List<ClientRegistration> registrations = clients.stream()
                .map(c -> getRegistration(c))
                .filter(registration -> registration != null)
                .collect(Collectors.toList());

        return new InMemoryClientRegistrationRepository(registrations);
    }


    private ClientRegistration getRegistration(String client) {
        String clientId = env.getProperty(
                CLIENT_PROPERTY_KEY + client + ".client-id");

        if (clientId == null) {
            return null;
        }

        String clientSecret = env.getProperty(
                CLIENT_PROPERTY_KEY + client + ".client-secret");

        if (client.equals("google")) {
            return CommonOAuth2Provider.GOOGLE.getBuilder(client)
                    .clientId(clientId)
                    .clientSecret(clientSecret).redirectUriTemplate("http://localhost:8080/home").build();
        }
        if (client.equals("facebook")) {
            return CommonOAuth2Provider.FACEBOOK.getBuilder(client)
                    .clientId(clientId)
                    .clientSecret(clientSecret)
                    .build();
        }
        return null;
    }
    @Bean
    public OAuth2AuthorizedClientService authorizedClientService() {

        return new InMemoryOAuth2AuthorizedClientService(
                clientRegistrationRepository());
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests()
                // URL matching for accessibility
                .antMatchers("/", "/home", "/login/**", "/register/**", "/registration/**", "/register/save/**", "/oauth/**",
                        "/support/**", "/feedback/**", "/donation/**",
                        "/volunteerRegistration/**", "/userRegistration/**", "/militaryRegistration/**",
                        "/user/**", "/volunteer/**", "/military/**",
                        "/waiting/**", "/save/**",
                        "/admin/**", "/admin/block/**", "/admin/users/admin/block/**", "/admin/approve/**", "/admin/block/**",
                        "/request/**", "/request/save/**",
                        "/fundraising/**", "/fundraising/save/**", "/statusFundraising", "/fundraisingOverview/**")
                .permitAll()
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
                .accessDeniedPage("/access-denied")
                .and()
                .oauth2Login()
                .loginPage("/login");
//                .redirectionEndpoint().baseUri("http://localhost:8080/home")
//                .and()
//                .clientRegistrationRepository(clientRegistrationRepository())
//                .authorizedClientService(authorizedClientService());
        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().antMatchers("/mixins/**", "/webjars/**", "/css/**", "/static/**", "/js/**", "/images/**", "/static/js/**");
    }
}
