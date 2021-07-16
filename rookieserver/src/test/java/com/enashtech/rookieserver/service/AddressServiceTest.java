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

import com.enashtech.rookieserver.entity.Address;
import com.enashtech.rookieserver.handleException.NotFoundExecptionHandle;
import com.enashtech.rookieserver.repository.AddressRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class AddressServiceTest {
    @MockBean
    AddressRepository repo;

    @Autowired
    AddressService service;

    List<Address> list;

    @BeforeEach
    public void setUp() {
        list = new ArrayList<>();
        list.add(new Address(1, "Lam Dong", "Dat Lat", "P9", "Nguyen Trai"));
        list.add(new Address(2, "HCM", "Q10", "P4", "Nguyen Trai"));
        list.add(new Address(3, "Ukiz", "ABC", "P9", "Nguyen Trai"));
    }

    @Test
    public void saveAddress_ReturnAddress() {
        when(repo.save(list.get(0))).thenReturn(list.get(0));
        assertEquals(service.saveAddress(list.get(0)), list.get(0));
        verify(repo, times(1)).save(list.get(0));
    }

    @Test
    public void getAllAddress_ReturnAddressList() {
        when(repo.findAll()).thenReturn(list);
        assertEquals(service.getAllAddresses(), list);
        verify(repo, times(1)).findAll();
    }

    @Test
    public void getAddressById_RetuenAddress() {
        when(repo.findById(3)).thenReturn(Optional.of(list.get(2)));
        Address address = service.getAddressById(3);
        assertEquals("Ukiz", address.getCity());
        verify(repo, times(1)).findById(3);
    }

    @Test
    public void getAddressById_ThrowNotFoundExecption() throws NotFoundExecptionHandle {
        Exception exception = assertThrows(NotFoundExecptionHandle.class, () -> {
            service.getAddressById(1);
        });
        assertEquals("Could not found address: 1", exception.getMessage());
    }

    @Test
    public void deleteAddressById() {
        service.deleteAddressById(1);
        verify(repo).deleteById(any());
    }

    @Test
    public void updateAddress_ReturnAddress() {
        Address address = list.get(0);

        when(repo.save(address)).thenReturn(address);
        when(repo.findById(1)).thenReturn(Optional.of(address));

        address.setCity("Duc Trong");
        service.updateAddress(address);

        address = service.getAddressById(1);
        assertEquals("Duc Trong", address.getCity());
    }

    @Test
    public void updateAddress_ThrowNotFoundExecption() {
        Address address = list.get(0);

        when(repo.save(address)).thenReturn(address);
        when(repo.findById(1)).thenReturn(Optional.of(address));

        address.setId(2);

        Exception exception = assertThrows(NotFoundExecptionHandle.class, () -> {
            service.updateAddress(address);
        });
        
        assertEquals("Could not found address: 2", exception.getMessage());
    }
}
