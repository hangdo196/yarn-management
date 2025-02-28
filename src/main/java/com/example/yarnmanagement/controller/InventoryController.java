package com.example.yarnmanagement.controller;


import com.example.yarnmanagement.entity.Inventory;
import com.example.yarnmanagement.service.InventoryService;
import com.example.yarnmanagement.service.RequestValidationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/yarn")
public class InventoryController {
    @Autowired
    private InventoryService inventoryService;

    @Autowired
    private RequestValidationService requestValidationService;

    @GetMapping("/inventories")
    public ResponseEntity<?> findAll() {
        return new ResponseEntity<Iterable<Inventory>>(inventoryService.findAllInventories(), HttpStatus.OK);
    }

    @GetMapping("/inventories/{id}")
    public ResponseEntity<?> findById(@PathVariable int id) {
        return new ResponseEntity<Inventory>(inventoryService.findInventoryById(id), HttpStatus.OK);
    }

    @PostMapping("/inventories")
    public ResponseEntity<?> newInventory(@Valid @RequestBody Inventory newinventory, BindingResult result) {
        Map<String, String> errorMap = requestValidationService.handleValidationErrors(result);
        if (errorMap != null) return new ResponseEntity<Map<String, String>>(errorMap, HttpStatus.BAD_REQUEST);
        return new ResponseEntity<Inventory>(inventoryService.saveInventory(newinventory), HttpStatus.CREATED);
    }

    @PutMapping("/inventories/{id}")
    public ResponseEntity<?> replaceInventory(@PathVariable int id, @Valid @RequestBody Inventory newinventory, BindingResult result) {
        Map<String, String> errorMap = requestValidationService.handleValidationErrors(result);
        if (errorMap != null) return new ResponseEntity<Map<String, String>>(errorMap, HttpStatus.BAD_REQUEST);
        newinventory.setId(id);
        inventoryService.saveInventory(newinventory);
        return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/inventories/{id}")
    public ResponseEntity<?> deleteInventory(@PathVariable int id) {
        inventoryService.deleteInventoryById(id);
        return new ResponseEntity<String>(HttpStatus.OK);
    }
}
