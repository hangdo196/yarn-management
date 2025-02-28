package com.example.yarnmanagement.controller;

import com.example.yarnmanagement.entity.Customer;
import com.example.yarnmanagement.service.CustomerService;
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
public class CustomerController {
    @Autowired
    private RequestValidationService requestValidationService;

    @Autowired
    private CustomerService customerService;

    @GetMapping("/customers")
    public ResponseEntity<?> findAll() {
        return new ResponseEntity<Iterable<Customer>>(customerService.findAllCustomers(), HttpStatus.OK);
    }

    @GetMapping("/customers/{id}")
    public ResponseEntity<?> findOne(@PathVariable int id) {
        return new ResponseEntity<Customer>(customerService.findCustomerById(id), HttpStatus.OK);
    }

    @PostMapping("/customers")
    public ResponseEntity<?> newCustomer(@Valid @RequestBody Customer newCustomer, BindingResult result) {
        Map<String, String> errorMap = requestValidationService.handleValidationErrors(result);
        if (errorMap != null) return new ResponseEntity<Map<String, String>>(errorMap, HttpStatus.BAD_REQUEST);
        return new ResponseEntity<Customer>(customerService.saveCustomer(newCustomer), HttpStatus.CREATED);
    }

    @PutMapping("/customers/{id}")
    public ResponseEntity<?> replaceCustomer(@PathVariable int id, @Valid @RequestBody Customer newCustomer, BindingResult result) {
        Map<String, String> errorMap = requestValidationService.handleValidationErrors(result);
        if (errorMap != null) return new ResponseEntity<Map<String, String>>(errorMap, HttpStatus.BAD_REQUEST);
        newCustomer.setId(id);
        customerService.saveCustomer(newCustomer);
        return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/customers/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable int id) {
        customerService.deleteCustomerById(id);
        return new ResponseEntity<String>(HttpStatus.OK);
    }


    /*@GetMapping("/customers/{username}")
    public ResponseEntity<?> findCustomerByUsername (@PathVariable String username) {
        return new ResponseEntity<Customer>(customerService.findCustomerByUsername(username), HttpStatus.OK);
    }*/



}
