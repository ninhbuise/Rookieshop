package com.enashtech.rookieserver.service;

import java.util.List;

import com.enashtech.rookieserver.entity.Order;
import com.enashtech.rookieserver.entity.OrderDTO;

public interface OrderService {
    List<Order> getOrderList();
    Order saveOrder(OrderDTO orderDTO, String customer);
}
