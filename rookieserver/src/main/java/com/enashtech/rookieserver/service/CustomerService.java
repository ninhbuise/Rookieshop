package com.enashtech.rookieserver.service;

import java.util.List;

import com.enashtech.rookieserver.entity.Customer;

public interface CustomerService {
    List<Customer> getAllCustomers();
    Customer findCustomerById(int id);
    Customer findCustomerByUserName(String username);
    Customer saveCustomer(Customer newCustomer);
    Customer updateCustomer(Customer newCustomer);
    void deleteCustomerById(int id);
    boolean existsByEmail(String email);
    boolean existsByPhone(String phone);
}
