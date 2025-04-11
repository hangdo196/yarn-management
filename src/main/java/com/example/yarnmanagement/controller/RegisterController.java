package com.example.yarnmanagement.controller;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/yarn")
public class RegisterController {
     @GetMapping("/register")
    public ResponseEntity<?> hello() {
        return new ResponseEntity<String>("hi", HttpStatus.OK);
    }
}
