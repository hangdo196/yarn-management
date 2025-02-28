package com.example.yarnmanagement.repository;

import com.example.yarnmanagement.entity.Inventory;
import com.example.yarnmanagement.entity.Yarn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Integer> {
    @Query ("SELECT i FROM Inventory i WHERE i.yarn.id = :yarnID")
    Optional<Inventory> findByYarnID(int yarnID);
}
