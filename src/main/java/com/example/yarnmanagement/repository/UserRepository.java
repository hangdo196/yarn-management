package com.example.yarnmanagement.repository;

import com.example.yarnmanagement.entity.UserYarnApp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserYarnApp, Integer> {
    Optional<UserYarnApp> findByUsername(String username);
}
