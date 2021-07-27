package com.enashtech.rookieserver.controller;

import java.util.List;

import javax.validation.Valid;

import com.enashtech.rookieserver.entity.Color;
import com.enashtech.rookieserver.entity.Order;
import com.enashtech.rookieserver.entity.OrderDTO;
import com.enashtech.rookieserver.entity.Product;
import com.enashtech.rookieserver.entity.ProductType;
import com.enashtech.rookieserver.entity.Size;
import com.enashtech.rookieserver.service.ColorService;
import com.enashtech.rookieserver.service.OrderService;
import com.enashtech.rookieserver.service.ProductService;
import com.enashtech.rookieserver.service.ProductTypeService;
import com.enashtech.rookieserver.service.SizeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/shop")
public class RookieshopController {
    private final ProductService productService;
    private final OrderService orderService;
    private final ProductTypeService productTypeService;
    private final ColorService colorService;
    private final SizeService sizeService;

    @Autowired
    public RookieshopController(ProductService productService, OrderService orderService,
            ProductTypeService productTypeService, ColorService colorService, SizeService sizeService) {
        this.productService = productService;
        this.orderService = orderService;
        this.productTypeService = productTypeService;
        this.colorService = colorService;
        this.sizeService = sizeService;
    }

    @GetMapping()
    public List<Product> getAllProducts() {
        return productService.getAllproducts();
    }

    @GetMapping("/product/{id}")
    public Product getProductById(@PathVariable int id) {
        return productService.findById(id);
    }

    @GetMapping("/type")
    public List<ProductType> getAllProductTypes() {
        return productTypeService.getAllProductTypes();
    }

    @GetMapping("/color")
    public List<Color> getAllColors() {
        return colorService.getAllColors();
    }

    @GetMapping("/size")
    public List<Size> getAllSize() {
        return sizeService.getAllSize();
    }

    @PostMapping("/order")
    @ResponseBody
    public Order saveOrder(@Valid @RequestBody OrderDTO orderDTO, Authentication authentication) {
        return orderService.saveOrder(orderDTO, authentication.getName());
    }
}
