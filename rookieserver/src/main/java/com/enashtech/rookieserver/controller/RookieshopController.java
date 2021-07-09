package com.enashtech.rookieserver.controller;

import java.util.List;

import com.enashtech.rookieserver.entity.Product;
import com.enashtech.rookieserver.entity.ProductDTO;
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
@RequestMapping("/api")
public class RookieshopController {
    private ProductService productService;

    @Autowired
    public RookieshopController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/rookieshop")
    public List<Product> getAllProducts() {
        return productService.getAllproducts();
    }

    @GetMapping("/shop/products")
    @ResponseBody
    public List<ProductDTO> getListProducts(Authentication authentication) {
        return productService.getListProductsByNameOwner(authentication.getName());
    }

    @PostMapping("shop/product")
    @ResponseBody
    public Product saveProduct(@RequestBody ProductDTO newProduct, Authentication authentication) {
        return productService.saveProduct(newProduct, authentication.getName());
    }
}
