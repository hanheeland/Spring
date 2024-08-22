package com.kcc.storeservice.domain;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantMenuVO {
    private Integer id;
    private Integer restaurant_id;
    private String name;
    private Integer price;
    private Date created_at;
    private Date updated_at;
}
