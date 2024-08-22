package com.kcc.storeservice.domain;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantVO {
    private Integer id;
    @Size(min = 2, message = "이름은 2글자 이상 입력해 주세요.")
    private String name;
    @Max(30)
    private String address;
    private Date created_at;
    private Date updated_at;
    private List<RestaurantMenuVO> menus;
}
