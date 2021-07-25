package com.enashtech.rookieserver.service;

import java.util.List;

import com.enashtech.rookieserver.entity.Customer;
import com.enashtech.rookieserver.handleException.NotFoundExecptionHandle;
import com.enashtech.rookieserver.repository.CustomerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final AddressService addressService;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, AddressService addressService) {
        this.customerRepository = customerRepository;
        this.addressService = addressService;
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer findCustomerByUserName(String username) {
        Customer customer = customerRepository.findByUserName(username);
        if (customer == null)
            throw new NotFoundExecptionHandle("Could not found customer with username: " + username);
        return customer;
    }

    @Override
    public Customer findCustomerById(int id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new NotFoundExecptionHandle("Could not found customer: " + id));
    }

    @Override
    public Customer saveCustomer(Customer newCustomer) {
        return customerRepository.save(newCustomer);
    }

    @Override
    public Customer updateCustomer(Customer newCustomer) {
        int id = newCustomer.getId();
        return customerRepository.findById(id).map(customer -> {
            customer.setFirst_name(newCustomer.getFirst_name());
            customer.setPhone(newCustomer.getPhone());
            customer.setLast_name(newCustomer.getLast_name());
            customer.setBirth_day(newCustomer.getBirth_day());
            if (customer.getAddress() == null) {
                addressService.saveAddress(newCustomer.getAddress());
                customer.setAddress(newCustomer.getAddress());
            } else {
                if (!customer.getAddress().equals(newCustomer.getAddress())) {
                    addressService.saveAddress(newCustomer.getAddress());
                    customer.setAddress(newCustomer.getAddress());
                }
            }
            return customerRepository.save(customer);
        }).orElseThrow(() -> new NotFoundExecptionHandle("Could not found customer: " + id));
    }

    @Override
    public void deleteCustomerById(int id) {
        customerRepository.deleteById(id);
    }

    @Override
    public boolean existsByEmail(String email) {
        return customerRepository.existsByEmail(email);
    }

    @Override
    public boolean existsByPhone(String phone) {
        return customerRepository.existsByPhone(phone);
    }
}
