package com.cgnexus.accounts.service;

import com.cgnexus.accounts.dto.CustomerDetailsDTO;


public interface ICustomerDetailsService {

    CustomerDetailsDTO fetchCustomerDetailsByMobileNumber(String mobileNumber);
}
