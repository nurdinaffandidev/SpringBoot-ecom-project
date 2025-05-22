package com.nurdinaffandidev.SpringBoot_ecom_project.product.controller;

import com.nurdinaffandidev.SpringBoot_ecom_project.product.model.Product;
import com.nurdinaffandidev.SpringBoot_ecom_project.product.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts() {
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable int id) {
        return new ResponseEntity<>(productService.getProductById(id), HttpStatus.OK);
    }

    @PostMapping("/product")
    public ResponseEntity<Product> addProduct(@Valid @RequestBody Product product) {
        Product addedProduct = productService.addProduct(product);
        return new ResponseEntity<>(addedProduct, HttpStatus.CREATED);
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable int id, @Valid @RequestBody Product updatedProduct) {
        Product productUpdated = productService.updateProduct(id, updatedProduct);
        return new ResponseEntity<>(productUpdated, HttpStatus.OK);
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<String> deleteProductById(@PathVariable int id) {
        Product product = productService.getProductById(id);
        if (product != null) {
            productService.deleteProductById(id);
            return new ResponseEntity<>("Deleted", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
        }
    }

//    @PostMapping("/product_with_image")
//    public ResponseEntity<?> addProductWithImage(@RequestPart Product product, @RequestPart MultipartFile imageFile) {
//        try {
//            Product addedProduct = productService.addProductWithImage(product, imageFile);
//            return new ResponseEntity<>(addedProduct, HttpStatus.CREATED);
//        } catch (Exception exception){
//            return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

//    @GetMapping("/products/{productId}/image")
//    public ResponseEntity<byte[]> getImageByProductId(@PathVariable int productId) {
//        Product product = productService.getProductById(productId);
//        byte[] imageFile = product.getImageData();
//
//        return ResponseEntity.ok()
//                .contentType(MediaType.valueOf(product.getImageType()))
//                .body(imageFile);
//    }

//    @PutMapping("/products/{id}/with_image")
//    public ResponseEntity<String> updateProductWithImage(@PathVariable int id, @RequestPart Product product, @RequestPart MultipartFile imageFile) {
//        Product productToUpdate = null;
//        try {
//            productToUpdate = productService.updateProductWithImage(id, product, imageFile);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        if (productToUpdate != null) {
//            return new ResponseEntity<>("Updated", HttpStatus.OK);
//        } else {
//            return  new ResponseEntity<>("Failed to update", HttpStatus.BAD_REQUEST);
//        }
//    }



}
