package com.example.yarnmanagement.service;

import com.example.yarnmanagement.entity.OrderYarnDetail;
import com.example.yarnmanagement.exception.EntityNotFoundException;
import com.example.yarnmanagement.repository.OrderYarnDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class OrderYarnDetailService {

    @Autowired
    private OrderYarnDetailRepository orderYarnDetailRepository;

    public OrderYarnDetail saveOrderYarnDetail (OrderYarnDetail orderYarnDetail) {
        return orderYarnDetailRepository.save(orderYarnDetail);
    }

    public List<OrderYarnDetail> findAllOrderYarnDetails() {
        return orderYarnDetailRepository.findAll();
    }

    public OrderYarnDetail findOrderYarnDetailById(int orderYarnDetailId) {
        return orderYarnDetailRepository.findById(orderYarnDetailId).orElseThrow(() -> new EntityNotFoundException("Customer doesn't exist!"));

    }

    public void deleteOrderYarnDetailById(int orderYarnDetailId) {
        try {
            orderYarnDetailRepository.deleteById(orderYarnDetailId);
        }
        catch (Exception e) {
            System.err.println("Unable to delete this OrderYarn Item!");
        }
    }
}
