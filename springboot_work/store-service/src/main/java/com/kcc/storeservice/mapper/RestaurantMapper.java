package com.kcc.storeservice.mapper;

import com.kcc.storeservice.domain.RestaurantMenuVO;
import com.kcc.storeservice.domain.RestaurantVO;
import com.kcc.storeservice.domain.ReviewDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RestaurantMapper {
    public void save(RestaurantVO restaurant);
    public void saveMenu(RestaurantMenuVO menu);
    public List<RestaurantVO> findAll();
    public RestaurantVO findOne(int id);
    public void update(RestaurantVO restaurant);
    public void updateMenu(RestaurantMenuVO menu);
    public void delete(int id);
    public void deleteMenu(int id);
    public List<ReviewDTO> findReviews(int id);
}
