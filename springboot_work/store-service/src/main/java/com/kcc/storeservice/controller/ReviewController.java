package com.kcc.storeservice.controller;

import com.kcc.storeservice.domain.ReviewDTO;
import com.kcc.storeservice.domain.ReviewVO;
import com.kcc.storeservice.service.ReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class ReviewController {

    private final ReviewService service;

    public ReviewController(ReviewService service) {
        this.service = service;
    }

    @PostMapping("/review")
    public ResponseEntity<String> createRestaurant(@RequestBody ReviewVO review) {
        service.save(review);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(review.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/review/{id}")
    public ResponseEntity<String> deleteRestaurant(@PathVariable int id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
