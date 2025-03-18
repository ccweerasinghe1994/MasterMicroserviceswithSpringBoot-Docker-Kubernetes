package com.cgnexus.accounts.service.impl;

import com.cgnexus.accounts.dto.AccountDTO;
import com.cgnexus.accounts.dto.CardsDto;
import com.cgnexus.accounts.dto.CustomerDetailsDTO;
import com.cgnexus.accounts.dto.LoansDto;
import com.cgnexus.accounts.entity.Account;
import com.cgnexus.accounts.entity.Customer;
import com.cgnexus.accounts.mapper.AccountMapper;
import com.cgnexus.accounts.mapper.CustomerMapper;
import com.cgnexus.accounts.service.IAccountService;
import com.cgnexus.accounts.service.ICustomerDetailsService;
import com.cgnexus.accounts.service.ICustomerService;
import com.cgnexus.accounts.service.client.CardsFeignClient;
import com.cgnexus.accounts.service.client.LoansFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ICustomerDetailsServiceImpl implements ICustomerDetailsService {

    private final ICustomerService customerService;
    private final IAccountService accountService;
    private final CardsFeignClient cardsFeignClient;
    private final LoansFeignClient loansFeignClient;

    @Override
    public CustomerDetailsDTO fetchCustomerDetailsByMobileNumber(String mobileNumber) {

        ResponseEntity<CardsDto> cardsDtoResponseEntity = cardsFeignClient.fetchCardDetails(mobileNumber);
        ResponseEntity<LoansDto> loansDtoResponseEntity = loansFeignClient.fetchLoanDetails(mobileNumber);
        Customer customer = customerService.fetchCustomerByMobileNumber(mobileNumber);
        Account account = accountService.findAccountByCustomerId(customer.getCustomerId());
        AccountDTO accountDTO = AccountMapper.toAccountDTO(account, new AccountDTO());
        CustomerDetailsDTO customerDetailsDTO = CustomerMapper.toCustomerDetailsDTO(customer, new CustomerDetailsDTO());

        customerDetailsDTO.setAccountDTO(accountDTO);
        customerDetailsDTO.setCardsDto(cardsDtoResponseEntity.getBody());
        customerDetailsDTO.setLoansDto(loansDtoResponseEntity.getBody());

        return customerDetailsDTO;
    }
}
