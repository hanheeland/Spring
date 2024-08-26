package com.kcc.security2.config;

import com.kcc.security2.filter.MyFilter2;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//우선 순위 비교용(test)
//springFramework에 bean 등록
@Configuration
public class FilterConfig {

    //@Bean
    public FilterRegistrationBean<MyFilter2> filter2(){
        FilterRegistrationBean<MyFilter2> bean = new FilterRegistrationBean<>(new MyFilter2());
        bean.addUrlPatterns("/*");
        bean.setOrder(0);
        return bean;
    }
}
