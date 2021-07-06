package com.enashtech.rookieserver.service;

import java.util.List;

import com.enashtech.rookieserver.entity.Product;
import com.enashtech.rookieserver.repository.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService{
    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getAllproducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(int id) {
        return productRepository.getById(id);
    }

    @Override
    public Product addNewProduct(Product newProduct) {
        return productRepository.save(newProduct);
    }

    @Override
    public Product updateProduct(Product newProduct, int id) {
        return productRepository.findById(id)
            .map(product ->{
                product.setName(newProduct.getName());
                product.setPrice(newProduct.getPrice());
                product.setQuantity(newProduct.getQuantity());
                product.setProductType(newProduct.getProductType());
                product.setStore(newProduct.getStore());
                product.setColors(newProduct.getColors());
                product.setSizes(newProduct.getSizes());
                product.setProductDetail(newProduct.getProductDetail());
                return productRepository.save(product);
            })
            .orElseGet(()->{
                newProduct.setId(id);
                return productRepository.save(newProduct);
            });
    }

    @Override
    public void deleteProduct(int id) {
        productRepository.deleteById(id);
    }
    
}
