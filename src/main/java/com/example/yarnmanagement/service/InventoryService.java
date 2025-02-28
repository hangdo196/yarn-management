package com.example.yarnmanagement.service;

import com.example.yarnmanagement.entity.Inventory;
import com.example.yarnmanagement.entity.Yarn;
import com.example.yarnmanagement.exception.EntityNotFoundException;
import com.example.yarnmanagement.repository.InventoryRepository;
import com.example.yarnmanagement.repository.YarnRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InventoryService {
    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private YarnRepository yarnRepository;

    public Inventory saveInventory (Inventory inventory) {
        return inventoryRepository.save(inventory);
    }

    public List<Inventory> findAllInventories() {
        return inventoryRepository.findAll();
    }

    public Inventory findInventoryById(int inventoryId) {
        return inventoryRepository.findById(inventoryId).orElseThrow(() -> new EntityNotFoundException("Customer doesn't exist!"));

    }

    public void deleteInventoryById(int inventoryId) {
        try {
            inventoryRepository.deleteById(inventoryId);
        }
        catch (Exception e) {
            System.err.println("Unable to delete this inventory!");
        }
    }

    public void updateInventory (int yarnID, int quantity) {
        Inventory inventory = inventoryRepository.findByYarnID(yarnID).orElseThrow(() -> new IllegalArgumentException("Yarn can't be found"));
        inventory.setQuantity(inventory.getQuantity() - quantity);
        inventoryRepository.save(inventory);
    }
}
