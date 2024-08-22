package com.kcc.storeservice.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewVO {
    private Integer id;
    private Integer restaurant_id;
    private String content;
    private Integer score;
    private Date created_at;
}
