package com.kcc.security2.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.kcc.security2.config.PrincipalDetail;
import com.kcc.security2.model.User;
import com.kcc.security2.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import java.io.IOException;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {
    private final UserRepository userRepository;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, UserRepository userRepository) {
        super(authenticationManager);
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        //super.doFilterInternal(request, response, chain);
        System.out.println("JWT 필터 호출");

        String jwtHeader = request.getHeader("Authorization");
        System.out.println("jwtToken = " + jwtHeader);

        //JWT 토큰을 검증을 해서 정상적인 사용자인지 확인
        if (jwtHeader == null || !jwtHeader.startsWith("Bearer ")) {
            chain.doFilter(request, response);
            return;
        }

        String jwtToken = request.getHeader("Authorization")
                .replace("Bearer ", "");
        String username = JWT.require(Algorithm.HMAC512("kcc"))
                .build().verify(jwtToken).getClaim("username").asString();
        System.out.println("username = " + username);

        // principalDetail 만들어서 authentication 넣고
        // authentication 만들어서 SecurityContextHolder에 넣는다.
        if (username != null) {
            User userEntity = userRepository.findByUsername(username);
            PrincipalDetail principalDetail = new PrincipalDetail(userEntity);
            Authentication authentication = new UsernamePasswordAuthenticationToken(principalDetail,
                    null, principalDetail.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            chain.doFilter(request, response);
        }

    }
}
