package com.demo.api.controllers;

import com.demo.api.dtos.ContractDto;
import com.demo.api.services.ContractService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/contracts")
@CrossOrigin
public class ContractController {

    private final ContractService contractService;

    public ContractController(ContractService contractService) {
        this.contractService = contractService;
    }

    @PostMapping("/add")
    public ResponseEntity<ContractDto> addContract(@RequestPart String contractDtoJson) throws IOException {
        log.info("contractDtoJson = {}", contractDtoJson);
        ContractDto contractDto = getContractDto(contractDtoJson);
        log.info("contractDto = {}", contractDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(contractService.addContract(contractDto));
    }

    @GetMapping("/all")
    public ResponseEntity<List<ContractDto>> getAllContracts() {
        return ResponseEntity.ok(contractService.getAllContracts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContractDto> getContractById(@PathVariable Long id) {
        return ResponseEntity.ok(contractService.getContractById(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ContractDto> updateContract(@PathVariable Long id,
                                                      @RequestPart String contractDtoJson) throws IOException {
        ContractDto contractDto = getContractDto(contractDtoJson);
        return ResponseEntity.ok(contractService.updateContract(contractDto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteContract(@PathVariable Long id) throws IOException {
        return ResponseEntity.ok(contractService.deleteContract(id));
    }

    private ContractDto getContractDto(String contractDtoJson) {
        ContractDto contractDto = new ContractDto();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            contractDto = objectMapper.readValue(contractDtoJson, ContractDto.class);
        } catch (Exception e) {
            log.info("Exception in converting string to JSON : {}", e.getMessage());
        }

        return contractDto;
    }
}
