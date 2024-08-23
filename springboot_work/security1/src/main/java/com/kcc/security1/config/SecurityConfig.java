package com.kcc.security1.config;

import com.kcc.security1.oauth.PrincipalOauth2UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SecurityConfig {

    @Autowired
    PrincipalOauth2UserService principalOauth2UserService;

    @Bean
    public BCryptPasswordEncoder encodePwd() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);
        http.authorizeRequests(authorizeRequests ->
                authorizeRequests
                        .requestMatchers("/common/**").permitAll()
                        .requestMatchers("/user/**").authenticated()
                        .requestMatchers("/manager/**").access("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
                        .requestMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
                        .requestMatchers(PathRequest.toH2Console()).permitAll()
                        .anyRequest().permitAll()
                ).csrf(csrf-> csrf.ignoringRequestMatchers(PathRequest.toH2Console()))
                .headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin))
                .formLogin(formLogin -> formLogin.loginPage("/loginForm")
                        .loginProcessingUrl("/login")
                        .defaultSuccessUrl("/main")
                ).oauth2Login(oauth2Login->
                        oauth2Login.loginPage("/loginForm")
                                .userInfoEndpoint(userInfoEndpoint ->
                                        userInfoEndpoint.userService(principalOauth2UserService))
                        );

        return http.build();
    }

}
