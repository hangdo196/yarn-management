package com.example.yarnmanagement.controller;

import com.example.yarnmanagement.entity.Yarn;
import com.example.yarnmanagement.service.RequestValidationService;
import com.example.yarnmanagement.service.YarnService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.naming.Binding;
import java.util.Map;

@RestController
@RequestMapping("/yarn")
public class YarnController {
    @Autowired
    private YarnService yarnService;

    @Autowired
    private RequestValidationService requestValidationService;

    @GetMapping("/yarns")
    public ResponseEntity<?> findAll() {
        return new ResponseEntity<Iterable<Yarn>>(yarnService.findAllYarns(), HttpStatus.OK);
    }

    @GetMapping("/yarns/{id}")
    public ResponseEntity<?> findOne(@PathVariable int id) {
        return new ResponseEntity<Yarn>(yarnService.findYarnById(id), HttpStatus.OK);
    }

    @PostMapping("/yarns")
    public ResponseEntity<?> newYarn(@Valid @RequestBody Yarn newyarn, BindingResult result) {
        Map<String, String> errorMap = requestValidationService.handleValidationErrors(result);
        if (errorMap != null) return new ResponseEntity<Map<String, String>>(errorMap, HttpStatus.BAD_REQUEST);
        return new ResponseEntity<Yarn>(yarnService.saveYarn(newyarn), HttpStatus.CREATED);
    }

    @PutMapping("/yarns/{id}")
    public ResponseEntity<?> replaceYarn(@PathVariable int id, @Valid @RequestBody Yarn newYarn, BindingResult result) {
        Map<String, String> errorMap = requestValidationService.handleValidationErrors(result);
        if (errorMap != null) return new ResponseEntity<Map<String, String>>(errorMap, HttpStatus.BAD_REQUEST);
        newYarn.setId(id);
        yarnService.saveYarn(newYarn);
        return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/yarns/{id}")
    public ResponseEntity<?> deleteYarnById(@PathVariable int id) {
        yarnService.deleteYarnById(id);
        return new ResponseEntity<String>(HttpStatus.OK);
    }
}
