package com.cgnexus.accounts.service;

import com.cgnexus.accounts.dto.AccountDTO;

public interface IAccountService {

    /**
     * Creates a new account based on the provided account details
     *
     * @param accountDTO The account data transfer object containing account information
     */
    void createAccount(AccountDTO accountDTO);
}
