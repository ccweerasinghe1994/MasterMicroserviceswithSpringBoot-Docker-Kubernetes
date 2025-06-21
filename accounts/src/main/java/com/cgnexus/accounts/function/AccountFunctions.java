package com.cgnexus.accounts.function;

import com.cgnexus.accounts.service.IAccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Slf4j
@Configuration
public class AccountFunctions {


    @Bean
    public Consumer<Long> updateCommunication(IAccountService accountService) {
        return accountNumber -> {
            log.info("Updating communication for account number: {}", accountNumber);
            accountService.updateCommunicationStatus(accountNumber);
        };
    }

}
