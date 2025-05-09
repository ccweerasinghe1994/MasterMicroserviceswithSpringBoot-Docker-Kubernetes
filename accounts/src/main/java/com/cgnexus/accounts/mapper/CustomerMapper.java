package com.cgnexus.accounts.mapper;

import com.cgnexus.accounts.dto.CustomerDTO;
import com.cgnexus.accounts.dto.CustomerDetailsDTO;
import com.cgnexus.accounts.entity.Customer;

public class CustomerMapper {

    public static CustomerDTO toCustomerDTO(Customer customer, CustomerDTO customerDTO) {
        customerDTO.setEmail(customer.getEmail());
        customerDTO.setName(customer.getName());
        customerDTO.setMobileNumber(customer.getMobileNumber());
        return customerDTO;
    }

    public static Customer toCustomer(CustomerDTO customerDTO, Customer customer) {
        customer.setEmail(customerDTO.getEmail());
        customer.setName(customerDTO.getName());
        customer.setMobileNumber(customerDTO.getMobileNumber());
        return customer;
    }

    public static CustomerDetailsDTO toCustomerDetailsDTO(Customer customer, CustomerDetailsDTO customerDetailsDTO) {
        customerDetailsDTO.setEmail(customer.getEmail());
        customerDetailsDTO.setName(customer.getName());
        customerDetailsDTO.setMobileNumber(customer.getMobileNumber());
        return customerDetailsDTO;
    }
}
