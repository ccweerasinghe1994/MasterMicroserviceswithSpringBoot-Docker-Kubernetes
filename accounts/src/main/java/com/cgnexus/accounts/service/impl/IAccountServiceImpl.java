package com.cgnexus.accounts.service.impl;

import com.cgnexus.accounts.dto.AccountDTO;
import com.cgnexus.accounts.repository.AccountRepository;
import com.cgnexus.accounts.repository.CustomerRepository;
import com.cgnexus.accounts.service.IAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IAccountServiceImpl implements IAccountService {

    private final AccountRepository accountRepository;

    private final CustomerRepository customerRepository;

    /**
     * Creates a new account based on the provided account details
     *
     * @param accountDTO The account data transfer object containing account information
     */
    @Override
    public void createAccount(AccountDTO accountDTO) {

    }
}
