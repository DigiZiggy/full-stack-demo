package com.demo.api.services;

import com.demo.api.dtos.PaymentDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PaymentService {

    PaymentDto addPayment(PaymentDto paymentDto, MultipartFile file);

    PaymentDto getPaymentById(Long id);

    PaymentDto updatePayment(PaymentDto paymentDto, MultipartFile file);

    String deletePayment(Long id);

    List<PaymentDto> getAllPayments();

    List<PaymentDto> getAllContractPayments(Long contractId);

    List<PaymentDto> getAllCustomerPayments(Long customerId);

    List<PaymentDto> getAllPaymentsByStatus(String status);

}
