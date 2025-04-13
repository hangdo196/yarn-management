package com.example.yarnmanagement.service;

import com.example.yarnmanagement.entity.Employee;
import com.example.yarnmanagement.exception.EntityNotFoundException;
import com.example.yarnmanagement.exception.UniqueConstraintViolationException;
import com.example.yarnmanagement.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.List;


@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private Argon2PasswordEncoder argon2PasswordEncoder;

    public Employee saveEmployee (Employee employee) {
        employee.setPassword(argon2PasswordEncoder.encode(employee.getPassword()));
        try {
            return employeeRepository.save(employee);
        }
        catch (DataIntegrityViolationException e) {
            throw new UniqueConstraintViolationException("User " + employee.getUsername() + " hat already taken!");
        }
    }

    public List<Employee> findAllEmployee() {
        return employeeRepository.findAll();
    }

    public Employee findById(int employeeId) {
        return employeeRepository.findById(employeeId).orElseThrow(() -> new EntityNotFoundException("Customer doesn't exist!"));

    }

    public void deleteEmployeeById(int employeeId) {
        try {
            employeeRepository.deleteById(employeeId);
        }
        catch (Exception e) {
            System.err.println("Unable to delete this employee!");
        }
    }


    public Employee getEmployeeDetailsById(int id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();

        Employee employee = findById(id);
        if (currentUsername.equals(employee.getUsername()) || hasRole("ROLE_AdminManager")) {
            return employee;
        } else {
            throw new AccessDeniedException("You do not have permission to access this resource.");
        }
    }

    private boolean hasRole(String role) {
        Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        for (GrantedAuthority authority : authorities) {
            if (authority.getAuthority().equals(role)) {
                return true;
            }
        }
        return false;
    }

}
