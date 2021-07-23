package com.enashtech.rookieserver.service;

import java.util.List;

import com.enashtech.rookieserver.entity.Product;
import com.enashtech.rookieserver.entity.ProductDTO;
import com.enashtech.rookieserver.entity.Store;
import com.enashtech.rookieserver.handleException.NotFoundExecptionHandle;
import com.enashtech.rookieserver.repository.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService{
    private final ProductRepository productRepository;
    private final StoreService storeService;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, StoreService storeService) {
        this.productRepository = productRepository;
        this.storeService = storeService;
    }

    @Override
    public List<Product> getAllproducts() {
        return productRepository.findAll();
    }

    @Override
    public Product findById(int id) {
        return productRepository.findById(id)
            .orElseThrow(() -> new NotFoundExecptionHandle("Could not found product: " + id));
    }

    @Override
    public Product saveProduct(ProductDTO newProduct, String owner) {
        Store store = storeService.findByNameOwner(owner);
        if(store != null) {
            Product product = new Product(newProduct);
            product.setStore(store);
            return productRepository.save(product);
        } else 
            throw new NotFoundExecptionHandle("Could not found Store by owner: " + owner);
    }

    @Override
    public Product updateProduct(Product newProduct) {
        int id = newProduct.getId();
        return productRepository.findById(id)
            .map(product -> {
                product.setName(newProduct.getName());
                product.setPrice(newProduct.getPrice());
                product.setQuantity(newProduct.getQuantity());
                product.setProductType(newProduct.getProductType());
                product.setColors(newProduct.getColors());
                product.setSizes(newProduct.getSizes());
                product.setProductDetail(newProduct.getProductDetail());
                return productRepository.save(product);
            })
            .orElseThrow(() -> new NotFoundExecptionHandle("Could not found product: " + id));
    }

    @Override
    public void deleteProduct(int id) {
        productRepository.deleteById(id);
    }
    
    @Override
    public List<ProductDTO> getListProductsByNameOwner(String owner) {
        return productRepository.getListProductsByNameOwner(owner);
    }
}
