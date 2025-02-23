package com.cgnexus.accounts.service;

import com.cgnexus.accounts.dto.CustomerDTO;
import com.cgnexus.accounts.entity.Customer;

public interface ICustomerService {

    Customer fetchCustomerByMobileNumber(String mobileNumber);

    boolean isCustomerExists(String mobileNumber);

    Customer createCustomer(CustomerDTO customerDTO);

    Customer findCustomerByCustomerId(Long customerId);

    Customer updateCustomer(Customer customer);
}
