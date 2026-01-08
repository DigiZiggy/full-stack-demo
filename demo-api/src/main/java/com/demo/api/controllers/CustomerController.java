package com.demo.api.controllers;

import com.demo.api.dtos.CustomerDto;
import com.demo.api.services.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/customers")
@CrossOrigin
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/add")
    public ResponseEntity<CustomerDto> addCustomer(@RequestPart String customerDtoJson,
                                                   @RequestPart(required = false) MultipartFile file) throws IOException {
        if(file == null || file.isEmpty()) file = null;
        log.info("customerDtoJson = {}", customerDtoJson);
        CustomerDto customerDto = getCustomerDto(customerDtoJson);
        log.info("customerDto = {}", customerDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(customerService.addCustomer(customerDto, file));
    }

    @GetMapping("/all")
    public ResponseEntity<List<CustomerDto>> getAllCustomers() {
        return ResponseEntity.ok(customerService.getAllCustomers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> getCustomerById(@PathVariable Long id) {
        return ResponseEntity.ok(customerService.getCustomerById(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CustomerDto> updateCustomer(@PathVariable Long id,
                                                      @RequestPart(required = false) MultipartFile file,
                                                      @RequestPart String customerDtoJson) throws IOException {
        if(file== null || file.isEmpty()) file = null;
        CustomerDto customerDto = getCustomerDto(customerDtoJson);
        return ResponseEntity.ok(customerService.updateCustomer(id, customerDto, file));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable Long id) throws IOException {
        return ResponseEntity.ok(customerService.deleteCustomer(id));
    }

    private CustomerDto getCustomerDto(String customerDtoJson) {
        CustomerDto customerDto = new CustomerDto();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            customerDto = objectMapper.readValue(customerDtoJson, CustomerDto.class);
        } catch (Exception e) {
            log.info("Exception in converting string to JSON : {}", e.getMessage());
        }

        return customerDto;
    }
}
