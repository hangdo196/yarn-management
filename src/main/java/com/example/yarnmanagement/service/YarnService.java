package com.example.yarnmanagement.service;

import com.example.yarnmanagement.entity.Yarn;
import com.example.yarnmanagement.exception.EntityNotFoundException;
import com.example.yarnmanagement.repository.YarnRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class YarnService{

    @Autowired
    private YarnRepository yarnRepository;

    public Yarn saveYarn (Yarn yarn) {
        return yarnRepository.save(yarn);
    }

    public List<Yarn> findAllYarns() {
        return yarnRepository.findAll();
    }

    public Yarn findYarnById(int yarnId) {
        return yarnRepository.findById(yarnId).orElseThrow(() -> new EntityNotFoundException("Customer doesn't exist!"));

    }

    public void deleteYarnById(int yarnId) {
        try {
            yarnRepository.deleteById(yarnId);
        }
        catch (Exception e) {
            System.err.println("Unable to delete this yarn!");
        }
    }

}
