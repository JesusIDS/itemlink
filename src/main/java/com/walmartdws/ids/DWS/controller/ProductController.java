package com.walmartdws.ids.DWS.controller;

import com.walmartdws.ids.DWS.model.Product;
import com.walmartdws.ids.DWS.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/DWS")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/allproducts")
    public ResponseEntity<?> getAllProduct(){
        List<Product> lista = productService.getAllProduct();
        if(lista.isEmpty()){
            return new ResponseEntity<>("Sin productos en la Base de Datos", HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok().body(productService.getAllProduct());
    }

    @GetMapping("/product/{barcode}")
    public ResponseEntity<Product> getProductByBarcode(@PathVariable String barcode) {
        return ResponseEntity.ok().body(productService.getProductByBarcode(barcode));
    }

    @PostMapping("/newproduct")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        return ResponseEntity.ok().body(this.productService.createProduct(product));
    }

    @PutMapping("/update/{barcode}")
    public ResponseEntity<Product> updateProduct(@PathVariable String barcode, @RequestBody Product product) {
        product.setBarcode(barcode);
        return ResponseEntity.ok().body(this.productService.updateProduct(product));
    }

    @DeleteMapping("/delete/{barcode}")
    public HttpStatus deleteProduct(@PathVariable String barcode) {
        this.productService.deleteProduct(barcode);
        return HttpStatus.OK;
    }

    @PostMapping(value = "/API")
    private ResponseEntity<Product> getApi() {
        return ResponseEntity.ok().body(this.productService.postApi());
    }

}
