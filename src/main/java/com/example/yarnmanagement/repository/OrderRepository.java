package com.example.yarnmanagement.repository;

import com.example.yarnmanagement.entity.OrderYarn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<OrderYarn, Integer> {
}
