package com.enashtech.rookieserver.service;

import java.util.List;

import com.enashtech.rookieserver.entity.Product;
import com.enashtech.rookieserver.entity.ProductDTO;

public interface ProductService {
    public List<Product> getAllproducts();
    public Product findById(int id);
    public Product saveProduct(ProductDTO newProduct, String owner);
    public Product updateProduct(Product newProduct, int id);
    public void deleteProduct(int id);
    public List<ProductDTO> getListProductsByNameOwner(String owner);
}
