package com.demo.api.dtos;

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
public class PaymentDto {

    private Long paymentId;

    private Contract contract;

    private LocalDate dueDate;

    @NotNull(message = "This field is required!")
    private BigDecimal amountDue;

    private BigDecimal amountPaid;

    private LocalDate paidDate;

    @Enumerated(EnumType.STRING)
    private PaymentStatus status;
}
