package com.example.yarnmanagement;

import com.example.yarnmanagement.entity.*;
import com.example.yarnmanagement.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.util.List;

@SpringBootApplication
public class YarnManagementApplication {
    @Autowired
    private CustomerService customerService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private InventoryService inventoryService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private YarnService yarnService;

    @Autowired
    private OrderYarnDetailService orderYarnDetailService;

    public static void main(String[] args) {
        SpringApplication.run(YarnManagementApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void execCodeAfterStartup() {
        //Tips: set all the attributes before saving entities, in order to avoid detached data.
        //test data
        Yarn yarn1 = new Yarn(Material.AnimalFiber, "White", 100.0, WeightType.Lace, 10, 400);
        Yarn yarn2 = new Yarn(Material.SyntheticFiber, "Black", 80.0, WeightType.Fingering, 7, 400);

        Customer customer1 = new Customer("Nguyen", "An", "nguyenan123@gmail.com", "na123", "Nguyenan0619", "Street A", 1,"Munich");
        Customer customer2 = new Customer("Do", "Ha", "doha567@gmail.com", "dh567", "DoHa9966", "Street B", 2, "Munich");

        Employee employee1 = new Employee("Chu", "Lin", "chulin789@gmail.com", "chulin789", "Chulin333");
        Employee employee2 = new Employee("Tran", "Uyen", "tranuyen223@gmail.com", "tr.uyen223", "Tranuyen2203");

        Inventory inventory1 = new Inventory(200);
        Inventory inventory2 = new Inventory(180);


        OrderYarnDetail orderYarnDetail1 = new OrderYarnDetail(10);
        OrderYarnDetail orderYarnDetail2 = new OrderYarnDetail(20);
        OrderYarnDetail orderYarnDetail3 = new OrderYarnDetail(150);

        OrderYarn order1 = new OrderYarn();
        OrderYarn order2 = new OrderYarn();
        OrderYarn order3 = new OrderYarn();

        //One-to-one
        yarn1.setInventory(inventory1);
        inventory1.setYarn(yarn1);

        yarn2.setInventory(inventory2);
        inventory2.setYarn(yarn2);


        orderYarnDetail3.setYarn(yarn2);
        yarn2.setOrderYarnDetails(List.of(orderYarnDetail2));

        //One-to-Many & Many-to-One
        orderYarnDetail1.setYarn(yarn1);
        orderYarnDetail2.setYarn(yarn1);
        orderYarnDetail3.setYarn(yarn2);
        yarn1.setOrderYarnDetails(List.of(orderYarnDetail1, orderYarnDetail2));
        yarn2.setOrderYarnDetails(List.of(orderYarnDetail2));



        order1.setCustomer(customer1);
        order2.setCustomer(customer1);
        order3.setCustomer(customer2);
        customer1.setOrders(List.of(new OrderYarn[]{order1, order2}));
        customer2.setOrders(List.of(new OrderYarn[]{order3}));

        orderYarnDetail1.setOrder(order1);
        orderYarnDetail2.setOrder(order1);
        orderYarnDetail3.setOrder(order2);
        orderYarnDetail1.setOrder(order3);
        order1.setOrderYarnDetails(List.of(new OrderYarnDetail[] {orderYarnDetail1, orderYarnDetail2}));
        order2.setOrderYarnDetails(List.of(new OrderYarnDetail[] {orderYarnDetail3}));
        order3.setOrderYarnDetails(List.of(new OrderYarnDetail[] {orderYarnDetail1}));


        employee1.promoteToAdmin();


        orderService.saveOrder(order1);
        orderService.saveOrder(order2);
        orderService.saveOrder(order3);
        inventoryService.saveInventory(inventory1);
        inventoryService.saveInventory(inventory2);
        yarnService.saveYarn(yarn1);
        yarnService.saveYarn(yarn2);
        orderYarnDetailService.saveOrderYarnDetail(orderYarnDetail1);
        orderYarnDetailService.saveOrderYarnDetail(orderYarnDetail2);
        orderYarnDetailService.saveOrderYarnDetail(orderYarnDetail3);
        customerService.saveCustomer(customer1);
        customerService.saveCustomer(customer2);
        employeeService.saveEmployee(employee1);
        employeeService.saveEmployee(employee2);


        // System.out.println(yarn1);
        // System.out.println(yarn2);
        // System.out.println(customer1);
        // System.out.println(customer2);
        // System.out.println(order1);
        // System.out.println(employee1.toString());
        // System.out.println(employee2.toString());
    }
}
