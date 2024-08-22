package com.kcc.storeservice.domain;

import com.fasterxml.jackson.annotation.JsonFilter;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonFilter("RestaurantInfo")
public class SearchRestaurantVO {
    private Integer id;
    private String name;
    private String address;
    private Date created_at;
    private Date updated_at;
    private List<RestaurantMenuVO> menus;
}
