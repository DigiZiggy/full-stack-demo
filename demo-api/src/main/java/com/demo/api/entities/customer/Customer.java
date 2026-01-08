package com.demo.api.entities.customer;

import com.demo.api.entities.contract.Contract;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(nullable = false)
    private Long customerId;

    private String picture;

    private String pictureUrl;

    @Column(nullable = false)
    @NotBlank(message = "This field is required!")
    private String firstName;

    @Column(nullable = false)
    @NotBlank(message = "This field is required!")
    private String lastName;

    @Column(nullable = false)
    @NotBlank(message = "This field is required!")
    private String email;

    @Column(nullable = false)
    @NotBlank(message = "This field is required!")
    private String  mobile;

    @Column(nullable = false)
    @NotBlank(message = "This field is required!")
    private String address;

    @Column(nullable = false)
    @NotBlank(message = "This field is required!")
    private String password;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Contract> contracts;
}
