package com.nurdinaffandidev.SpringBoot_ecom_project.product.repository;

import com.nurdinaffandidev.SpringBoot_ecom_project.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
