package com.nurdinaffandidev.SpringBoot_ecom_project.user.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user_access", uniqueConstraints = {@UniqueConstraint(columnNames = "username")})
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    // Attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false, unique = true)
    private String username;
    @NotBlank(message = "Password cannot be blank")
    @NotNull(message = "Password is required")
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;

    // Constructor
    // custom constructor to init user in DataLoader
    public User(String username, String password, Role role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }
}
