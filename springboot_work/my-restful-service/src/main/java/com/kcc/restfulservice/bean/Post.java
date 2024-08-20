package com.kcc.restfulservice.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String description;

    // LAZY는 필요한 데이터만 보여지도록
    @ManyToOne(fetch = FetchType.LAZY) // 다대일
    @JsonIgnore
    private User user; // 관계를 설정하기 위해 작성함
}
