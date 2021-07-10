package com.enashtech.rookieserver.service;

import com.enashtech.rookieserver.entity.Address;
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
    public Address updateAddress(Address newAddress, int id) {
        return addressRepository.findById(id)
            .map(address ->{
                address.setCity(newAddress.getCity());
                address.setDistrict(newAddress.getDistrict());
                address.setWard(newAddress.getWard());
                address.setStreet(newAddress.getStreet());
                return addressRepository.save(address);
            })
            .orElseGet(() ->{
                return addressRepository.save(newAddress);
            }
        );
    }
    
}
