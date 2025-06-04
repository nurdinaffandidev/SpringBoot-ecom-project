package com.nurdinaffandidev.SpringBoot_ecom_project;

import com.nurdinaffandidev.SpringBoot_ecom_project.product.model.Product;
import com.nurdinaffandidev.SpringBoot_ecom_project.product.repository.ProductRepository;
import com.nurdinaffandidev.SpringBoot_ecom_project.user.model.Role;
import com.nurdinaffandidev.SpringBoot_ecom_project.user.model.User;
import com.nurdinaffandidev.SpringBoot_ecom_project.user.repository.UserRepository;
import com.nurdinaffandidev.SpringBoot_ecom_project.utilities.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Component
public class DataLoader implements ApplicationRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        boolean initializeTableData = false; // set to true to initialize data in table for the very first time

        List<User> initUsers = new ArrayList<>(Arrays.asList(
                new User("nurdin_admin", "na@123", Role.ADMIN),
                new User("nurdin_user", "nu@123", Role.USER)
        ));

        List<Product> initProducts = new ArrayList<>(Arrays.asList(
                new Product("EchoBuds Pro",
                        "Wireless noise-canceling earbuds with voice assistant integration and up to 8 hours of playtime.",
                        "EchoTech",
                        new BigDecimal(129.99),
                        "Electronics",
                        DateUtils.convertToDate("2024-11-15"),
                        true,
                        520),
                new Product("ArcticShield Winter Jacket",
                        "Waterproof, windproof, insulated jacket designed for extreme winter conditions.",
                        "NorthPeak Gear",
                        new BigDecimal(179.99),
                        "Apparel",
                        DateUtils.convertToDate("2023-10-05"),
                        false,
                        0),
                new Product("SnapCam 360",
                        "Compact 360° action camera with 4K video, live streaming, and waterproof casing.",
                        "SnapMotion",
                        new BigDecimal(289.00),
                        "Electronics",
                        DateUtils.convertToDate("2023-06-21"),
                        true,
                        75),
                new Product("SnapCam 360",
                        "Compact 360° action camera with 4K video, live streaming, and waterproof casing.",
                        "SnapMotion",
                        new BigDecimal(289.00),
                        "Electronics",
                        DateUtils.convertToDate("2023-06-21"),
                        true,
                        75),
                new Product("AeroMist Humidifier",
                        "Ultrasonic cool mist humidifier with essential oil tray and night light mode.",
                        "AirLume",
                        new BigDecimal(39.99),
                        "Home Appliances",
                        DateUtils.convertToDate("2024-12-02"),
                        true,
                        410),
                new Product("ArcticShield Everyday Jacket",
                        "Everyday jacket designed for extreme conditions.",
                        "NorthPeak Gear",
                        new BigDecimal(89.99),
                        "Apparel",
                        DateUtils.convertToDate("2023-10-05"),
                        true,
                        10)

        ));

        if (initializeTableData) {
            userRepository.saveAll(initUsers);
            productRepository.saveAll(initProducts);
        }

        System.out.println("DataLoader - init table = " + initializeTableData);
        if (!initializeTableData) {
            System.out.println("Table data initialized using data.sql");
        }
    }
}
