package com.enashtech.rookieserver.service;

import com.enashtech.rookieserver.entity.Order;
import com.enashtech.rookieserver.entity.OrderDTO;

public interface OrderService {
    Order saveOrder(OrderDTO orderDTO, String customer);
}
