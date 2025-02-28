package com.example.yarnmanagement.controller;

import com.example.yarnmanagement.entity.OrderYarnDetail;
import com.example.yarnmanagement.service.OrderYarnDetailService;
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
public class OrderYarnDetailController {
    @Autowired
    private OrderYarnDetailService orderYarnDetailService;

    @Autowired
    private RequestValidationService requestValidationService;

    @GetMapping("/OrderYarnDetails")
    public ResponseEntity<?> findAll() {
        return new ResponseEntity<Iterable<OrderYarnDetail>>(orderYarnDetailService.findAllOrderYarnDetails(), HttpStatus.OK);
    }

    @GetMapping("/OrderYarnDetails/{id}")
    public ResponseEntity<?> findOne(@PathVariable int id) {
        return new ResponseEntity<OrderYarnDetail>(orderYarnDetailService.findOrderYarnDetailById(id), HttpStatus.OK);
    }

    @PostMapping("/OrderYarnDetails")
    public ResponseEntity<?> newOrderYarnDetail(@Valid @RequestBody OrderYarnDetail neworderYarnDetail, BindingResult result) {
        Map<String, String> errorMap = requestValidationService.handleValidationErrors(result);
        if (errorMap != null) return new ResponseEntity<Map<String, String>>(errorMap, HttpStatus.BAD_REQUEST);
        return new ResponseEntity<OrderYarnDetail>(orderYarnDetailService.saveOrderYarnDetail(neworderYarnDetail), HttpStatus.CREATED);
    }

    @PutMapping("/OrderYarnDetails/{id}")
    public ResponseEntity<?> replaceOrderYarnDetail(@PathVariable int id, @Valid @RequestBody OrderYarnDetail neworderYarnDetail, BindingResult result) {
        Map<String, String> errorMap = requestValidationService.handleValidationErrors(result);
        if (errorMap != null) return new ResponseEntity<Map<String, String>>(errorMap, HttpStatus.BAD_REQUEST);
        neworderYarnDetail.setId(id);
        orderYarnDetailService.saveOrderYarnDetail(neworderYarnDetail);
        return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/OrderYarnDetails/{id}")
    public ResponseEntity<?> deleteOrderYarnDetail(@PathVariable int id) {
        orderYarnDetailService.deleteOrderYarnDetailById(id);
        return new ResponseEntity<String>(HttpStatus.OK);
    }
}
