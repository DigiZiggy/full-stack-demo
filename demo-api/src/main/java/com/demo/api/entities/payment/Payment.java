package com.demo.api.entities;

import com.demo.api.entities.contract.Contract;
import com.demo.api.entities.payment.PaymentStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(nullable = false)
    private Long paymentId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contract_id", nullable = false)
    @ToString.Exclude
    private Contract contract;

    @Column(nullable = false)
    private LocalDate dueDate;

    @Column(nullable = false)
    @NotNull(message = "This field is required!")
    private BigDecimal amountDue;

    private BigDecimal amountPaid;

    private LocalDate paidDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentStatus status;

}