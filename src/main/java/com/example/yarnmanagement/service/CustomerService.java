package com.example.yarnmanagement.service;

import com.example.yarnmanagement.entity.Customer;
import com.example.yarnmanagement.exception.EntityNotFoundException;
import com.example.yarnmanagement.exception.UniqueConstraintViolationException;
import com.example.yarnmanagement.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private Argon2PasswordEncoder argon2PasswordEncoder;

    public Customer saveCustomer (Customer customer) {
        customer.setPassword(argon2PasswordEncoder.encode(customer.getPassword()));
        try {
            return customerRepository.save(customer);
        }
        catch (DataIntegrityViolationException e) {
            throw new UniqueConstraintViolationException("User " + customer.getUsername() + " hat already taken!");
        }
    }

    public List<Customer> findAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer findCustomerById(int customerId) {
        return customerRepository.findById(customerId).orElseThrow(() -> new EntityNotFoundException("Customer doesn't exist!"));
    }

    public void deleteCustomerById(int customerId) {
        try {
            customerRepository.deleteById(customerId);
        }
        catch (Exception e) {
            System.err.println("Unable to delete this customer!");
        }
    }

    /*public Customer findCustomerByUsername (String username) {
        return customerRepository.findCustomerByUsername(username).orElseThrow(() -> new IllegalArgumentException("User doesn't exist"));
    }*/

}
