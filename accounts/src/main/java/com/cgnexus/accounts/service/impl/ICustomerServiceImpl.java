package com.cgnexus.accounts.service.impl;

import com.cgnexus.accounts.dto.CustomerDTO;
import com.cgnexus.accounts.entity.Customer;
import com.cgnexus.accounts.exception.ResourceNotFoundException;
import com.cgnexus.accounts.mapper.CustomerMapper;
import com.cgnexus.accounts.repository.CustomerRepository;
import com.cgnexus.accounts.service.ICustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ICustomerServiceImpl implements ICustomerService {

    private final CustomerRepository customerRepository;


    @Override
    public Customer fetchCustomerByMobileNumber(String mobileNumber) {
        return customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("customer", "mobileNumber", mobileNumber)
        );
    }

    @Override
    public boolean isCustomerExists(String mobileNumber) {
        return customerRepository.existsByMobileNumber(mobileNumber);
    }

    @Override
    public Customer createCustomer(CustomerDTO customerDTO) {
        Customer customer = CustomerMapper.toCustomer(customerDTO, new Customer());
        customer.setCreatedAt(LocalDateTime.now());
        customer.setCreatedBy("Anonymous");
        return customerRepository.save(customer);

    }
}
