package com.nurdinaffandidev.SpringBoot_ecom_project.product.exception;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(String message) {
        super(message);
    }
}
