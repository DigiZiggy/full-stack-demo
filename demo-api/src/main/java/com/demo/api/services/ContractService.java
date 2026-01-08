package com.demo.api.services;

import com.demo.api.dtos.ContractDto;

import java.util.List;

public interface ContractService {

    ContractDto addContract(ContractDto contractDto);

    ContractDto getContractById(Long id);

    ContractDto updateContract(ContractDto contractDto);

    List<ContractDto> getAllContracts();

    List<ContractDto> getAllContractsForCustomer(Long customerId);

    String deleteContract(Long id);
}
