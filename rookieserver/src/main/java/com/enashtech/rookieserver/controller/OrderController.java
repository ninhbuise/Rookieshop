package com.enashtech.rookieserver.controller;

import com.enashtech.rookieserver.entity.Customer;
import com.enashtech.rookieserver.entity.Order;
import com.enashtech.rookieserver.entity.OrderDTO;
import com.enashtech.rookieserver.service.CustomerService;
import com.enashtech.rookieserver.service.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class OrderController {
    private final OrderService orderService;
    private final CustomerService customerService;

    @Autowired
    public OrderController(OrderService orderService, CustomerService customerService) {
        this.orderService = orderService;
        this.customerService = customerService;
    }
    
    @PostMapping("/rookieshop/order")
    @ResponseBody
    public Order saveProduct(@RequestBody OrderDTO orderDTO, Authentication authentication) {
        return orderService.saveOrder(orderDTO, authentication.getName());
    }
}
