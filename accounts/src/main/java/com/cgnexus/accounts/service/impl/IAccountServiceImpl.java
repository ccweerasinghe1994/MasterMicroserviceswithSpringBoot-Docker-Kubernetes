package com.cgnexus.accounts.service.impl;

import com.cgnexus.accounts.constants.AccountConstants;
import com.cgnexus.accounts.dto.AccountDTO;
import com.cgnexus.accounts.dto.AccountMsgDto;
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
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

import java.util.Random;

@Slf4j
@Service
@RequiredArgsConstructor
public class IAccountServiceImpl implements IAccountService {

    private final AccountRepository accountRepository;

    private final ICustomerService customerService;

    private final StreamBridge streamBridge;


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

        Account savedAccount = accountRepository.save(account);

        sendCommunication(savedAccount, customer);

    }

    private void sendCommunication(Account savedAccount, Customer customer) {
        AccountMsgDto accountMsgDto = new AccountMsgDto(
                savedAccount.getAccountNumber(),
                customer.getName(),
                customer.getEmail(),
                customer.getMobileNumber()
        );
        log.info("Sending Communication request for the details: {}", accountMsgDto);
        boolean send = streamBridge.send("sendCommunication-out-0", accountMsgDto);
        log.info("Is communication request successfully triggered: {}", send);
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

    /**
     * @param customerDTO
     * @return
     */
    @Override
    public boolean updateAccount(CustomerDTO customerDTO) {

        boolean isUpdated = false;

        AccountDTO accountDTO = customerDTO.getAccountDTO();

        if (accountDTO != null) {
            Account account = findAccountByAccountNumber(accountDTO.getAccountNumber());
            AccountMapper.toAccount(accountDTO, account);
            Account updatedAccount = this.updateAccount(account);

            Long customerId = updatedAccount.getCustomerId();
            Customer customer = customerService.findCustomerByCustomerId(customerId);
            CustomerMapper.toCustomer(customerDTO, customer);
            customerService.updateCustomer(customer);

            isUpdated = true;

        }

        return isUpdated;
    }

    /**
     * @param mobileNumber The mobile number of the customer
     */
    @Override
    public void deleteAccount(String mobileNumber) {
        Customer customer = customerService.fetchCustomerByMobileNumber(mobileNumber);
        Account account = findAccountByCustomerId(customer.getCustomerId());
        deleteAccount(account);
        customerService.deleteCustomer(customer);
    }


    public Account findAccountByCustomerId(Long customerId) {
        return accountRepository.findByCustomerId(customerId).orElseThrow(
                () -> new ResourceNotFoundException("Account", "customerId", customerId.toString())
        );
    }

    @Override
    public boolean updateCommunicationStatus(Long accountNumber) {
        boolean isUpdated = false;

        if (accountNumber != null) {
            Account account = accountRepository.findById(accountNumber).orElseThrow(
                    () -> new ResourceNotFoundException("Account", "accountNumber", accountNumber.toString())
            );
            account.setCommunicationSw(true);
            accountRepository.save(account);
            isUpdated = true;
        }
        return isUpdated;
    }

    private Account findAccountByAccountNumber(Long accountNumber) {
        return accountRepository.findById(accountNumber).orElseThrow(
                () -> new ResourceNotFoundException("Account", "accountNumber", accountNumber.toString())
        );
    }

    private Account createNewAccount(Long customerId) {

        long randomAccountNumber = 1000000000L + new Random().nextInt(900000000);

        Account account = new Account();
        account.setCustomerId(customerId);
        account.setAccountNumber(randomAccountNumber);
        account.setAccountType(AccountConstants.SAVINGS);
        account.setBranchAddress(AccountConstants.ADDRESS);
        return account;

    }

    private Account updateAccount(Account account) {
        try {
            Account updatedAccount = accountRepository.save(account);
            log.info("Account updated successfully, account number: {}", account.getAccountNumber());
            return updatedAccount;
        } catch (Exception e) {
            log.error("Failed to update account, account number: {}", account.getAccountNumber(), e);
            throw new RuntimeException("Error while updating account, account number: " + account.getAccountNumber(), e);
        }
    }

    private void deleteAccount(Account account) {
        try {
            log.info("Deleting account, account number: {}", account.getAccountNumber());
            accountRepository.delete(account);
        } catch (Exception e) {
            log.error("Error while deleting account, account number: {}", account.getAccountNumber(), e);
            throw new RuntimeException("Error while deleting account, account number: " + account.getAccountNumber(), e);
        }
    }
}
