package com.enashtech.rookieserver.service;

import java.util.List;

import com.enashtech.rookieserver.entity.Customer;
import com.enashtech.rookieserver.handleException.CustomerNotFoundException;
import com.enashtech.rookieserver.repository.CustomerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService{
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer findCustomerById(int id) {
        return customerRepository.findById(id)
            .orElseThrow(() -> new CustomerNotFoundException(id));
    }

    @Override
    public Customer addNewCustomer(Customer newCustomer) {
        return customerRepository.save(newCustomer);
    }

    @Override
    public Customer updateCustomer(Customer newCustomer, int id) {
        return customerRepository.findById(id)
            .map(customer ->{
                customer.setFirst_name(newCustomer.getFirst_name());
                customer.setLast_name(newCustomer.getLast_name());
                return customerRepository.save(customer);
            })
            .orElseGet(() ->{
                return customerRepository.save(newCustomer);
            }
        );
    }

    @Override
    public void deleteCustomerById(int id) {
        customerRepository.deleteById(id);
    }

    @Override
    public boolean existsByEmail(String email){
        return customerRepository.existsByEmail(email);
    }

    @Override
    public boolean existsByPhone(String phone){
        return customerRepository.existsByPhone(phone);
    }
}
