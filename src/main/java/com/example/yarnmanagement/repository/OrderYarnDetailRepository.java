package com.example.yarnmanagement.repository;

import com.example.yarnmanagement.entity.OrderYarnDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderYarnDetailRepository extends JpaRepository<OrderYarnDetail, Integer> {
}
