package com.enashtech.rookieserver.controller;

import java.util.List;

import javax.validation.Valid;

import com.enashtech.rookieserver.entity.Order;
import com.enashtech.rookieserver.entity.OrderDTO;
import com.enashtech.rookieserver.entity.Product;
import com.enashtech.rookieserver.service.OrderService;
import com.enashtech.rookieserver.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/rookieshop")
public class RookieshopController {
    private final ProductService productService;
    private final OrderService orderService;

    @Autowired
    public RookieshopController(ProductService productService, OrderService orderService) {
        this.productService = productService;
        this.orderService = orderService;
    }

    @GetMapping()
    public List<Product> getAllProducts() {
        return productService.getAllproducts();
    }

    @PostMapping("/order")
    @ResponseBody
    public Order saveOrder(@Valid @RequestBody OrderDTO orderDTO, Authentication authentication) {
        return orderService.saveOrder(orderDTO, authentication.getName());
    }
}
