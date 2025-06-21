package com.cgnexus.message.functions;

import com.cgnexus.message.dto.AccountMsgDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

@Configuration
public class MessageFunctions {
    private static final Logger log = LoggerFactory.getLogger(MessageFunctions.class);

    @Bean
    public Function<AccountMsgDto, AccountMsgDto> email() {
        return accountMsgDto -> {
            log.info("sending email with details {}", accountMsgDto);
            return accountMsgDto;
        };
    }

    @Bean
    public Function<AccountMsgDto, Long> sms() {
        return accountMsgDto -> {
            log.info("sending sms with details {}", accountMsgDto);
            return accountMsgDto.accountNumber();
        };
    }
}
