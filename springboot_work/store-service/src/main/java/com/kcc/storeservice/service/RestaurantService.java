package com.kcc.storeservice.service;

import com.kcc.storeservice.domain.RestaurantVO;
import com.kcc.storeservice.domain.ReviewDTO;

import java.util.List;

public interface RestaurantService {
    public void save(RestaurantVO restaurant);
    public List<RestaurantVO> findAll();
    public RestaurantVO findOne(int id);
    public void update(RestaurantVO restaurant);
    public void delete(int id);
    public List<ReviewDTO> findReviews(int id);
}
