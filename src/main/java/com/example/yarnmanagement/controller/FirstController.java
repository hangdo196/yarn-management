package com.example.yarnmanagement.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/yarn")
public class FirstController {
    @GetMapping("/welcoming")
    public ResponseEntity<?> sayHello(Authentication auth) {
        if(auth != null) return new ResponseEntity<String> ("Dear " + auth.getName() + ", welcome to yarn world and hope you enjoy this journey ^^", HttpStatus.OK);
        return new ResponseEntity<String>("Welcome to yarn world, please create an account to experience more >.<", HttpStatus.OK);
    }

}
