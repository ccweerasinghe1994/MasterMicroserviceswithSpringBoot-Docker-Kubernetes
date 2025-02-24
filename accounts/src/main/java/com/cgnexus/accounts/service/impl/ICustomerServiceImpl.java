package com.cgnexus.accounts.service.impl;

import com.cgnexus.accounts.dto.CustomerDTO;
import com.cgnexus.accounts.entity.Customer;
import com.cgnexus.accounts.exception.ResourceNotFoundException;
import com.cgnexus.accounts.mapper.CustomerMapper;
import com.cgnexus.accounts.repository.CustomerRepository;
import com.cgnexus.accounts.service.ICustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
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
        return saveCustomer(customer);

    }

    /**
     * @param customerId
     * @return
     */
    @Override
    public Customer findCustomerByCustomerId(Long customerId) {
        return customerRepository.findById(customerId).orElseThrow(
                () -> new ResourceNotFoundException("customer", "customerId", customerId.toString())
        );
    }

    private Customer saveCustomer(Customer customer) {
        try {
            Customer saved = customerRepository.save(customer);
            log.info("Customer with id {} saved successfully", saved.getCustomerId());
            return saved;
        } catch (Exception e) {
            log.error("Error saving customer with id {}: {}", customer.getCustomerId(), e.getMessage());
            throw new RuntimeException("Error saving customer with id " + customer.getCustomerId(), e);
        }
    }

    /**
     * @param customer
     * @return customer
     */
    @Override
    public Customer updateCustomer(Customer customer) {
        try {
            Customer updatedCustomer = customerRepository.save(customer);
            log.info("Customer with id {} updated successfully", updatedCustomer.getCustomerId());
            return updatedCustomer;
        } catch (Exception e) {
            log.error("Error updating customer with id {}: {}", customer.getCustomerId(), e.getMessage());
            throw new RuntimeException("Error updating customer with id " + customer.getCustomerId(), e);
        }
    }

    /**
     * @param customer
     */
    @Override
    public void deleteCustomer(Customer customer) {
        try {
            customerRepository.delete(customer);
            log.info("Customer with id {} deleted successfully", customer.getCustomerId());
        } catch (Exception e) {
            log.error("Error deleting customer with id {}: {}", customer.getCustomerId(), e.getMessage());
            throw new RuntimeException("Error deleting customer with id " + customer.getCustomerId(), e);
        }
    }
}
