package com.enashtech.rookieserver.controller;

import java.util.List;

import javax.validation.Valid;

import com.enashtech.rookieserver.entity.*;
import com.enashtech.rookieserver.service.CustomerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/")
public class CustomerController {
    private CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/customers")
    List<Customer> getAllCustomer() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/customer/{id}")
    Customer getCustomerById(@PathVariable int id) {
        return customerService.findCustomerById(id);
    }

    @PostMapping("/customer")
    Customer saveCustomer(@Valid @RequestBody Customer newCustomer) {
        return customerService.saveCustomer(newCustomer);
    }

    @PutMapping("/customer/{id}")
    Customer updatCustomer(@Valid @RequestBody Customer newCustomer) {
        return customerService.updateCustomer(newCustomer);
    }

    @GetMapping("/customer")
    @ResponseBody
    Customer getUserByUsername(Authentication authentication) {
        return customerService.findCustomerByUserName(authentication.getName());
    }
}
