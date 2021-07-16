package com.enashtech.rookieserver.service;

import java.util.List;

import com.enashtech.rookieserver.entity.OrderDetail;

public interface OrderDetailService {
    List<OrderDetail> getListProductsByStoreName(String name);
    OrderDetail saveOrderDetail(OrderDetail orderDetail);
}
