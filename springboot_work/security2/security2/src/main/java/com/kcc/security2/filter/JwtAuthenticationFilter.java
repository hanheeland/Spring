package com.kcc.security2.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kcc.security2.config.PrincipalDetail;
import com.kcc.security2.model.User;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.Date;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;

    // 로그인이 호출될때 실행되는 메서드
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        System.out.println("attemptAuthentication 호출");
        //1. username, password 받기
        try {
            ObjectMapper om = new ObjectMapper(); // json데이터를 파싱하기 위함
            User user = om.readValue(request.getInputStream(), User.class);
            System.out.println("user = " + user);

            // authenticationToken 만들어서 전달해야한다.
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());

            //2. PrincipalDetailService => loadUserByUsername 메서드 호출
            Authentication authentication = authenticationManager.authenticate(authenticationToken);
            //3. PrincipalDetail 구하기
            PrincipalDetail principarDetail = (PrincipalDetail) authentication.getPrincipal();
            System.out.println("principarDetail = " + principarDetail);

            return authentication;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    // 로그인 성공시 호출
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain chain, Authentication authResult) throws IOException, ServletException {
        System.out.println("로그인 성공");
        //JWT Token 생성, 전송
        PrincipalDetail principalDetail = (PrincipalDetail) authResult.getPrincipal();
        String jwtToken = JWT.create()
                .withSubject("kosaToken")
                .withExpiresAt(new Date(System.currentTimeMillis() + (60000 * 10))) // 만료시간
                .withClaim("id", principalDetail.getUser().getId())
                .withClaim("username", principalDetail.getUser().getUsername())
                .sign(Algorithm.HMAC512("kcc")); // 서명

        response.addHeader("Authorization", "Bearer " + jwtToken);
    }

}
