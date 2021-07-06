package com.enashtech.rookieserver.service;

import java.util.List;

import com.enashtech.rookieserver.entity.Product;

public interface ProductService {
    public List<Product> getAllproducts();
    public Product getProductById(int id);
    public Product addNewProduct(Product newProduct);
    public Product updateProduct(Product newProduct, int id);
    public void deleteProduct(int id);
}
