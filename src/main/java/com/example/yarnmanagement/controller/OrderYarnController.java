package com.example.yarnmanagement.controller;

import com.example.yarnmanagement.entity.OrderYarn;
import com.example.yarnmanagement.service.OrderService;
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
public class OrderYarnController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private RequestValidationService requestValidationService;

    @GetMapping("/orders")
    public ResponseEntity<?> findAll() {
        return new ResponseEntity<Iterable<OrderYarn>>(orderService.findAllOrders(), HttpStatus.OK);
    }

    @GetMapping("/orders/{id}")
    public ResponseEntity<?> findOne(@PathVariable int id) {
        return new ResponseEntity<OrderYarn>(orderService.findOrderById(id), HttpStatus.OK);
    }

    @PostMapping("/orders")
    public ResponseEntity<?> newOrder(@Valid @RequestBody OrderYarn newOrderYarn, BindingResult result) {
        Map<String, String> errorMap = requestValidationService.handleValidationErrors(result);
        if (errorMap != null) return new ResponseEntity<Map<String, String>>(errorMap, HttpStatus.BAD_REQUEST);
        return new ResponseEntity<OrderYarn>(orderService.saveOrder(newOrderYarn), HttpStatus.CREATED);
    }

    @PutMapping("/orders/{id}")
    public ResponseEntity<?> replaceOrder(@PathVariable int id, @Valid @RequestBody OrderYarn neworderYarn, BindingResult result) {
        Map<String, String> errorMap = requestValidationService.handleValidationErrors(result);
        if (errorMap != null) return new ResponseEntity<Map<String, String>>(errorMap, HttpStatus.BAD_REQUEST);
        neworderYarn.setId(id);
        orderService.saveOrder(neworderYarn);
        return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/orders/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable int id) {
        orderService.deleteOrderById(id);
        return new ResponseEntity<String>(HttpStatus.OK);
    }

}
