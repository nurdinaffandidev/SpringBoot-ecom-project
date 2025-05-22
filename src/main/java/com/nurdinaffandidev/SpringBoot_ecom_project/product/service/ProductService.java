package com.nurdinaffandidev.SpringBoot_ecom_project.product.service;

import com.nurdinaffandidev.SpringBoot_ecom_project.product.exception.ProductNotFoundException;
import com.nurdinaffandidev.SpringBoot_ecom_project.product.model.Product;
import com.nurdinaffandidev.SpringBoot_ecom_project.product.repository.ProductRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.awt.color.ProfileDataException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(int id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product with id = " + id + " not found."));
    }

    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    public Product updateProduct(int id, Product updatedProduct) {
        Optional<Product> originalProduct = productRepository.findById(id);
        if (originalProduct.isPresent()) {
            Product existingProduct = originalProduct.get();
            // Copy non-null properties from updatedProduct to existingProduct
            BeanUtils.copyProperties(updatedProduct, existingProduct, "id"); // exclude 'id'
            return productRepository.save(existingProduct);
        }
        return null;
    }

    public void deleteProductById(int id) {
        productRepository.deleteById(id);
    }

//    public Product addProductWithImage(Product product, MultipartFile imageFile) throws IOException {
//        product.setImageName(imageFile.getOriginalFilename());
//        product.setImageType(imageFile.getContentType());
//        product.setImageData(imageFile.getBytes());
//        return productRepository.save(product);
//    }

//    public Product updateProductWithImage(int id, Product product, MultipartFile imageFile) throws IOException {
//        product.setImageName(imageFile.getOriginalFilename());
//        product.setImageType(imageFile.getContentType());
//        product.setImageData(imageFile.getBytes());
//        return productRepository.save(product);
//    }
}
