package com.example.yarnmanagement.service;

import com.example.yarnmanagement.entity.OrderYarn;
import com.example.yarnmanagement.entity.OrderStatus;
import com.example.yarnmanagement.entity.OrderYarnDetail;
import com.example.yarnmanagement.exception.EntityNotFoundException;
import com.example.yarnmanagement.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private InventoryService inventoryService;

    public boolean validOrder (OrderYarn order) {
        if (order == null) return false;
        for (OrderYarnDetail i : order.getOrderYarnDetails()) {
            if (i.getQuantity() > i.getYarn().getInventory().getQuantity()) return false;
        }
        return true;
    }

    public OrderYarn saveOrder (OrderYarn order) {
        double totalprice = 0;
        int amount = 0;
        for (OrderYarnDetail i : order.getOrderYarnDetails()) {
            if (i.getQuantity() > i.getYarn().getInventory().getQuantity()) throw new IllegalStateException("List Order is invalid!");
            totalprice += i.getQuantity()*i.getYarn().getPrice();
            amount += i.getQuantity();
        }
        order.setTotalPrice(totalprice);
        order.setTotalAmount(amount);
        order.setOrderStatus(OrderStatus.Confirmed);
        return orderRepository.save(order);
    }

    public List<OrderYarn> findAllOrders() {
        return orderRepository.findAll();
    }

    public OrderYarn findOrderById(int orderId) {
        return orderRepository.findById(orderId).orElseThrow(() -> new EntityNotFoundException("Customer doesn't exist!"));

    }

    public void deleteOrderById(int orderId) {
        try {
            orderRepository.deleteById(orderId);
        }
        catch (Exception e) {
            System.err.println("Unable to delete this order!");
        }
    }

    public double getTotalPrice (int orderId) {
        OrderYarn order = findOrderById(orderId);
        double total = 0.0;
        for (OrderYarnDetail i: order.getOrderYarnDetails()) {
            total += i.getQuantity() * i.getYarn().getPrice();
        }
        return total;
    }

    public void processOrder (OrderYarn order) {
        if (!validOrder(order)) throw new IllegalStateException("Order is invalid!");
        double total = 0.0;
        for (OrderYarnDetail i: order.getOrderYarnDetails()) {
            total += i.getQuantity() * i.getYarn().getPrice();
            inventoryService.updateInventory(i.getYarn().getId(), i.getQuantity());
        }
        order.setTotalPrice(total);
        order.setOrderStatus(OrderStatus.Confirmed);
        orderRepository.save(order);
    }
}
