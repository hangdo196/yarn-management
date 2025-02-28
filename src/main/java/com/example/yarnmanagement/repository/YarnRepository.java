package com.example.yarnmanagement.repository;

import com.example.yarnmanagement.entity.Yarn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface YarnRepository extends JpaRepository<Yarn, Integer> {
}
