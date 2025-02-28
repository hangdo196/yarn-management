package com.example.yarnmanagement.controller;

import com.example.yarnmanagement.entity.Employee;
import com.example.yarnmanagement.service.EmployeeService;
import com.example.yarnmanagement.service.RequestValidationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/yarn")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private RequestValidationService requestValidationService;

    @GetMapping("/employees")
   // @PreAuthorize("hasRole('Role.AdminManager')")
    public ResponseEntity<?> findAll() {
        return new ResponseEntity<Iterable<Employee>>(employeeService.findAllEmployee(), HttpStatus.OK);
    }

    @GetMapping("/employees/{id}")
    //@PreAuthorize("hasRole('Role.Employee') and #id == authentication.name")
    public ResponseEntity<?> findOne(@PathVariable int id) {
        return new ResponseEntity<>(employeeService.getEmployeeDetailsById(id), HttpStatus.OK);
    }

    @PostMapping("/employees")
    public ResponseEntity<?> newEmployee(@Valid @RequestBody Employee newEmployee, BindingResult result) {
        Map<String, String> errorMap = requestValidationService.handleValidationErrors(result);
        if (errorMap != null) return new ResponseEntity<Map<String, String>>(errorMap, HttpStatus.BAD_REQUEST);
        return new ResponseEntity<Employee>(employeeService.saveEmployee(newEmployee), HttpStatus.CREATED);
    }

    @PutMapping("/employees/{id}")
    public ResponseEntity<?> replaceEmployee(@PathVariable int id, @Valid @RequestBody Employee newEmployee, BindingResult result) {
        Map<String, String> errorMap = requestValidationService.handleValidationErrors(result);
        if (errorMap != null) return new ResponseEntity<Map<String, String>>(errorMap, HttpStatus.BAD_REQUEST);
        newEmployee.setId(id);
        employeeService.saveEmployee(newEmployee);
        return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/employees/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable int id) {
        employeeService.deleteEmployeeById(id);
        return new ResponseEntity<String>(HttpStatus.OK);
    }

}
