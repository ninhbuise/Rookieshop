package com.enashtech.rookieserver.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.enashtech.rookieserver.entity.Customer;
import com.enashtech.rookieserver.handleException.NotFoundExecptionHandle;
import com.enashtech.rookieserver.repository.CustomerRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class CustomerServiceTest {
    @MockBean
    CustomerRepository repo;

    @Autowired
    CustomerService service;

    List<Customer> list;

    @BeforeEach
    public void setUp() {
        list = new ArrayList<>();
        list.add(new Customer(1, "Ngoc", "Nguyen"));
        list.add(new Customer(2, "NNN", "Nguyen"));
        list.add(new Customer(3, "BCA", "Krzay"));
    }

    @Test
    public void saveCustomer_ReturnCustomer() {
        when(repo.save(list.get(0))).thenReturn(list.get(0));
        assertEquals(service.saveCustomer(list.get(0)), list.get(0));
    }

    @Test
    public void getAllCustomer_ReturnCustomerList() {
        when(repo.findAll()).thenReturn(list);
        assertEquals(service.getAllCustomers(), list);
        verify(repo, times(1)).findAll();
    }

    @Test
    public void getCustomerById_RetuenCustomer() {
        when(repo.findById(1)).thenReturn(Optional.of(list.get(0)));
        Customer Customer = service.findCustomerById(1);
        assertEquals("Ngoc", Customer.getFirst_name());
    }

    @Test
    public void getCustomerById_ThrowNotFoundExecption() throws NotFoundExecptionHandle {
        Exception exception = assertThrows(NotFoundExecptionHandle.class, () -> {
            service.findCustomerById(1);
        });
        assertEquals("Could not found customer: 1", exception.getMessage());
    }

    @Test
    public void deleteCustomerById() {
        service.deleteCustomerById(1);
        verify(repo).deleteById(any());
    }

    @Test
    public void updateCustomer_ReturnCustomer() {
        Customer Customer = list.get(0);

        when(repo.save(Customer)).thenReturn(Customer);
        when(repo.findById(1)).thenReturn(Optional.of(Customer));

        Customer.setFirst_name("Ukiazt");
        service.updateCustomer(Customer);

        Customer = service.findCustomerById(1);
        assertEquals("Ukiazt", Customer.getFirst_name());
    }

    @Test
    public void updateCustomer_ThrowNotFoundExecption() {
        Customer Customer = list.get(0);

        when(repo.save(Customer)).thenReturn(Customer);
        when(repo.findById(1)).thenReturn(Optional.of(Customer));

        Customer.setId(2);

        Exception exception = assertThrows(NotFoundExecptionHandle.class, () -> {
            service.updateCustomer(Customer);
        });
        
        assertEquals("Could not found customer: 2", exception.getMessage());
    }
}
