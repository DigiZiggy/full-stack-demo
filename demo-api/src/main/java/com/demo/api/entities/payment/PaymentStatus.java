package com.demo.api.entities.payment;

public enum PaymentStatus {
    PENDING,    // Future payment, not yet due
    DUE,        // Due date reached, awaiting payment
    PAID,       // Successfully paid
    OVERDUE,    // Due date passed, no payment received
    PARTIALLY_PAID,
    CANCELLED
}
