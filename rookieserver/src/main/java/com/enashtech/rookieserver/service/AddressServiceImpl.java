package com.enashtech.rookieserver.service;

import java.util.List;

import com.enashtech.rookieserver.entity.Address;
import com.enashtech.rookieserver.handleException.NotFoundExecptionHandle;
import com.enashtech.rookieserver.repository.AddressRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepository;

    @Autowired
    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public Address saveAddress(Address newAddress) {
        return addressRepository.save(newAddress);
    }

    @Override
    public Address updateAddress(Address newAddress) throws NotFoundExecptionHandle {
        int id = newAddress.getId();
        return addressRepository.findById(id)
            .map(address -> {
                address.setCity(newAddress.getCity());
                address.setDistrict(newAddress.getDistrict());
                address.setWard(newAddress.getWard());
                address.setStreet(newAddress.getStreet());
                return addressRepository.save(address);
            })
            .orElseThrow(() -> new NotFoundExecptionHandle("Could not found address: " + id));
    }

    @Override
    public List<Address> getAllAddresses() {
        return addressRepository.findAll();
    }

    @Override
    public Address getAddressById(int id) {
        return addressRepository.findById(id)
            .orElseThrow(() -> new NotFoundExecptionHandle("Could not found address: " + id));
    }

    @Override
    public void deleteAddressById(int id) {
        addressRepository.deleteById(id);
    }
    
}
