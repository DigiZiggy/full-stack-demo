package com.demo.api.services;

import com.demo.api.dtos.ContractDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ContractServiceImpl implements ContractService {

    @Override
    public ContractDto addContract(ContractDto contractDto) {
        return null;
    }

    @Override
    public ContractDto getContractById(Long id) {
        return null;
    }

    @Override
    public ContractDto updateContract(ContractDto contractDto) {
        return null;
    }

    @Override
    public List<ContractDto> getAllContracts() {
        return List.of();
    }

    @Override
    public List<ContractDto> getAllContractsForCustomer(Long customerId) {
        return List.of();
    }

    @Override
    public String deleteContract(Long id) {
        return "";
    }
}
