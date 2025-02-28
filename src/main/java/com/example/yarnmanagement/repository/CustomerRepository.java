package com.example.yarnmanagement.repository;

import com.example.yarnmanagement.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    //find a customer by username
    //@Query("SELECT c FROM Customer c where c.username = :username")
    Optional<Customer> findCustomerByUsername(String username);
}
