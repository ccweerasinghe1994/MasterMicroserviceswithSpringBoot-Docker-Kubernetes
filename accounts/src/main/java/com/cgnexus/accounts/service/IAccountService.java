package com.cgnexus.accounts.service;

import com.cgnexus.accounts.dto.CustomerDTO;

public interface IAccountService {

    /**
     * Creates a new account based on the provided account details
     *
     * @param customerDTO The customer data transfer object containing account information
     */
    void createAccount(CustomerDTO customerDTO);

    /**
     * Fetches the account details based on the provided mobile number
     *
     * @param mobileNumber The mobile number of the customer
     * @return The customer data transfer object containing account information
     */
    CustomerDTO fetchAccountByMobileNumber(String mobileNumber);

    /**
     * Updates the account details based on the provided account details
     *
     * @param customerDTO The customer data transfer object containing account information
     * @return The boolean value indicating the status of the update operation
     */
    boolean updateAccount(CustomerDTO customerDTO);

    /**
     * Deletes the account based on the provided mobile number
     *
     * @param mobileNumber The mobile number of the customer
     */
    void deleteAccount(String mobileNumber);
}
