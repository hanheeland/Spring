package com.kcc.restfulservice.controller;

import com.kcc.restfulservice.bean.Post;
import com.kcc.restfulservice.bean.User;
import com.kcc.restfulservice.exception.UserNotFoundException;
import com.kcc.restfulservice.service.UserService;
import jakarta.validation.Valid;
import org.springframework.hateoas.EntityModel;

import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
public class UserController {

    private UserService service;

    // 생성자 주입
    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("/users")
    public List<User> getUsers() {
        return service.findAll();
    }

    @GetMapping("/users/{id}")
    public EntityModel retrieveUser(@PathVariable int id) {
        User user = service.findOne(id);

        if (user == null) {
            throw new UserNotFoundException(String.format("ID[%s] not found", id));
        }

        EntityModel<User> entityModel = EntityModel.of(user);
        WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).getUsers());
        entityModel.add(linkTo.withRel("all-users"));

        return entityModel;
    }

    @PostMapping("/users")
    public ResponseEntity<User> CreateUser(@Valid @RequestBody User user) {
        service.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(user.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @PostMapping("/users/{id}/posts")
    public ResponseEntity<Post> createPost(@RequestBody Post post) {
        service.savePost(post);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(post.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @PatchMapping("/users")
    public ResponseEntity<String> updateUser(@RequestBody User user) {
        if (user.getId() == null) {
            return ResponseEntity.badRequest().body("User ID is required");
        }
        service.UpdateUser(user);
        return ResponseEntity.ok("updated success");
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable int id) {
        service.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

}
