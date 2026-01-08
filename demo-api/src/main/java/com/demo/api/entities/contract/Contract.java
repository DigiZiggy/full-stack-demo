package com.demo.api.entities.contract;

import com.demo.api.entities.Payment;
import com.demo.api.entities.customer.Customer;
import jakarta.persistence.*;
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
@Entity
public class Contract {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(nullable = false)
    private Long contractId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Column(nullable = false)
    private ContractType type;

    @Column(nullable = false)
    private ContractStatus status;

    @Column(nullable = false)
    @NotNull(message = "This field is required!")
    private Integer termMonths;

    @Column(nullable = false)
    @NotNull(message = "This field is required!")
    private BigDecimal amount;

    @Column(nullable = false)
    private Currency currency;

    @Column(nullable = false)
    @NotNull(message = "This field is required!")
    private Float interestRate;

    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private LocalDate endDate;

    @OneToMany(mappedBy = "contract", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Payment> payments;
}
