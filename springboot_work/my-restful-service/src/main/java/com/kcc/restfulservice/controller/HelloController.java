package com.kcc.restfulservice.controller;

import com.kcc.restfulservice.bean.HelloWorldBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello-world")
    public String helloWorld() {
        return "Hello World";
    }

    @GetMapping("/hello-world-bean")
    public HelloWorldBean helloWorldBean() {
        return new HelloWorldBean("Hello World Bean!!");
    }

    @GetMapping("/hello-world-bean/{name}")
    public HelloWorldBean helloWorldBean2(@PathVariable String name) {
        return new HelloWorldBean(String.format("Hello world bean %s!", name));
    }
}
