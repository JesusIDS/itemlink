package com.walmartdws.ids.DWS.service;

import com.walmartdws.ids.DWS.model.Product;
import com.walmartdws.ids.DWS.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;


    @Override
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public List<Product> getAllProduct() {
        return this.productRepository.findAll();
    }

    @Override
    public Product getProductByBarcode(long id) {
        Optional<Product> productDb = this.productRepository.findById(id);
        return productDb.get();
    }

    @Override
    public Product updateProduct(Product product) {
        Optional<Product> productDb = this.productRepository.findById(product.getId());

        Product productUpdate = productDb.get();
        productUpdate.setBarcode(product.getBarcode());
        productUpdate.setHeight(product.getHeight());
        productUpdate.setLenght(product.getLenght());
        productUpdate.setWeight(product.getWeight());
        productUpdate.setWidth(product.getWidth());
        productRepository.save(productUpdate);
        return productUpdate;

    }

    @Override
    public void deleteProduct(long id) {
        Optional<Product> productDb = this.productRepository.findById(id);
        this.productRepository.delete(productDb.get());
    }
}
