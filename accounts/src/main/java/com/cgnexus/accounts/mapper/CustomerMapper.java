package com.cgnexus.accounts.mapper;

import com.cgnexus.accounts.dto.CustomerDTO;
import com.cgnexus.accounts.entity.Customer;

public class CustomerMapper {

    public static CustomerDTO toCustomer(Customer customer,CustomerDTO customerDTO){
        customerDTO.setEmail(customer.getEmail());
        customerDTO.setName(customer.getName());
        customerDTO.setMobileNumber(customer.getMobileNumber());
        return customerDTO;
    }

    public static Customer toCustomer(CustomerDTO customerDTO,Customer customer){
        customer.setEmail(customerDTO.getEmail());
        customer.setName(customerDTO.getName());
        customer.setMobileNumber(customerDTO.getMobileNumber());
        return customer;
    }
}
