package com.demo.api.dtos;

import com.demo.api.entities.Payment;
import com.demo.api.entities.contract.ContractStatus;
import com.demo.api.entities.contract.ContractType;
import com.demo.api.entities.contract.Currency;
import com.demo.api.entities.customer.Customer;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContractDto {

    private Long contractId;

    private Customer customer;

    private ContractType type;

    private ContractStatus status;

    @NotNull(message = "This field is required!")
    private Integer termMonths;

    @NotNull(message = "This field is required!")
    private BigDecimal amount;

    private Currency currency;

    @NotNull(message = "This field is required!")
    private BigDecimal interestRate;

    private LocalDate startDate;

    private LocalDate endDate;

    private List<Payment> payments;
}
