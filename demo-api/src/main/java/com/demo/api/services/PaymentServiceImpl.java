package com.demo.api.services;

import com.demo.api.dtos.PaymentDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class PaymentServiceImpl implements PaymentService {

    @Override
    public PaymentDto addPayment(PaymentDto paymentDto, MultipartFile file) {
        return null;
    }

    @Override
    public PaymentDto getPaymentById(Long id) {
        return null;
    }

    @Override
    public PaymentDto updatePayment(PaymentDto paymentDto, MultipartFile file) {
        return null;
    }

    @Override
    public String deletePayment(Long id) {
        return "";
    }

    @Override
    public List<PaymentDto> getAllPayments() {
        return List.of();
    }

    @Override
    public List<PaymentDto> getAllContractPayments(Long contractId) {
        return List.of();
    }

    @Override
    public List<PaymentDto> getAllCustomerPayments(Long customerId) {
        return List.of();
    }

    @Override
    public List<PaymentDto> getAllPaymentsByStatus(String status) {
        return List.of();
    }
}
