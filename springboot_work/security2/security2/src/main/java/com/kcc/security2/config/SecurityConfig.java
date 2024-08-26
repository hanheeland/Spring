package com.kcc.security2.config;

import com.kcc.security2.filter.JwtAuthenticationFilter;
import com.kcc.security2.filter.MyFilter1;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final String[] WHITE_LIST = {
      "/join", "/h2-console/**",
    };

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        AuthenticationManager authenticationManager =
                http.getSharedObject(AuthenticationManager.class);

        //http.addFilterBefore(new MyFilter1(), BasicAuthenticationFilter.class); // 실행전에 필터 건다
        http.csrf(AbstractHttpConfigurer::disable)
                .headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin)) //프레임 깨지는 것 방지
                .formLogin(formLogin -> formLogin.disable()) //사용 x
                .httpBasic(httpBasic -> httpBasic.disable()) //사용 x
                .addFilter(new JwtAuthenticationFilter(authentication -> authentication))
                .authorizeHttpRequests(authorize ->
                        authorize.requestMatchers(WHITE_LIST).permitAll() //요청 허용
                                .requestMatchers(PathRequest.toH2Console()).permitAll() //요청 허용
                                .anyRequest().authenticated()
                ).sessionManagement(sessionManagement ->
                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)); // 세션을 쓰지 않겠다 선언
    return http.build();
    }

}
