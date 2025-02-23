package com.cgnexus.accounts.service.impl;

import com.cgnexus.accounts.constants.AccountConstants;
import com.cgnexus.accounts.dto.AccountDTO;
import com.cgnexus.accounts.dto.CustomerDTO;
import com.cgnexus.accounts.entity.Account;
import com.cgnexus.accounts.entity.Customer;
import com.cgnexus.accounts.exception.CustomerAlreadyExistsException;
import com.cgnexus.accounts.exception.ResourceNotFoundException;
import com.cgnexus.accounts.mapper.AccountMapper;
import com.cgnexus.accounts.mapper.CustomerMapper;
import com.cgnexus.accounts.repository.AccountRepository;
import com.cgnexus.accounts.service.IAccountService;
import com.cgnexus.accounts.service.ICustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class IAccountServiceImpl implements IAccountService {

    private final AccountRepository accountRepository;

    private final ICustomerService customerService;

    /**
     * Creates a new account based on the provided account details
     *
     * @param customerDTO The account data transfer object containing account information
     */
    @Override
    public void createAccount(CustomerDTO customerDTO) {

        if (customerService.isCustomerExists(customerDTO.getMobileNumber())) {
            throw new CustomerAlreadyExistsException("Customer with mobile number " + customerDTO.getMobileNumber() + " already exists");
        }

        Customer customer = customerService.createCustomer(customerDTO);

        Account account = createNewAccount(customer.getCustomerId());

        accountRepository.save(account);

    }

    @Override
    public CustomerDTO fetchAccountByMobileNumber(String mobileNumber) {
        Customer customer = customerService.fetchCustomerByMobileNumber(mobileNumber);
        Account account = findAccountByCustomerId(customer.getCustomerId());
        AccountDTO accountDTO = AccountMapper.toAccountDTO(account, new AccountDTO());
        CustomerDTO customerDTO = CustomerMapper.toCustomerDTO(customer, new CustomerDTO());
        customerDTO.setAccountDTO(accountDTO);
        return customerDTO;

    }

    private Account findAccountByCustomerId(Long customerId) {
        return accountRepository.findByCustomerId(customerId).orElseThrow(
                () -> new ResourceNotFoundException("Account", "customerId", customerId.toString())
        );
    }

    private Account createNewAccount(Long customerId) {

        long randomAccountNumber = 1000000000L + new Random().nextInt(900000000);

        Account account = new Account();
        account.setCustomerId(customerId);
        account.setAccountNumber(randomAccountNumber);
        account.setAccountType(AccountConstants.SAVINGS);
        account.setBranchAddress(AccountConstants.ADDRESS);
        account.setCreatedAt(LocalDateTime.now());
        account.setCreatedBy("Anonymous");
        return account;

    }
}
