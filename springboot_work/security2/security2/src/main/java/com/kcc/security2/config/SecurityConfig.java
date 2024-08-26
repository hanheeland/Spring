package com.kcc.security2.config;

import com.kcc.security2.filter.JwtAuthenticationFilter;
import com.kcc.security2.filter.JwtAuthorizationFilter;
import com.kcc.security2.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final UserRepository userRepository;
    private final CorsFilter corsFilter;

    private final String[] WHITE_LIST = {
      "/join", "/h2-console/**",
    };

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, AuthenticationManager authenticationManager) throws Exception {
//        AuthenticationManager authenticationManager =
//                http.getSharedObject(AuthenticationManager.class);
        //http.addFilterBefore(new MyFilter1(), BasicAuthenticationFilter.class); // 실행전에 필터 건다

        http.csrf(AbstractHttpConfigurer::disable) // csrf 토큰 사용 안하겠다.
                .headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin)) // h2 db 프레임 깨지는 것 방지
                .formLogin(formLogin -> formLogin.disable()) // restful 서비스 이기때문에 사용 x
                .httpBasic(httpBasic -> httpBasic.disable()) //restful 서비스 이기때문에 사용 x
                .addFilter(corsFilter)
                .addFilter(new JwtAuthenticationFilter(authenticationManager)) //
                .addFilter(new JwtAuthorizationFilter(authenticationManager, userRepository))
                .authorizeHttpRequests(authorize ->
                        authorize.requestMatchers(WHITE_LIST).permitAll() //권한 허용
                                .requestMatchers("/manager/**").hasAnyRole("MANAGER", "ADMIN")
                                .requestMatchers("/admin/**").hasAnyRole("ADMIN")
                                .requestMatchers(PathRequest.toH2Console()).permitAll() //권한 허용
                                .anyRequest().authenticated()
                ).sessionManagement(sessionManagement ->
                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)); // 세션을 쓰지 않겠다 선언
        return http.build();
    }

}
