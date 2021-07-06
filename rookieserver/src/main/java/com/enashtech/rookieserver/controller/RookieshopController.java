package com.enashtech.rookieserver.controller;

import java.util.List;

import com.enashtech.rookieserver.entity.Product;
import com.enashtech.rookieserver.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/rookieshop")
public class RookieshopController {
    private ProductService productService;

    @Autowired
    public RookieshopController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping()
    public List<Product> getAllProducts(){
        return productService.getAllproducts();
    }
}
