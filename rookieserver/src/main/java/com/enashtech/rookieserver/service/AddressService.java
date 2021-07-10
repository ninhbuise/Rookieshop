package com.enashtech.rookieserver.service;

import com.enashtech.rookieserver.entity.Address;

public interface AddressService {
    Address saveAddress(Address newAddress);
    Address updateAddress(Address newAddress, int id);
}
