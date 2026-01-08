package com.demo.api.dtos;

import com.demo.api.entities.contract.Contract;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerDto {

    private Long customerId;

    private String picture;

    private String pictureUrl;

    @NotBlank(message = "This field is required!")
    private String firstName;

    @NotBlank(message = "This field is required!")
    private String lastName;

    @NotBlank(message = "This field is required!")
    private String email;

    @NotBlank(message = "This field is required!")
    private String  mobile;

    @NotBlank(message = "This field is required!")
    private String address;

    @NotBlank(message = "This field is required!")
    private String password;

    private List<Contract> contracts;
}
