package com.cgnexus.accounts.service.impl;

import com.cgnexus.accounts.constants.AccountConstants;
import com.cgnexus.accounts.dto.CustomerDTO;
import com.cgnexus.accounts.entity.Account;
import com.cgnexus.accounts.entity.Customer;
import com.cgnexus.accounts.exception.CustomerAlreadyExistsException;
import com.cgnexus.accounts.mapper.CustomerMapper;
import com.cgnexus.accounts.repository.AccountRepository;
import com.cgnexus.accounts.repository.CustomerRepository;
import com.cgnexus.accounts.service.IAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class IAccountServiceImpl implements IAccountService {

    private final AccountRepository accountRepository;

    private final CustomerRepository customerRepository;

    /**
     * Creates a new account based on the provided account details
     *
     * @param customerDTO The account data transfer object containing account information
     */
    @Override
    public void createAccount(CustomerDTO customerDTO) {

        Optional<Customer> byMobileNumber = customerRepository.findByMobileNumber(customerDTO.getMobileNumber());

        if (byMobileNumber.isPresent()) {
            throw new CustomerAlreadyExistsException("Customer with mobile number " + customerDTO.getMobileNumber() + " already exists");
        }


        Customer customer = CustomerMapper.toCustomer(customerDTO, new Customer());
        customer.setCreatedAt(LocalDateTime.now());
        customer.setCreatedBy("Anonymous");
        Customer savedCustomer = customerRepository.save(customer);

        Account account = createNewAccount(savedCustomer);
        account.setCreatedAt(LocalDateTime.now());
        account.setCreatedBy("Anonymous");
        accountRepository.save(account);

    }

    private Account createNewAccount(Customer customer) {

        long randomAccountNumber = 1000000000L + new Random().nextInt(900000000);

        Account account = new Account();

        account.setCustomerId(customer.getCustomerId());
        account.setAccountNumber(randomAccountNumber);
        account.setAccountType(AccountConstants.SAVINGS);
        account.setBranchAddress(AccountConstants.ADDRESS);

        return account;

    }
}
