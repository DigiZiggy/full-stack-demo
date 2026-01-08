package com.demo.api.services;

import com.demo.api.dtos.CustomerDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface CustomerService {

    CustomerDto addCustomer(CustomerDto customerDto, MultipartFile file) throws IOException;

    CustomerDto getCustomerById(Long id);

    CustomerDto getCustomerByContractId(Long contractId);

    CustomerDto updateCustomer(Long id, CustomerDto customerDto, MultipartFile file) throws IOException;

    List<CustomerDto> getAllCustomers();

    String deleteCustomer(Long id);
}
