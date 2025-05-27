package com.nurdinaffandidev.SpringBoot_ecom_project.product.repository;

import com.nurdinaffandidev.SpringBoot_ecom_project.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
/*
    Note:
    - 'Product' is not a table but a class name
    - 'p' is an alias for Product class
    - '%' is to match any characters
    - ':' before keyword is to indicate search for 'keyword'
 */
    @Query("SELECT p FROM Product p " +
            "WHERE " +
            "LOWER(p.name) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(p.brand) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(p.category) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Product> searchProducts(String keyword) ;
}
