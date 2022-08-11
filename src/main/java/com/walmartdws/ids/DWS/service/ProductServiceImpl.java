package com.walmartdws.ids.DWS.service;

import com.walmartdws.ids.DWS.exception.ResourceNotFoundException;
import com.walmartdws.ids.DWS.model.Product;
import com.walmartdws.ids.DWS.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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
    public Product getProductByBarcode(String barcode) {
        Optional<Product> productDb = this.productRepository.findById(barcode);

        if (productDb.isPresent()) {
            return productDb.get();
        } else {
            throw new ResourceNotFoundException("Record not found with id : " + barcode);
        }
    }

    @Override
    public Product updateProduct(Product product) {
        Optional<Product> productDb = this.productRepository.findById(product.getBarcode());

        if(productDb.isPresent()) {
            Product productUpdate = productDb.get();
            productUpdate.setBarcode(product.getBarcode());
            productUpdate.setHeight(product.getHeight());
            productUpdate.setLenght(product.getLenght());
            productUpdate.setWeight(product.getWeight());
            productUpdate.setWidth(product.getWidth());
            productRepository.save(productUpdate);
            return productUpdate;
        } else {
            throw new ResourceNotFoundException("Record not found with id : " + product.getBarcode());
        }

    }

    @Override
    public void deleteProduct(String barcode) {
        Optional<Product> productDb = this.productRepository.findById(barcode);
        if (productDb.isPresent()) {
            this.productRepository.delete(productDb.get());
        } else {
            throw new ResourceNotFoundException("Record not found with id : " + barcode);
        }
    }

    @Override
    public Product postApi() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://hello-world-rest-api-master.azurewebsites.net/dws";
        Product product = restTemplate.postForObject(url, "", Product.class);
        return productRepository.save(product);
    }
}
