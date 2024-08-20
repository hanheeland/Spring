package com.kcc.restfulservice.repository;

import com.kcc.restfulservice.bean.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// 넣을 객체, 주키의 데이터 타입
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}
