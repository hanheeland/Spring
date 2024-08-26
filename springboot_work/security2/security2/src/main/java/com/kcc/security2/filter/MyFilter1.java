package com.kcc.security2.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

public class MyFilter1 implements Filter {

    @Override
    public void doFilter(ServletRequest request,ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        System.out.println("filter1...........");

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        if(req.getMethod().equals("POST")){
            String auth = req.getHeader("Authorization");
            System.out.println(auth);

            if(auth.equals("kosa")){
                chain.doFilter(req, res);
            }else{
                PrintWriter out = res.getWriter();
                out.println("login Fail....");
            }
        }

        //chain.doFilter(req, res);
    }
}
