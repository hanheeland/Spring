package com.kcc.storeservice.controller;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.kcc.storeservice.domain.RestaurantVO;
import com.kcc.storeservice.domain.ReviewDTO;
import com.kcc.storeservice.domain.SearchRestaurantVO;
import com.kcc.storeservice.service.RestaurantService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
public class RestaurantController {

    private final RestaurantService service;

    public RestaurantController(RestaurantService restaurantService) {
        this.service = restaurantService;
    }

    @PostMapping("/restaurant")
    public ResponseEntity<String> createRestaurant(@Valid @RequestBody RestaurantVO restaurant) {
        service.save(restaurant);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(restaurant.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping("/restaurant")
    public MappingJacksonValue  getAllRestaurants() {
        List<RestaurantVO> restaurants = service.findAll();
        List<SearchRestaurantVO> searchRestaurants = new ArrayList<>();
        SearchRestaurantVO searchRestaurant = null;

        for (RestaurantVO restaurant : restaurants) {
            searchRestaurant = new SearchRestaurantVO();
            BeanUtils.copyProperties(restaurant, searchRestaurant);
            searchRestaurants.add(searchRestaurant);
        }

        SimpleBeanPropertyFilter filter =
                SimpleBeanPropertyFilter.filterOutAllExcept("id", "name", "address", "created_at", "updated_at");
        FilterProvider filterProvider =
                new SimpleFilterProvider().addFilter("RestaurantInfo", filter);
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(searchRestaurants);
        mappingJacksonValue.setFilters(filterProvider);

        return mappingJacksonValue;

    }

    @GetMapping("/restaurant/{restaurantId}")
    public ResponseEntity<RestaurantVO> getRestaurant(@PathVariable int restaurantId) {
        RestaurantVO restaurant = service.findOne(restaurantId);
        if (restaurant == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(restaurant);
    }

    @PutMapping("/restaurant/{restaurantId}")
    public ResponseEntity<String> updateRestaurant(@PathVariable int restaurantId, @RequestBody RestaurantVO restaurant) {
        if (service.findOne(restaurantId) == null) {
            return ResponseEntity.notFound().build();
        }
        restaurant.setId(restaurantId);
        service.update(restaurant);
        return ResponseEntity.ok().body("update success");
    }

    @DeleteMapping("/restaurant/{restaurantId}")
    public ResponseEntity<String> deleteRestaurant(@PathVariable int restaurantId) {
        if (service.findOne(restaurantId) == null) {
            return ResponseEntity.notFound().build();
        }
        service.delete(restaurantId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/restaurant/{id}/reviews")
    public List<ReviewDTO> getReviews(@PathVariable int id) {
        return service.findReviews(id);
    }

}
