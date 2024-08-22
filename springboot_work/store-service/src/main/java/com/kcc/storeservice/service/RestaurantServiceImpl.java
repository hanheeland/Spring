package com.kcc.storeservice.service;

import com.kcc.storeservice.domain.RestaurantMenuVO;
import com.kcc.storeservice.domain.RestaurantVO;
import com.kcc.storeservice.domain.ReviewDTO;
import com.kcc.storeservice.mapper.RestaurantMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    @Autowired
    RestaurantMapper mapper;

    @Override
    public List<RestaurantVO> findAll() {
        return mapper.findAll();
    }

    @Override
    public RestaurantVO findOne(int id) {
        return mapper.findOne(id);
    }

    @Override
    @Transactional
    public void save(RestaurantVO restaurant) {
        mapper.save(restaurant);
        if (restaurant.getMenus() != null) {
            for (RestaurantMenuVO menu : restaurant.getMenus()) {
                mapper.saveMenu(menu);
            }
        }
    }

    @Override
    @Transactional
    public void update(RestaurantVO restaurant) {
        mapper.update(restaurant);
        if (restaurant.getMenus() != null) {
            for (RestaurantMenuVO menu : restaurant.getMenus()) {
                menu.setId(restaurant.getId());
                mapper.updateMenu(menu);
            }
        }
    }

    @Override
    @Transactional
    public void delete(int id) {
        mapper.deleteMenu(id);
        mapper.delete(id);
    }

    @Override
    public List<ReviewDTO> findReviews(int id) {
        return mapper.findReviews(id);
    }

}
