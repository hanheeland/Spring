package com.kcc.restfulservice.controller;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.kcc.restfulservice.UserDaoService;
import com.kcc.restfulservice.bean.AdminUser;
import com.kcc.restfulservice.bean.User;
import com.kcc.restfulservice.exception.UserNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminUserController {

    private UserDaoService service;

    // 생성자 주입
    public AdminUserController(UserDaoService service) {
        this.service = service;
    }

    @GetMapping("/users")
    public MappingJacksonValue getUsers() {
        List<User> users = service.findAll();
        List<AdminUser> adminUsers = new ArrayList<>();
        AdminUser adminUser = null;
        for (User user : users) {
            adminUser = new AdminUser();
            BeanUtils.copyProperties(user, adminUser);
            adminUsers.add(adminUser);
        }

        // 4개만 필터링 하겠다.
        SimpleBeanPropertyFilter filter =
                SimpleBeanPropertyFilter.filterOutAllExcept("id", "name", "joinDate", "ssn");
        FilterProvider filterProvider =
                new SimpleFilterProvider().addFilter("UserInfo", filter);
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(adminUsers);
        mappingJacksonValue.setFilters(filterProvider);

        return mappingJacksonValue;
    }

    @GetMapping("/users/{id}")
    public MappingJacksonValue getUser(@PathVariable int id) {
        User user = service.findOne(id);
        AdminUser adminUser = new AdminUser();

        if (user == null) {
            throw new UserNotFoundException(String.format("id[%s] not found", id));
        } else {
            // user 객체를 adminUser에 복사해준다. 변수명이 같아야 한다.
            BeanUtils.copyProperties(user, adminUser);
        }

        // 4개만 조회하겠다.
        SimpleBeanPropertyFilter filter =
                SimpleBeanPropertyFilter.filterOutAllExcept("id", "name", "joinDate", "ssn");
        FilterProvider filterProvider =
                new SimpleFilterProvider().addFilter("UserInfo", filter);
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(adminUser);
        mappingJacksonValue.setFilters(filterProvider);

        return mappingJacksonValue;
    }

    @PostMapping("/users")
    public ResponseEntity<User> CreateUser(@Valid @RequestBody User user) {
        User saveUser = service.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(saveUser.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable int id) {
        User deleteUser = service.deleteById(id);

        if (deleteUser == null) {
           // throw new UserNotFoundException(String.format("id[%s] not found", id));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("fail");
        }

        return ResponseEntity.ok("success");
    }

}
