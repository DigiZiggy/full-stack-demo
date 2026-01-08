package com.demo.api.entities.contract;

public enum ContractStatus {
    DRAFT,              // Initial creation, not yet valid
    PENDING_APPROVAL,   // Awaiting internal or external sign-off
    ACTIVE,             // Contract is in effect
    COMPLETED,          // Contract terms fulfilled naturally
    TERMINATED,         // Ended early (e.g., default or mutual agreement)
    EXPIRED,            // Reached end date without renewal
    CANCELLED           // Voided before becoming active
}
