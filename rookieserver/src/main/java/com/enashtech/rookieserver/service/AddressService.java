package com.enashtech.rookieserver.service;

import java.util.List;

import com.enashtech.rookieserver.entity.Address;

public interface AddressService {
    List<Address> getAllAddresses();
    Address saveAddress(Address newAddress);
    Address updateAddress(Address newAddress);
    Address getAddressById(int id);
    void deleteAddressById(int id);
}
