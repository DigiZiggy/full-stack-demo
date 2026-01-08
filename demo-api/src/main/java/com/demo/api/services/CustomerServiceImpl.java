package com.demo.api.services;

import com.demo.api.dtos.CustomerDto;
import com.demo.api.entities.customer.Customer;
import com.demo.api.repositories.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService {

    @Value("${project.images}")
    private String path;

    @Value("${base.url}")
    private String baseUrl;

    private final CustomerRepository customerRepository;

    private final FileService fileService;

    public CustomerServiceImpl(CustomerRepository customerRepository, FileService fileService) {
        this.customerRepository = customerRepository;
        this.fileService = fileService;
    }

    @Override
    public CustomerDto addCustomer(CustomerDto customerDto, MultipartFile file) throws IOException {
        if (Objects.nonNull(file) && Files.exists(Paths.get(path + File.separator + file.getOriginalFilename()))) {
            throw new FileAlreadyExistsException("File already exists! Please give another file!");
        }

        if (Objects.nonNull(file)) {
            String uploadedFileName = fileService.uploadFile(path, file);
            String customerPictureUrl = baseUrl + "/api/v1/file/" + uploadedFileName;

            customerDto.setPicture(uploadedFileName);
            customerDto.setPictureUrl(customerPictureUrl);
        }

        Customer customer = convertToCustomer(customerDto);

        Customer savedCustomer = customerRepository.save(customer);

        return convertToCustomerDto(savedCustomer);
    }

    @Override
    public CustomerDto getCustomerById(Long id) {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new RuntimeException("Customer not found with id : " + id));
        return convertToCustomerDto(customer);    }

    @Override
    public CustomerDto getCustomerByContractId(Long contractId) {
        return null;
    }

    @Override
    public CustomerDto updateCustomer(Long id, CustomerDto customerDto, MultipartFile file) throws IOException {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new RuntimeException("Customer not found with id: " + id));

        String customerPicture = customer.getPicture();
        String customerPictureUrl = customer.getPictureUrl();
        if (file != null) {
            CompletableFuture.runAsync(() -> {
                try {
                    Files.deleteIfExists(Paths.get(path + File.separator + customer.getPicture()));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            customerPicture = fileService.uploadFile(path, file);
            customerPictureUrl = baseUrl + "/api/v1/file/" + customerPicture;
        }

        customerDto.setPicture(customerPicture);
        customerDto.setPictureUrl(customerPictureUrl);

        log.info("customerDto  = {}", customerDto);
        // update the customer
        customer.setFirstName(customerDto.getFirstName());
        customer.setLastName(customerDto.getLastName());
        customer.setEmail(customerDto.getEmail());
        customer.setMobile(customerDto.getMobile());
        customer.setAddress(customerDto.getAddress());
        customer.setPassword(customerDto.getPassword());
        customer.setPicture(customerDto.getPicture());
        customer.setPictureUrl(customerDto.getPictureUrl());

        log.info("customer  = {}", customer);

        Customer updatedCustomer = customerRepository.save(customer);

        return convertToCustomerDto(updatedCustomer);
    }

    @Override
    public List<CustomerDto> getAllCustomers() {
        List<Customer> books = customerRepository.findAll();
        return books.stream()
                .map(this::convertToCustomerDto)
                .toList();
    }

    @Override
    public String deleteCustomer(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + id));

        if (Objects.nonNull(customer.getPicture())) {
            try {
                Files.deleteIfExists(Paths.get(path + File.separator + customer.getPicture()));
            } catch (IOException e) {
                log.error("Could not delete image file: {}", e.getMessage());
            }
        }

        customerRepository.delete(customer);
        return "Customer deleted with id: " + id;
    }

    private CustomerDto convertToCustomerDto(Customer customer) {
        return CustomerDto.builder()
                .customerId(customer.getCustomerId())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .email(customer.getEmail())
                .mobile(customer.getMobile())
                .address(customer.getAddress())
                .password(customer.getPassword())
                .picture(customer.getPicture())
                .pictureUrl(customer.getPictureUrl())
                .build();
    }

    private Customer convertToCustomer(CustomerDto customerDto) {
        return Customer.builder()
                .customerId(customerDto.getCustomerId())
                .firstName(customerDto.getFirstName())
                .lastName(customerDto.getLastName())
                .email(customerDto.getEmail())
                .mobile(customerDto.getMobile())
                .address(customerDto.getAddress())
                .password(customerDto.getPassword())
                .picture(customerDto.getPicture())
                .pictureUrl(customerDto.getPictureUrl())
                .build();
    }
}
