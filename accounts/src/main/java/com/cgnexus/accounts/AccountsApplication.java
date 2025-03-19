package com.cgnexus.accounts;

import com.cgnexus.accounts.dto.AccountContactInfoDTO;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@EnableConfigurationProperties(value = {AccountContactInfoDTO.class})
@EnableFeignClients
@OpenAPIDefinition(
        info = @Info(
                title = "Accounts Microservice REST API Documentation",
                version = "v1",
                contact = @Contact(
                        name = "CGNexus",
                        email = "ccweerasinghe1994@gmail.com",
                        url = "api.cgnexus.com"
                ),
                license = @License(
                        name = "Apache 2.0",
                        url = "http://www.apache.org/licenses/LICENSE-2.0"
                ),
                description = "This is a RESTful API documentation for the Accounts Microservice."
        ),
        externalDocs = @ExternalDocumentation(
                description = "External Documentation",
                url = "https://cgnexus.com"
        )
)
public class AccountsApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountsApplication.class, args);
    }

}
