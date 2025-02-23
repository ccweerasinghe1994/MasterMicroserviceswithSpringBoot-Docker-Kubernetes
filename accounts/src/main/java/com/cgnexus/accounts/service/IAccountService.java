package com.cgnexus.accounts.service;

import com.cgnexus.accounts.dto.CustomerDTO;

public interface IAccountService {

    /**
     * Creates a new account based on the provided account details
     *
     * @param customerDTO The customer data transfer object containing account information
     */
    void createAccount(CustomerDTO customerDTO);


    CustomerDTO fetchAccountByMobileNumber(String mobileNumber);
}
