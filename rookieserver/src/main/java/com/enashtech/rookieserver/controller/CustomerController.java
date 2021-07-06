package com.enashtech.rookieserver.controller;

import java.util.List;

import com.enashtech.rookieserver.entity.Customer;
import com.enashtech.rookieserver.service.CustomerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/")
public class CustomerController {
    private CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService){
        this.customerService = customerService;
    }

    @GetMapping("/customers")
    public List<Customer> getAllCustomer(){
        return customerService.getAllCustomers();
    }

    @GetMapping("/customer/{id}")
    public Customer getCustomerById(@PathVariable int id){
        return customerService.findCustomerById(id);
    }

    @PostMapping("/customer")
    public Customer addNewCustomer(@RequestBody Customer newCustomer){
        return customerService.addNewCustomer(newCustomer);
    }

    @PutMapping("/customer")
    public Customer updatCustomer(@RequestBody Customer newCustomer, @PathVariable int id){
        return customerService.updateCustomer(newCustomer, id);
    }
}
