package com.walmartdws.ids.DWS.service;

import com.walmartdws.ids.DWS.model.Product;

import java.util.List;

public interface ProductService {

    Product createProduct(Product product);

    List<Product> getAllProduct();

    Product getProductByBarcode(String barcode);

    Product updateProduct(Product product);

    void deleteProduct(String barcode);

    Product postApi();
}
