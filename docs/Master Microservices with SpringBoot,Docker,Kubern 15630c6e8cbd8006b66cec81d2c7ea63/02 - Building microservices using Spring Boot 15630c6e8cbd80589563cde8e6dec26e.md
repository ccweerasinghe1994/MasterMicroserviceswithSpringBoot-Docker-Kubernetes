# 02 - Building microservices using Spring Boot

## 001 How to build microservices

![image.png](02%20-%20Building%20microservices%20using%20Spring%20Boot%2015630c6e8cbd80589563cde8e6dec26e/image.png)

## 002 Introduction to SpringBoot framework

![image.png](02%20-%20Building%20microservices%20using%20Spring%20Boot%2015630c6e8cbd80589563cde8e6dec26e/image%201.png)

![image.png](02%20-%20Building%20microservices%20using%20Spring%20Boot%2015630c6e8cbd80589563cde8e6dec26e/image%202.png)

## 003 Funny memes of SpringBoot framework

![image.png](02%20-%20Building%20microservices%20using%20Spring%20Boot%2015630c6e8cbd80589563cde8e6dec26e/image%203.png)

![image.png](02%20-%20Building%20microservices%20using%20Spring%20Boot%2015630c6e8cbd80589563cde8e6dec26e/image%204.png)

![image.png](02%20-%20Building%20microservices%20using%20Spring%20Boot%2015630c6e8cbd80589563cde8e6dec26e/image%205.png)

![image.png](02%20-%20Building%20microservices%20using%20Spring%20Boot%2015630c6e8cbd80589563cde8e6dec26e/image%206.png)

![image.png](02%20-%20Building%20microservices%20using%20Spring%20Boot%2015630c6e8cbd80589563cde8e6dec26e/image%207.png)

![image.png](02%20-%20Building%20microservices%20using%20Spring%20Boot%2015630c6e8cbd80589563cde8e6dec26e/image%208.png)

## 004 Introduction to REST APIs & best practices

![image.png](02%20-%20Building%20microservices%20using%20Spring%20Boot%2015630c6e8cbd80589563cde8e6dec26e/image%209.png)

![image.png](02%20-%20Building%20microservices%20using%20Spring%20Boot%2015630c6e8cbd80589563cde8e6dec26e/image%2010.png)

## 006 Creating a Spring Boot project

[start.spring.io](https://start.spring.io/)

![image.png](02%20-%20Building%20microservices%20using%20Spring%20Boot%2015630c6e8cbd80589563cde8e6dec26e/image%2011.png)

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>
	<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>3.4.3</version>
		<relativePath/> <!-- lookup parent from repository -->
	</parent>
	<groupId>com.cgnexus</groupId>
	<artifactId>accounts</artifactId>
	<version>0.0.1-SNAPSHOT</version>
	<name>accounts</name>
	<description>microservice for accounts</description>
	<url/>
	<licenses>
		<license/>
	</licenses>
	<developers>
		<developer/>
	</developers>
	<scm>
		<connection/>
		<developerConnection/>
		<tag/>
		<url/>
	</scm>
	<properties>
		<java.version>21</java.version>
	</properties>
	<dependencies>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-actuator</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-jpa</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-validation</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-devtools</artifactId>
			<scope>runtime</scope>
			<optional>true</optional>
		</dependency>
		<dependency>
			<groupId>com.h2database</groupId>
			<artifactId>h2</artifactId>
			<scope>runtime</scope>
		</dependency>
		<dependency>
			<groupId>org.projectlombok</groupId>
			<artifactId>lombok</artifactId>
			<optional>true</optional>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>
	</dependencies>

	<build>
		<plugins>
			<plugin>
				<groupId>org.apache.maven.plugins</groupId>
				<artifactId>maven-compiler-plugin</artifactId>
				<configuration>
					<annotationProcessorPaths>
						<path>
							<groupId>org.projectlombok</groupId>
							<artifactId>lombok</artifactId>
						</path>
					</annotationProcessorPaths>
				</configuration>
			</plugin>
			<plugin>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-maven-plugin</artifactId>
				<configuration>
					<excludes>
						<exclude>
							<groupId>org.projectlombok</groupId>
							<artifactId>lombok</artifactId>
						</exclude>
					</excludes>
				</configuration>
			</plugin>
		</plugins>
	</build>

</project>
```

## 007 Say Hello to your new AI Coding Companion

## 008 Creating Hello World REST API using @RestController

```java
package com.cgnexus.accounts.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountsController {

    @GetMapping("syaHello")
    public ResponseEntity<String> sayHello(){
        return ResponseEntity.ok("Hello from Accounts Service");
    }
}

```

![image.png](02%20-%20Building%20microservices%20using%20Spring%20Boot%2015630c6e8cbd80589563cde8e6dec26e/image%2012.png)

```java
➜ ~ git:(main) ✗ curl -X GET --location "http://localhost:8080/syaHello"
Hello from Accounts Service
```

## 009 Configuring H2 DB & YAML properties

```java
spring:
  datasource:
    url: jdbc:h2:mem:testdb
    driver-class-name: org.h2.Driver
    username: sa
    password: ''
  h2:
    console:
      enabled: true
  jpa:
    database-platform: org.hibernate.dialect.H2Dialect
    hibernate:
      ddl-auto: update
    show-sql: true
```

```java
create table if not exists `customer`
(
    `customer_id`   int auto_increment primary key,
    `name`          varchar(100) not null,
    `email`         varchar(100) not null,
    `mobile_number` varchar(10)  not null,
    `created_at`    date         not null,
    `created_by`    varchar(20)  not null,
    `updated_at`    date        default null,
    `updated_by`    varchar(20) default null
);

create table if not exists `accounts`
(
    `customer_id`    int          not null,
    `account_number` int auto_increment primary key,
    `account_type`   varchar(100) not null,
    `branch_address` varchar(200) not null,
    `created_at`     date         not null,
    `created_by`     varchar(20)  not null,
    `updated_at`     date        default null,
    `updated_by`     varchar(20) default null
);
```

## 010 Writing Spring Data JPA entities & repositories to interact with DB tables

```java
package com.cgnexus.accounts.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@Setter
@ToString
public class BaseEntity {
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "created_by", updatable = false)
    private String createdBy;

    @Column(name = "updated_at", insertable = false)
    private LocalDateTime updatedAt;

    @Column(name = "updated_by", insertable = false)
    private String updatedBy;
}

```

```java
package com.cgnexus.accounts.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long customerId;

    private String name;

    private String email;

    @Column(name = "mobile_number")
    private String mobileNumber;
}

```

```java
package com.cgnexus.accounts.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account extends BaseEntity {

    @Id
    @Column(name = "account_number")
    private Long accountNumber;

    @Column(name = "account_type")
    private String accountType;

    @Column(name = "branch_address")
    private String branchAddress;

    @Column(name = "customer_id")
    private Long customerId;
}

```

```java
package com.cgnexus.accounts.repository;

import com.cgnexus.accounts.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}

```

```java
package com.cgnexus.accounts.repository;

import com.cgnexus.accounts.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}

```

## 011 Introduction to DTO (Data Transfer Object) pattern

![image.png](02%20-%20Building%20microservices%20using%20Spring%20Boot%2015630c6e8cbd80589563cde8e6dec26e/image%2013.png)

## 012 Creating DTOs inside accounts microservice

```java
package com.cgnexus.accounts.dto;

import lombok.Data;

@Data
public class AccountDTO {
    private Long accountNumber;
    private String accountType;
    private String branchAddress;
}

```

```java
package com.cgnexus.accounts.dto;

import lombok.Data;

@Data
public class CustomerDTO {
    private String name;
    private String email;
    private String mobileNumber;
}

```

```java
package com.cgnexus.accounts.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseDTO {

    private String statusCode;

    private String statusMessage;

    
}

```

```java
package com.cgnexus.accounts.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ErrorResponseDTO {
    private String apiPath;
    private HttpStatus errorCode;
    private String errorMessage;
    private LocalDateTime errorTime;

}

```

## 013 CREATE API inside accounts microservice - Part 1

```java
package com.cgnexus.accounts.mapper;

import com.cgnexus.accounts.dto.AccountDTO;
import com.cgnexus.accounts.entity.Account;

public class AccountMapper {

    public static AccountDTO toAccountDTO(Account account, AccountDTO accountDTO) {
        accountDTO.setAccountNumber(account.getAccountNumber());
        accountDTO.setAccountType(account.getAccountType());
        accountDTO.setBranchAddress(account.getBranchAddress());
        return accountDTO;
    }

    public static Account toAccount(AccountDTO accountDTO, Account account) {
        account.setAccountNumber(accountDTO.getAccountNumber());
        account.setAccountType(accountDTO.getAccountType());
        account.setBranchAddress(accountDTO.getBranchAddress());
        return account;
    }
}

```

```java
package com.cgnexus.accounts.constants;

public class AccountConstants {
    public static final String SAVINGS = "Savings";
    public static final String ADDRESS = "1234, 5th Cross, 6th Main, Bangalore";

    public static final String STATUS_201 = "201";
    public static final String MESSAGE_201 = "Account created successfully";

    public static final String STATUS_200 = "200";
    public static final String MESSAGE_200 = "Request processed successfully";

    public static final String STATUS_500 = "500";
    public static final String MESSAGE_500 = "An error occurred, please try again or contact support";

    private AccountConstants() {
    }

}

```

```java
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

```

```java
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

```

```java
package com.cgnexus.accounts.controller;

import com.cgnexus.accounts.dto.AccountDTO;
import com.cgnexus.accounts.dto.ResponseDTO;
import com.cgnexus.accounts.service.IAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.cgnexus.accounts.constants.AccountConstants.MESSAGE_201;
import static com.cgnexus.accounts.constants.AccountConstants.STATUS_201;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AccountsController {

    private final IAccountService accountService;

    @PostMapping("/account")
    public ResponseEntity<ResponseDTO> createAccount(@RequestBody AccountDTO accountDTO) {
        accountService.createAccount(accountDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDTO(STATUS_201, MESSAGE_201));
    }
}

```

![image.png](02%20-%20Building%20microservices%20using%20Spring%20Boot%2015630c6e8cbd80589563cde8e6dec26e/image%2014.png)

![image.png](02%20-%20Building%20microservices%20using%20Spring%20Boot%2015630c6e8cbd80589563cde8e6dec26e/image%2015.png)

![image.png](02%20-%20Building%20microservices%20using%20Spring%20Boot%2015630c6e8cbd80589563cde8e6dec26e/image%2016.png)

![image.png](02%20-%20Building%20microservices%20using%20Spring%20Boot%2015630c6e8cbd80589563cde8e6dec26e/image%2017.png)

## 014 CREATE API inside accounts microservice - Part 2

```java
package com.cgnexus.accounts.controller;

import com.cgnexus.accounts.dto.CustomerDTO;
import com.cgnexus.accounts.dto.ResponseDTO;
import com.cgnexus.accounts.service.IAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.cgnexus.accounts.constants.AccountConstants.MESSAGE_201;
import static com.cgnexus.accounts.constants.AccountConstants.STATUS_201;

@RestController
@RequestMapping(value = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@RequiredArgsConstructor
public class AccountsController {
    private final IAccountService accountService;

    @PostMapping("/account")
    public ResponseEntity<ResponseDTO> createAccount(@RequestBody CustomerDTO customerDTO) {
        accountService.createAccount(customerDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDTO(STATUS_201, MESSAGE_201));
    }
}

```

```java
package com.cgnexus.accounts.mapper;

import com.cgnexus.accounts.dto.CustomerDTO;
import com.cgnexus.accounts.entity.Customer;

public class CustomerMapper {

    public static CustomerDTO toCustomer(Customer customer,CustomerDTO customerDTO){
        customerDTO.setEmail(customer.getEmail());
        customerDTO.setName(customer.getName());
        customerDTO.setMobileNumber(customer.getMobileNumber());
        return customerDTO;
    }

    public static Customer toCustomer(CustomerDTO customerDTO,Customer customer){
        customer.setEmail(customerDTO.getEmail());
        customer.setName(customerDTO.getName());
        customer.setMobileNumber(customerDTO.getMobileNumber());
        return customer;
    }
}

```

```java
package com.cgnexus.accounts.repository;

import com.cgnexus.accounts.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findByMobileNumber(String mobileNumber);
}

```

```java
package com.cgnexus.accounts.service;

import com.cgnexus.accounts.dto.AccountDTO;
import com.cgnexus.accounts.dto.CustomerDTO;

public interface IAccountService {

    /**
     * Creates a new account based on the provided account details
     *
     * @param customerDTO The customer data transfer object containing account information
     */
    void createAccount(CustomerDTO customerDTO);
}

```

```java
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

```

```java
package com.cgnexus.accounts.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class CustomerAlreadyExistsException extends RuntimeException {
    public CustomerAlreadyExistsException(String message) {
        super(message);
    }
}

```

```java
package com.cgnexus.accounts.exception;

import com.cgnexus.accounts.dto.ErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomerAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDTO> handleCustomerAlreadyExistsException(CustomerAlreadyExistsException ex, WebRequest request) {
        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(request.getDescription(false), HttpStatus.BAD_REQUEST, ex.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(errorResponseDTO, HttpStatus.BAD_REQUEST);
    }
}
```

![image.png](02%20-%20Building%20microservices%20using%20Spring%20Boot%2015630c6e8cbd80589563cde8e6dec26e/image%2018.png)

## 015 READ API inside accounts microservice

```java
package com.cgnexus.accounts.repository;

import com.cgnexus.accounts.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByCustomerId(Long customerId);
}

```

```java
package com.cgnexus.accounts.controller;

import com.cgnexus.accounts.dto.CustomerDTO;
import com.cgnexus.accounts.dto.ResponseDTO;
import com.cgnexus.accounts.service.IAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.cgnexus.accounts.constants.AccountConstants.MESSAGE_201;
import static com.cgnexus.accounts.constants.AccountConstants.STATUS_201;

@RestController
@RequestMapping(value = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@RequiredArgsConstructor
public class AccountsController {
    private final IAccountService accountService;

    @PostMapping("/account")
    public ResponseEntity<ResponseDTO> createAccount(@RequestBody CustomerDTO customerDTO) {
        accountService.createAccount(customerDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDTO(STATUS_201, MESSAGE_201));
    }

    @GetMapping("/account")
    public ResponseEntity<CustomerDTO> fetchAccountDetails(@RequestParam("mobile-number") String mobileNumber) {
        CustomerDTO customerDTO = accountService.fetchAccountByMobileNumber(mobileNumber);
        return ResponseEntity.ok(customerDTO);
    }
}

```

```java
package com.cgnexus.accounts.dto;

import lombok.Data;

@Data
public class CustomerDTO {
    private String name;
    private String email;
    private String mobileNumber;
    private AccountDTO accountDTO;
}

```

```java
package com.cgnexus.accounts.mapper;

import com.cgnexus.accounts.dto.CustomerDTO;
import com.cgnexus.accounts.entity.Customer;

public class CustomerMapper {

    public static CustomerDTO toCustomerDTO(Customer customer, CustomerDTO customerDTO) {
        customerDTO.setEmail(customer.getEmail());
        customerDTO.setName(customer.getName());
        customerDTO.setMobileNumber(customer.getMobileNumber());
        return customerDTO;
    }

    public static Customer toCustomer(CustomerDTO customerDTO, Customer customer) {
        customer.setEmail(customerDTO.getEmail());
        customer.setName(customerDTO.getName());
        customer.setMobileNumber(customerDTO.getMobileNumber());
        return customer;
    }
}

```

```java
package com.cgnexus.accounts.repository;

import com.cgnexus.accounts.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findByMobileNumber(String mobileNumber);

    boolean existsByMobileNumber(String mobileNumber);
}

```

```java
package com.cgnexus.accounts.exception;

import com.cgnexus.accounts.dto.ErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomerAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDTO> handleCustomerAlreadyExistsException(CustomerAlreadyExistsException ex, WebRequest request) {
        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(request.getDescription(false), HttpStatus.BAD_REQUEST, ex.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(errorResponseDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleCustomerNotFoundException(ResourceNotFoundException exception, WebRequest request) {
        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(request.getDescription(false), HttpStatus.BAD_REQUEST, exception.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(errorResponseDTO, HttpStatus.NOT_FOUND);
    }
}
```

```java
package com.cgnexus.accounts.service;

import com.cgnexus.accounts.dto.CustomerDTO;

public interface IAccountService {

    /**
     * Creates a new account based on the provided account details
     *
     * @param customerDTO The customer data transfer object containing account information
     */
    void createAccount(CustomerDTO customerDTO);

    CustomerDTO fetchAccountByMobileNumber(String mobileNumber);
}

```

```java
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

```

```java
package com.cgnexus.accounts.service;

import com.cgnexus.accounts.dto.CustomerDTO;
import com.cgnexus.accounts.entity.Customer;

public interface ICustomerService {

    Customer fetchCustomerByMobileNumber(String mobileNumber);

    boolean isCustomerExists(String mobileNumber);

    Customer createCustomer(CustomerDTO customerDTO);
}

```

```java
package com.cgnexus.accounts.service.impl;

import com.cgnexus.accounts.dto.CustomerDTO;
import com.cgnexus.accounts.entity.Customer;
import com.cgnexus.accounts.exception.ResourceNotFoundException;
import com.cgnexus.accounts.mapper.CustomerMapper;
import com.cgnexus.accounts.repository.CustomerRepository;
import com.cgnexus.accounts.service.ICustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ICustomerServiceImpl implements ICustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public Customer fetchCustomerByMobileNumber(String mobileNumber) {
        return customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("customer", "mobileNumber", mobileNumber)
        );
    }

    @Override
    public boolean isCustomerExists(String mobileNumber) {
        return customerRepository.existsByMobileNumber(mobileNumber);
    }

    @Override
    public Customer createCustomer(CustomerDTO customerDTO) {
        Customer customer = CustomerMapper.toCustomer(customerDTO, new Customer());
        customer.setCreatedAt(LocalDateTime.now());
        customer.setCreatedBy("Anonymous");
        return customerRepository.save(customer);

    }
}

```

```java
package com.cgnexus.accounts.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String resourceName, String fieldName, String fieldValue) {
        super(String.format("%s not found with %s : '%s'", resourceName, fieldName, fieldValue));
    }
}

```

## 016 UPDATE API inside accounts microservice

```java
package com.cgnexus.accounts.controller;

import com.cgnexus.accounts.dto.CustomerDTO;
import com.cgnexus.accounts.dto.ResponseDTO;
import com.cgnexus.accounts.service.IAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.cgnexus.accounts.constants.AccountConstants.*;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RestController
@RequestMapping(value = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@RequiredArgsConstructor
public class AccountsController {
    private final IAccountService accountService;

    @PostMapping("/account")
    public ResponseEntity<ResponseDTO> createAccount(@RequestBody CustomerDTO customerDTO) {
        accountService.createAccount(customerDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDTO(STATUS_201, MESSAGE_201));
    }

    @GetMapping("/account")
    public ResponseEntity<CustomerDTO> fetchAccountDetails(@RequestParam("mobile-number") String mobileNumber) {
        CustomerDTO customerDTO = accountService.fetchAccountByMobileNumber(mobileNumber);
        return ResponseEntity.ok(customerDTO);
    }

    @PutMapping("/account")
    public ResponseEntity<ResponseDTO> updateAccount(@RequestBody CustomerDTO customerDTO) {
        boolean isUpdated = accountService.updateAccount(customerDTO);

        if (isUpdated) {
            return ResponseEntity.ok(new ResponseDTO(STATUS_200, MESSAGE_200));
        } else {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ResponseDTO(STATUS_500, MESSAGE_500));
        }
    }
}

```

```java
package com.cgnexus.accounts.service;

import com.cgnexus.accounts.dto.CustomerDTO;

public interface IAccountService {

    /**
     * Creates a new account based on the provided account details
     *
     * @param customerDTO The customer data transfer object containing account information
     */
    void createAccount(CustomerDTO customerDTO);

    CustomerDTO fetchAccountByMobileNumber(String mobileNumber);

    boolean updateAccount(CustomerDTO customerDTO);
}

```

```java
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

    private Account findAccountByCustomerId(Long customerId) {
        return accountRepository.findByCustomerId(customerId).orElseThrow(
                () -> new ResourceNotFoundException("Account", "customerId", customerId.toString())
        );
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
        account.setCreatedAt(LocalDateTime.now());
        account.setCreatedBy("Anonymous");
        return account;

    }

    private Account updateAccount(Account account) {
        return accountRepository.save(account);
    }
}

```

```java
package com.cgnexus.accounts.service;

import com.cgnexus.accounts.dto.CustomerDTO;
import com.cgnexus.accounts.entity.Customer;

public interface ICustomerService {

    Customer fetchCustomerByMobileNumber(String mobileNumber);

    boolean isCustomerExists(String mobileNumber);

    Customer createCustomer(CustomerDTO customerDTO);

    Customer findCustomerByCustomerId(Long customerId);

    Customer updateCustomer(Customer customer);
}

```

```java
package com.cgnexus.accounts.service.impl;

import com.cgnexus.accounts.dto.CustomerDTO;
import com.cgnexus.accounts.entity.Customer;
import com.cgnexus.accounts.exception.ResourceNotFoundException;
import com.cgnexus.accounts.mapper.CustomerMapper;
import com.cgnexus.accounts.repository.CustomerRepository;
import com.cgnexus.accounts.service.ICustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ICustomerServiceImpl implements ICustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public Customer fetchCustomerByMobileNumber(String mobileNumber) {
        return customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("customer", "mobileNumber", mobileNumber)
        );
    }

    @Override
    public boolean isCustomerExists(String mobileNumber) {
        return customerRepository.existsByMobileNumber(mobileNumber);
    }

    @Override
    public Customer createCustomer(CustomerDTO customerDTO) {
        Customer customer = CustomerMapper.toCustomer(customerDTO, new Customer());
        customer.setCreatedAt(LocalDateTime.now());
        customer.setCreatedBy("Anonymous");
        return customerRepository.save(customer);

    }

    /**
     * @param customerId
     * @return
     */
    @Override
    public Customer findCustomerByCustomerId(Long customerId) {
        return customerRepository.findById(customerId).orElseThrow(
                () -> new ResourceNotFoundException("customer", "customerId", customerId.toString())
        );
    }

    /**
     * @param customer
     * @return customer
     */
    @Override
    public Customer updateCustomer(Customer customer) {
        return customerRepository.save(customer);
    }
}

```

## 017 DELETE API inside accounts microservice

```java
package com.cgnexus.accounts.controller;

import com.cgnexus.accounts.dto.CustomerDTO;
import com.cgnexus.accounts.dto.ResponseDTO;
import com.cgnexus.accounts.service.IAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.cgnexus.accounts.constants.AccountConstants.*;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RestController
@RequestMapping(value = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@RequiredArgsConstructor
public class AccountsController {
    
    private final IAccountService accountService;
    
    @DeleteMapping("/account")
    public ResponseEntity<ResponseDTO> deleteAccount(@RequestParam("mobile-number") String mobileNumber) {
        accountService.deleteAccount(mobileNumber);
        return ResponseEntity.ok(new ResponseDTO(STATUS_200, MESSAGE_200));

    }
}

```

```java
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

```

```java
package com.cgnexus.accounts.service;

import com.cgnexus.accounts.dto.CustomerDTO;
import com.cgnexus.accounts.entity.Customer;

public interface ICustomerService {

    Customer fetchCustomerByMobileNumber(String mobileNumber);

    boolean isCustomerExists(String mobileNumber);

    Customer createCustomer(CustomerDTO customerDTO);

    Customer findCustomerByCustomerId(Long customerId);

    Customer updateCustomer(Customer customer);

    void deleteCustomer(Customer customer);
}

```

```java
package com.cgnexus.accounts.service.impl;

import com.cgnexus.accounts.dto.CustomerDTO;
import com.cgnexus.accounts.entity.Customer;
import com.cgnexus.accounts.exception.ResourceNotFoundException;
import com.cgnexus.accounts.mapper.CustomerMapper;
import com.cgnexus.accounts.repository.CustomerRepository;
import com.cgnexus.accounts.service.ICustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class ICustomerServiceImpl implements ICustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public Customer fetchCustomerByMobileNumber(String mobileNumber) {
        return customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("customer", "mobileNumber", mobileNumber)
        );
    }

    @Override
    public boolean isCustomerExists(String mobileNumber) {
        return customerRepository.existsByMobileNumber(mobileNumber);
    }

    @Override
    public Customer createCustomer(CustomerDTO customerDTO) {
        Customer customer = CustomerMapper.toCustomer(customerDTO, new Customer());
        customer.setCreatedAt(LocalDateTime.now());
        customer.setCreatedBy("Anonymous");
        return saveCustomer(customer);

    }

    /**
     * @param customerId
     * @return
     */
    @Override
    public Customer findCustomerByCustomerId(Long customerId) {
        return customerRepository.findById(customerId).orElseThrow(
                () -> new ResourceNotFoundException("customer", "customerId", customerId.toString())
        );
    }

    private Customer saveCustomer(Customer customer) {
        try {
            Customer saved = customerRepository.save(customer);
            log.info("Customer with id {} saved successfully", saved.getCustomerId());
            return saved;
        } catch (Exception e) {
            log.error("Error saving customer with id {}: {}", customer.getCustomerId(), e.getMessage());
            throw new RuntimeException("Error saving customer with id " + customer.getCustomerId(), e);
        }
    }

    /**
     * @param customer
     * @return customer
     */
    @Override
    public Customer updateCustomer(Customer customer) {
        try {
            Customer updatedCustomer = customerRepository.save(customer);
            log.info("Customer with id {} updated successfully", updatedCustomer.getCustomerId());
            return updatedCustomer;
        } catch (Exception e) {
            log.error("Error updating customer with id {}: {}", customer.getCustomerId(), e.getMessage());
            throw new RuntimeException("Error updating customer with id " + customer.getCustomerId(), e);
        }
    }

    /**
     * @param customer
     */
    @Override
    public void deleteCustomer(Customer customer) {
        try {
            customerRepository.delete(customer);
            log.info("Customer with id {} deleted successfully", customer.getCustomerId());
        } catch (Exception e) {
            log.error("Error deleting customer with id {}: {}", customer.getCustomerId(), e.getMessage());
            throw new RuntimeException("Error deleting customer with id " + customer.getCustomerId(), e);
        }
    }
}

```

```java
package com.cgnexus.accounts.service;

import com.cgnexus.accounts.dto.CustomerDTO;
import com.cgnexus.accounts.entity.Customer;

public interface ICustomerService {

    Customer fetchCustomerByMobileNumber(String mobileNumber);

    boolean isCustomerExists(String mobileNumber);

    Customer createCustomer(CustomerDTO customerDTO);

    Customer findCustomerByCustomerId(Long customerId);

    Customer updateCustomer(Customer customer);

    void deleteCustomer(Customer customer);
}

```

```java
package com.cgnexus.accounts.service.impl;

import com.cgnexus.accounts.dto.CustomerDTO;
import com.cgnexus.accounts.entity.Customer;
import com.cgnexus.accounts.exception.ResourceNotFoundException;
import com.cgnexus.accounts.mapper.CustomerMapper;
import com.cgnexus.accounts.repository.CustomerRepository;
import com.cgnexus.accounts.service.ICustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class ICustomerServiceImpl implements ICustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public Customer fetchCustomerByMobileNumber(String mobileNumber) {
        return customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("customer", "mobileNumber", mobileNumber)
        );
    }

    @Override
    public boolean isCustomerExists(String mobileNumber) {
        return customerRepository.existsByMobileNumber(mobileNumber);
    }

    @Override
    public Customer createCustomer(CustomerDTO customerDTO) {
        Customer customer = CustomerMapper.toCustomer(customerDTO, new Customer());
        customer.setCreatedAt(LocalDateTime.now());
        customer.setCreatedBy("Anonymous");
        return saveCustomer(customer);

    }

    /**
     * @param customerId
     * @return
     */
    @Override
    public Customer findCustomerByCustomerId(Long customerId) {
        return customerRepository.findById(customerId).orElseThrow(
                () -> new ResourceNotFoundException("customer", "customerId", customerId.toString())
        );
    }

    private Customer saveCustomer(Customer customer) {
        try {
            Customer saved = customerRepository.save(customer);
            log.info("Customer with id {} saved successfully", saved.getCustomerId());
            return saved;
        } catch (Exception e) {
            log.error("Error saving customer with id {}: {}", customer.getCustomerId(), e.getMessage());
            throw new RuntimeException("Error saving customer with id " + customer.getCustomerId(), e);
        }
    }

    /**
     * @param customer
     * @return customer
     */
    @Override
    public Customer updateCustomer(Customer customer) {
        try {
            Customer updatedCustomer = customerRepository.save(customer);
            log.info("Customer with id {} updated successfully", updatedCustomer.getCustomerId());
            return updatedCustomer;
        } catch (Exception e) {
            log.error("Error updating customer with id {}: {}", customer.getCustomerId(), e.getMessage());
            throw new RuntimeException("Error updating customer with id " + customer.getCustomerId(), e);
        }
    }

    /**
     * @param customer
     */
    @Override
    public void deleteCustomer(Customer customer) {
        try {
            customerRepository.delete(customer);
            log.info("Customer with id {} deleted successfully", customer.getCustomerId());
        } catch (Exception e) {
            log.error("Error deleting customer with id {}: {}", customer.getCustomerId(), e.getMessage());
            throw new RuntimeException("Error deleting customer with id " + customer.getCustomerId(), e);
        }
    }
}

```

## 018 Handle all types of runtime exceptions using global logic inside accounts

```java
package com.cgnexus.accounts.exception;

import com.cgnexus.accounts.dto.ErrorResponseDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Handle validation errors when method arguments fail validation.
     *
     * @param ex      the exception containing validation errors.
     * @param headers the headers to be written to the response.
     * @param status  the status to be written to the response.
     * @param request the current web request.
     * @return a response entity with error details.
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Map<String, String> validationErrors = new HashMap<>();
        List<ObjectError> validationErrorList = ex.getBindingResult().getAllErrors();

        validationErrorList.forEach(objectError -> {
            String fieldName = ((FieldError) objectError).getField();
            String validationMessage = objectError.getDefaultMessage();
            validationErrors.put(fieldName, validationMessage);
        });

        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(request.getDescription(false), HttpStatus.BAD_REQUEST, validationErrors.toString(), LocalDateTime.now());

        return new ResponseEntity<>(errorResponseDTO, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handle validation errors when method arguments fail validation.
     *
     * @param ex      the exception containing validation errors.
     * @param request the current web request.
     * @return a response entity with error details.
     */
    @ExceptionHandler(CustomerAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDTO> handleCustomerAlreadyExistsException(CustomerAlreadyExistsException ex, WebRequest request) {
        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(request.getDescription(false), HttpStatus.BAD_REQUEST, ex.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(errorResponseDTO, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handle validation errors when method arguments fail validation.
     *
     * @param exception the exception containing validation errors.
     * @param request   the current web request.
     * @return a response entity with error details.
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleCustomerNotFoundException(ResourceNotFoundException exception, WebRequest request) {
        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(request.getDescription(false), HttpStatus.BAD_REQUEST, exception.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(errorResponseDTO, HttpStatus.NOT_FOUND);
    }

    /**
     * Handle all other exceptions.
     *
     * @param ex      the exception to handle.
     * @param request the current web request.
     * @return a response entity with error details.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> handleAllExceptions(Exception ex, WebRequest request) {
        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(request.getDescription(false), HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(errorResponseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
```

## 019 Perform input data validations inside accounts microservice

## 020 Update audit columns using Spring Data

```java
package com.cgnexus.accounts.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@Setter
@ToString
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {
    @CreatedDate
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @CreatedBy
    @Column(name = "created_by", updatable = false)
    private String createdBy;

    @LastModifiedDate
    @Column(name = "updated_at", insertable = false)
    private LocalDateTime updatedAt;

    @LastModifiedBy
    @Column(name = "updated_by", insertable = false)
    private String updatedBy;
}

```

```java
package com.cgnexus.accounts.audit;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("auditAwareImpl")
public class AuditAwareImpl implements AuditorAware<String> {
    /**
     * Returns the current auditor name for entities being tracked by JPA auditing.
     * This implementation returns a constant value 'ACCOUNT_MS' as the auditor.
     *
     * @return an Optional containing the auditor name "ACCOUNT_MS"
     */
    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of("ACCOUNT_MS");
    }
}

```

```java
package com.cgnexus.accounts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
public class AccountsApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountsApplication.class, args);
    }

}

```

## 021 Introduction to documentation of REST APIs using springdoc openapi

```xml
        <dependency>
            <groupId>org.springdoc</groupId>
            <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
            <version>2.8.5</version>
        </dependency>
```

[](http://localhost:8080/swagger-ui/index.html#/)

## 022 Enhancing documentation of REST APIs using @OpenAPIDefinition

```java
package com.cgnexus.accounts.controller;

import com.cgnexus.accounts.dto.CustomerDTO;
import com.cgnexus.accounts.dto.ResponseDTO;
import com.cgnexus.accounts.service.IAccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.cgnexus.accounts.constants.AccountConstants.*;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@Tag(
    name = "CRUD REST APIs for Accounts",
    description = "Create, Read, Update and Delete account details"
)
@RestController
@RequestMapping(value = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@RequiredArgsConstructor
@Validated
public class AccountsController {
    private final IAccountService accountService;

    @Operation(
        summary = "Create Account REST API",
        description = "REST API to create new customer account"
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "201",
            description = "HTTP Status CREATED"
        ),
        @ApiResponse(
            responseCode = "400",
            description = "HTTP Status BAD REQUEST",
            content = @Content(
                schema = @Schema(implementation = ResponseDTO.class)
            )
        )
    })
    @PostMapping("/account")
    public ResponseEntity<ResponseDTO> createAccount(@Valid @RequestBody CustomerDTO customerDTO) {
        accountService.createAccount(customerDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDTO(STATUS_201, MESSAGE_201));
    }

    @Operation(
        summary = "Fetch Account Details REST API",
        description = "REST API to fetch customer account details based on mobile number"
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "HTTP Status OK"
        ),
        @ApiResponse(
            responseCode = "404",
            description = "HTTP Status NOT FOUND",
            content = @Content(
                schema = @Schema(implementation = ResponseDTO.class)
            )
        ),
        @ApiResponse(
            responseCode = "400",
            description = "HTTP Status BAD REQUEST",
            content = @Content(
                schema = @Schema(implementation = ResponseDTO.class)
            )
        )
    })
    @GetMapping("/account")
    public ResponseEntity<CustomerDTO> fetchAccountDetails(
            @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number should be valid")
            @RequestParam("mobile-number") String mobileNumber
    ) {
        CustomerDTO customerDTO = accountService.fetchAccountByMobileNumber(mobileNumber);
        return ResponseEntity.ok(customerDTO);
    }

    @Operation(
        summary = "Update Account Details REST API",
        description = "REST API to update customer account details"
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "HTTP Status OK"
        ),
        @ApiResponse(
            responseCode = "500",
            description = "HTTP Status INTERNAL SERVER ERROR",
            content = @Content(
                schema = @Schema(implementation = ResponseDTO.class)
            )
        ),
        @ApiResponse(
            responseCode = "400",
            description = "HTTP Status BAD REQUEST",
            content = @Content(
                schema = @Schema(implementation = ResponseDTO.class)
            )
        )
    })
    @PutMapping("/account")
    public ResponseEntity<ResponseDTO> updateAccount(@Valid @RequestBody CustomerDTO customerDTO) {
        boolean isUpdated = accountService.updateAccount(customerDTO);

        if (isUpdated) {
            return ResponseEntity.ok(new ResponseDTO(STATUS_200, MESSAGE_200));
        } else {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ResponseDTO(STATUS_500, MESSAGE_500));
        }
    }

    @Operation(
        summary = "Delete Account Details REST API",
        description = "REST API to delete customer account based on mobile number"
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "HTTP Status OK"
        ),
        @ApiResponse(
            responseCode = "404",
            description = "HTTP Status NOT FOUND",
            content = @Content(
                schema = @Schema(implementation = ResponseDTO.class)
            )
        ),
        @ApiResponse(
            responseCode = "400",
            description = "HTTP Status BAD REQUEST",
            content = @Content(
                schema = @Schema(implementation = ResponseDTO.class)
            )
        )
    })
    @DeleteMapping("/account")
    public ResponseEntity<ResponseDTO> deleteAccount(
            @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number should be valid")
            @RequestParam("mobile-number") String mobileNumber
    ) {
        accountService.deleteAccount(mobileNumber);
        return ResponseEntity.ok(new ResponseDTO(STATUS_200, MESSAGE_200));
    }
}

```

## 023 Enhancing documentation of REST APIs using @Tag, @Operation, @ApiResponse

```java
package com.cgnexus.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Schema(
    name = "Account",
    description = "Schema to hold Account information"
)
public class AccountDTO {
    @Schema(
        description = "Account number of the customer",
        example = "3456789012"
    )
    @NotEmpty(message = "Account number is required")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Account number should be valid")
    private Long accountNumber;

    @Schema(
        description = "Type of the account",
        example = "Savings"
    )
    @NotEmpty(message = "Account type is required")
    private String accountType;

    @Schema(
        description = "Address of the branch where account is created",
        example = "123 Main Street, New York, NY 10001"
    )
    @NotEmpty(message = "Branch address is required")
    private String branchAddress;
}

```

```java
package com.cgnexus.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(
    name = "Customer",
    description = "Schema to hold Customer and Account information"
)
public class CustomerDTO {

    @Schema(
        description = "Name of the customer",
        example = "John Doe"
    )
    @NotEmpty(message = "Name is required")
    @Size(min = 3, max = 50, message = "Name should be between 3 to 50 characters")
    private String name;

    @Schema(
        description = "Email address of the customer",
        example = "john.doe@example.com"
    )
    @NotEmpty(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;

    @Schema(
        description = "Mobile number of the customer",
        example = "9876543210"
    )
    @NotEmpty(message = "Mobile number is required")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number should be valid")
    private String mobileNumber;

    @Schema(
        description = "Account details of the customer"
    )
    private AccountDTO accountDTO;
}

```

```java
package com.cgnexus.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Schema(
    name = "ErrorResponse",
    description = "Schema to hold error response information"
)
public class ErrorResponseDTO {
    @Schema(
        description = "Path of the API that returned the error",
        example = "/api/accounts"
    )
    private String apiPath;

    @Schema(
        description = "HTTP status code of the error",
        example = "404"
    )
    private HttpStatus errorCode;

    @Schema(
        description = "Message of the error",
        example = "Resource not found"
    )
    private String errorMessage;

    @Schema(
        description = "Timestamp of the error",
        example = "2024-01-01 10:00:00"
    )
    private LocalDateTime errorTime;

}

```

```java
package com.cgnexus.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(
    name = "Response",
    description = "Schema to hold response information"
)                                                                                                                           
public class ResponseDTO {
    @Schema(
        description = "HTTP status code of the response",
        example = "200"
    )
    private String statusCode;

    @Schema(
        description = "Message of the response",
        example = "Operation successful"
    )
    private String statusMessage;

    
}
                                                                                                
```

## 024 Enhancing documentation of REST APIs using @Schema & example data - Part 1

```java
package com.cgnexus.accounts.controller;

import com.cgnexus.accounts.dto.CustomerDTO;
import com.cgnexus.accounts.dto.ErrorResponseDTO;
import com.cgnexus.accounts.dto.ResponseDTO;
import com.cgnexus.accounts.service.IAccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.cgnexus.accounts.constants.AccountConstants.*;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@Tag(
    name = "CRUD REST APIs for Accounts",
    description = "Create, Read, Update and Delete account details"
)
@RestController
@RequestMapping(value = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@RequiredArgsConstructor
@Validated
public class AccountsController {
    private final IAccountService accountService;

    @Operation(
        summary = "Create Account REST API",
        description = "REST API to create new customer account"
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "201",
            description = "HTTP Status CREATED"
        ),
        @ApiResponse(
            responseCode = "400",
            description = "HTTP Status BAD REQUEST",
            content = @Content(
                schema = @Schema(implementation = ResponseDTO.class)
            )
        )
    })
    @PostMapping("/account")
    public ResponseEntity<ResponseDTO> createAccount(@Valid @RequestBody CustomerDTO customerDTO) {
        accountService.createAccount(customerDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDTO(STATUS_201, MESSAGE_201));
    }

    @Operation(
        summary = "Fetch Account Details REST API",
        description = "REST API to fetch customer account details based on mobile number"
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "HTTP Status OK"
        ),
        @ApiResponse(
            responseCode = "404",
            description = "HTTP Status NOT FOUND",
            content = @Content(
                schema = @Schema(implementation = ErrorResponseDTO.class)
            )
        ),
        @ApiResponse(
            responseCode = "400",
            description = "HTTP Status BAD REQUEST",
            content = @Content(
                schema = @Schema(implementation = ErrorResponseDTO.class)
            )
        )
    })
    @GetMapping("/account")
    public ResponseEntity<CustomerDTO> fetchAccountDetails(
            @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number should be valid")
            @RequestParam("mobile-number") String mobileNumber
    ) {
        CustomerDTO customerDTO = accountService.fetchAccountByMobileNumber(mobileNumber);
        return ResponseEntity.ok(customerDTO);
    }

    @Operation(
        summary = "Update Account Details REST API",
        description = "REST API to update customer account details"
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "HTTP Status OK"
        ),
        @ApiResponse(
            responseCode = "500",
            description = "HTTP Status INTERNAL SERVER ERROR",
            content = @Content(
                schema = @Schema(implementation = ErrorResponseDTO.class)
            )
        ),
        @ApiResponse(
            responseCode = "400",
            description = "HTTP Status BAD REQUEST",
            content = @Content(
                schema = @Schema(implementation = ErrorResponseDTO.class)
            )
        )
    })
    @PutMapping("/account")
    public ResponseEntity<ResponseDTO> updateAccount(@Valid @RequestBody CustomerDTO customerDTO) {
        boolean isUpdated = accountService.updateAccount(customerDTO);

        if (isUpdated) {
            return ResponseEntity.ok(new ResponseDTO(STATUS_200, MESSAGE_200));
        } else {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ResponseDTO(STATUS_500, MESSAGE_500));
        }
    }

    @Operation(
        summary = "Delete Account Details REST API",
        description = "REST API to delete customer account based on mobile number"
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "HTTP Status OK"
        ),
        @ApiResponse(
            responseCode = "404",
            description = "HTTP Status NOT FOUND",
            content = @Content(
                schema = @Schema(implementation = ErrorResponseDTO.class)
            )
        ),
        @ApiResponse(
            responseCode = "400",
            description = "HTTP Status BAD REQUEST",
            content = @Content(
                schema = @Schema(implementation = ErrorResponseDTO.class)
            )
        )
    })
    @DeleteMapping("/account")
    public ResponseEntity<ResponseDTO> deleteAccount(
            @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number should be valid")
            @RequestParam("mobile-number") String mobileNumber
    ) {
        accountService.deleteAccount(mobileNumber);
        return ResponseEntity.ok(new ResponseDTO(STATUS_200, MESSAGE_200));
    }
}

```

## 025 Enhancing documentation of REST APIs using @Schema & example data - Part 2

## 026 Important Annotations & Classes that supports building REST services

![image.png](02%20-%20Building%20microservices%20using%20Spring%20Boot%2015630c6e8cbd80589563cde8e6dec26e/image%2019.png)

## 027 Assignment to build Loans & Cards microservices

building the cards microservice

```bash
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>
	<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>3.4.3</version>
		<relativePath/> <!-- lookup parent from repository -->
	</parent>
	<groupId>com.cgnexus</groupId>
	<artifactId>cards</artifactId>
	<version>0.0.1-SNAPSHOT</version>
	<name>cards</name>
	<description>cards microservice</description>
	<url/>
	<licenses>
		<license/>
	</licenses>
	<developers>
		<developer/>
	</developers>
	<scm>
		<connection/>
		<developerConnection/>
		<tag/>
		<url/>
	</scm>
	<properties>
		<java.version>21</java.version>
	</properties>
	<dependencies>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-actuator</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-jpa</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-validation</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-devtools</artifactId>
			<scope>runtime</scope>
			<optional>true</optional>
		</dependency>
		<dependency>
			<groupId>com.h2database</groupId>
			<artifactId>h2</artifactId>
			<scope>runtime</scope>
		</dependency>
		<dependency>
			<groupId>org.projectlombok</groupId>
			<artifactId>lombok</artifactId>
			<optional>true</optional>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>
		<dependency>
			<groupId>org.springdoc</groupId>
			<artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
			<version>2.8.5</version>
		</dependency>
	</dependencies>

	<build>
		<plugins>
			<plugin>
				<groupId>org.apache.maven.plugins</groupId>
				<artifactId>maven-compiler-plugin</artifactId>
				<configuration>
					<annotationProcessorPaths>
						<path>
							<groupId>org.projectlombok</groupId>
							<artifactId>lombok</artifactId>
						</path>
					</annotationProcessorPaths>
				</configuration>
			</plugin>
			<plugin>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-maven-plugin</artifactId>
				<configuration>
					<excludes>
						<exclude>
							<groupId>org.projectlombok</groupId>
							<artifactId>lombok</artifactId>
						</exclude>
					</excludes>
				</configuration>
			</plugin>
		</plugins>
	</build>

</project>

```

adding open API definition

```java
package com.cgnexus.cards;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "Cards Microservice REST API Documentation",
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
				description = "This is a RESTful API documentation for the Cards Microservice."
		)
)
public class CardsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CardsApplication.class, args);
	}

}

```

<aside>
➡️

adding application properties and server name and adding local profile

</aside>

```java
spring:
  application:
    name: cards
server:
  port: 8081
logging:
  pattern:
    console: ${LOG_PATTERN_CONSOLE:%green(%d{HH:mm:ss.SSS}) %blue(%-5level) %red([%thread]) %yellow(%logger{15}) - %msg%n}
```

```java
spring:
  datasource:
    url: jdbc:h2:mem:testdb
    driver-class-name: org.h2.Driver
    username: sa
    password: ''
  h2:
    console:
      enabled: true
  jpa:
    database-platform: org.hibernate.dialect.H2Dialect
    hibernate:
      ddl-auto: update
    show-sql: true
```

```java
package com.cgnexus.cards.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@Setter
@ToString
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {
    @CreatedDate
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @CreatedBy
    @Column(name = "created_by", updatable = false)
    private String createdBy;

    @LastModifiedDate
    @Column(name = "updated_at", insertable = false)
    private LocalDateTime updatedAt;

    @LastModifiedBy
    @Column(name = "updated_by", insertable = false)
    private String updatedBy;
}

```

```java
package com.cgnexus.cards.audit;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("auditAwareImpl")
public class AuditAwareImpl implements AuditorAware<String> {
    /**
     * Returns the current auditor name for entities being tracked by JPA auditing.
     * This implementation returns a constant value 'ACCOUNT_MS' as the auditor.
     *
     * @return an Optional containing the auditor name "ACCOUNT_MS"
     */
    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of("ACCOUNT_MS");
    }
}

```

```java
package com.cgnexus.cards;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title = "Cards Microservice REST API Documentation",
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
                description = "This is a RESTful API documentation for the Cards Microservice."
        )
)
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
public class CardsApplication {

    public static void main(String[] args) {
        SpringApplication.run(CardsApplication.class, args);
    }

}

```

```java
package com.cgnexus.cards.constants;

public class CardConstants {
    public static final String CREDIT_CARD = "Credit Card";
    public static final int NEW_CARD_LIMIT = 1_00_000;
    public static final String STATUS_201 = "201";
    public static final String MESSAGE_201 = "Card created successfully";
    public static final String STATUS_200 = "200";
    public static final String MESSAGE_200 = "Request processed successfully";
    public static final String STATUS_417 = "417";
    public static final String MESSAGE_417_UPDATE = "Update operation failed. Please try again or contact Dev team";
    public static final String MESSAGE_417_DELETE = "Delete operation failed. Please try again or contact Dev team";
    private CardConstants() {
        // restrict instantiation
    }
}

```

```java
package com.cgnexus.cards.service.repository;

import com.cgnexus.cards.entity.Cards;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CardRepository extends JpaRepository<Cards, Long> {
    Optional<Cards> findByMobileNumber(String mobileNumber);
}

```

```java
package com.cgnexus.cards.service;

public interface CardsService {

    void createCard(String mobileNumber);
}
 
```

```java
package com.cgnexus.cards.service.impl;

import com.cgnexus.cards.constants.CardConstants;
import com.cgnexus.cards.entity.Cards;
import com.cgnexus.cards.service.CardsService;
import com.cgnexus.cards.service.exception.CardAlreadyExistsException;
import com.cgnexus.cards.service.repository.CardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class CardsServiceImpl implements CardsService {

    private final CardRepository cardRepository;

    /**
     * @param mobileNumber
     */
    @Override
    public void createCard(String mobileNumber) {
        Optional<Cards> card = cardRepository.findByMobileNumber(mobileNumber);

        if (card.isPresent()) {
            throw new CardAlreadyExistsException("Card already exists for the given mobile number" + mobileNumber);
        }

        Cards newCard = createNewCard(mobileNumber);

        cardRepository.save(newCard);
    }

    /**
     * Creates a new credit card for the given mobile number.
     *
     * @param mobileNumber The mobile number associated with the card owner
     * @return A new {@link Cards} entity with default credit card settings
     * including randomly generated card number, default credit card type,
     * standard credit limit, and calculated available amount
     */
    private Cards createNewCard(String mobileNumber) {
        Cards newCard = new Cards();
        long randomCardNumber = 100000000000L + new Random().nextInt(900000000);
        newCard.setCardNumber(Long.toString(randomCardNumber));
        newCard.setMobileNumber(mobileNumber);
        newCard.setCardType(CardConstants.CREDIT_CARD);
        newCard.setTotalLimit(CardConstants.NEW_CARD_LIMIT);
        newCard.setAmountUsed(0);
        newCard.setAvailableAmount(CardConstants.NEW_CARD_LIMIT);
        return newCard;
    }

}

```

```java
package com.cgnexus.cards.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Schema(
        name = "ErrorResponse",
        description = "Schema to hold error response information"
)
public class ErrorResponseDto {
    @Schema(
            description = "API path invoked by client",
            example = "/v1/card"
    )
    private String apiPath;

    @Schema(
            description = "Error code representing the error happened",
            example = "400"
    )
    private HttpStatus errorCode;

    @Schema(
            description = "Error message representing the error happened",
            example = "Bad Request"
    )
    private String errorMessage;

    @Schema(
            description = "Time representing when the error happened",
            example = "2021-09-01T12:00:00"
    )
    private LocalDateTime errorTime;
}

```

```java
package com.cgnexus.cards.dto;

import lombok.Data;

@Data
public class ResponseDTO {

    private String statusCode;
    private String statusMessage;
}

```

```java
package com.cgnexus.cards.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@ToString
public class Cards extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cardId;

    private String mobileNumber;

    private String cardNumber;

    private String cardType;

    private int totalLimit;

    private int amountUsed;

    private int availableAmount;
}

```

```java
package com.cgnexus.cards.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class CardAlreadyExistsException extends RuntimeException {
    public CardAlreadyExistsException(String s) {
        super(s);
    }
}

```

```java
package com.cgnexus.cards.service.exception;

import com.cgnexus.cards.dto.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CardAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDto> handleCardAlreadyExistsException(CardAlreadyExistsException exception,
                                                                             WebRequest webRequest) {
        ErrorResponseDto errorResponseDTO = new ErrorResponseDto(
                webRequest.getDescription(false),
                HttpStatus.BAD_REQUEST,
                exception.getMessage(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorResponseDTO, HttpStatus.BAD_REQUEST);
    }
}

```

```java
package com.cgnexus.cards.controller;

import com.cgnexus.cards.dto.ErrorResponseDto;
import com.cgnexus.cards.dto.ResponseDTO;
import com.cgnexus.cards.service.CardsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v1/", produces = {MediaType.APPLICATION_JSON_VALUE})
@Tag(
        name = "CRUD REST APIs for Cards",
        description = "Create, Read, Update and Delete card details"
)
@RequiredArgsConstructor
@Validated
public class CardsController {

    private final CardsService cardsService;

    @Operation(
            summary = "Create Card REST API",
            description = "REST API to create new card"
    )
    @ApiResponses({

            @ApiResponse(
                    responseCode = "201",
                    description = "HTTP Status CREATED",
                    useReturnTypeSchema = true
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "HTTP Status BAD REQUEST",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    @PostMapping("/card")
    public ResponseEntity<ResponseDTO> createCard(
            @Parameter(
                    description = "Mobile number of the customer",
                    example = "9876543210",
                    schema = @Schema(
                            type = "string",
                            format = "string",
                            pattern = "(^$|[0-9]{10})"
                    )
            )
            @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number should be valid")
            @RequestParam String mobileNumber) {
        cardsService.createCard(mobileNumber);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDTO());
    }
}
```

```java
CREATE TABLE IF NOT EXISTS `cards`
(
    `card_id`          int          NOT NULL AUTO_INCREMENT,
    `mobile_number`    varchar(15)  NOT NULL,
    `card_number`      varchar(100) NOT NULL,
    `card_type`        varchar(100) NOT NULL,
    `total_limit`      int          NOT NULL,
    `amount_used`      int          NOT NULL,
    `available_amount` int          NOT NULL,
    `created_at`       date         NOT NULL,
    `created_by`       varchar(20)  NOT NULL,
    `updated_at`       date        DEFAULT NULL,
    `updated_by`       varchar(20) DEFAULT NULL,
    PRIMARY KEY (`card_id`)

```

<aside>
➡️

adding get card detail by mobile number endpoint

</aside>

```java
package com.cgnexus.cards.controller;

import com.cgnexus.cards.dto.CardsDto;
import com.cgnexus.cards.dto.ErrorResponseDto;
import com.cgnexus.cards.dto.ResponseDTO;
import com.cgnexus.cards.service.CardsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/v1/", produces = {MediaType.APPLICATION_JSON_VALUE})
@Tag(
        name = "CRUD REST APIs for Cards",
        description = "Create, Read, Update and Delete card details"
)
@RequiredArgsConstructor
@Validated
public class CardsController {

    private final CardsService cardsService;

    

    @Operation(
            summary = "Fetch Card Details REST API",
            description = "REST API to fetch card details based on mobile number"
    )
    @ApiResponses(
            {
                    @ApiResponse(
                            responseCode = "200",
                            description = "HTTP Status OK",
                            useReturnTypeSchema = true
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "HTTP Status NOT FOUND",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorResponseDto.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "HTTP Status BAD REQUEST",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorResponseDto.class)
                            )
                    )
            }
    )
    @GetMapping("/card")
    public ResponseEntity<CardsDto> fetchCardDetails(
            @Parameter(
                    description = "Mobile number of the customer",
                    example = "9876543210",
                    schema = @Schema(
                            type = "string",
                            format = "string",
                            pattern = "(^$|[0-9]{10})"
                    )
            )
            @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number should be valid")
            @RequestParam("mobile-number") String mobileNumber) {
        CardsDto cardsDto = cardsService.fetchCardDetails(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK).body(cardsDto);
    }

}

```

```java
package com.cgnexus.cards.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Schema(
        name = "Cards",
        description = "Schema to hold Card information"
)
@Data
public class CardsDto {

    @NotEmpty(message = "Mobile Number can not be a null or empty")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile Number must be 10 digits")
    @Schema(
            description = "Mobile Number of Customer", example = "4354437687"
    )
    private String mobileNumber;

    @NotEmpty(message = "Card Number can not be a null or empty")
    @Pattern(regexp = "(^$|[0-9]{12})", message = "CardNumber must be 12 digits")
    @Schema(
            description = "Card Number of the customer", example = "100646930341"
    )
    private String cardNumber;

    @NotEmpty(message = "CardType can not be a null or empty")
    @Schema(
            description = "Type of the card", example = "Credit Card"
    )
    private String cardType;

    @Positive(message = "Total card limit should be greater than zero")
    @Schema(
            description = "Total amount limit available against a card", example = "100000"
    )
    private int totalLimit;

    @PositiveOrZero(message = "Total amount used should be equal or greater than zero")
    @Schema(
            description = "Total amount used by a Customer", example = "1000"
    )
    private int amountUsed;

    @PositiveOrZero(message = "Total available amount should be equal or greater than zero")
    @Schema(
            description = "Total available amount against a card", example = "90000"
    )
    private int availableAmount;

}
```

```java
package com.cgnexus.cards.service.mapper;

import com.cgnexus.cards.dto.CardsDto;
import com.cgnexus.cards.entity.Cards;

public class CardsMapper {

    public static CardsDto mapToCardsDto(Cards cards, CardsDto cardsDto) {
        cardsDto.setCardNumber(cards.getCardNumber());
        cardsDto.setCardType(cards.getCardType());
        cardsDto.setMobileNumber(cards.getMobileNumber());
        cardsDto.setTotalLimit(cards.getTotalLimit());
        cardsDto.setAvailableAmount(cards.getAvailableAmount());
        cardsDto.setAmountUsed(cards.getAmountUsed());
        return cardsDto;
    }

    public static Cards mapToCards(CardsDto cardsDto, Cards cards) {
        cards.setCardNumber(cardsDto.getCardNumber());
        cards.setCardType(cardsDto.getCardType());
        cards.setMobileNumber(cardsDto.getMobileNumber());
        cards.setTotalLimit(cardsDto.getTotalLimit());
        cards.setAvailableAmount(cardsDto.getAvailableAmount());
        cards.setAmountUsed(cardsDto.getAmountUsed());
        return cards;
    }

}
```

```java
package com.cgnexus.cards.service;

import com.cgnexus.cards.dto.CardsDto;

public interface CardsService {

    CardsDto fetchCardDetails(String mobileNumber);
}

```

```java
package com.cgnexus.cards.service.impl;

import com.cgnexus.cards.constants.CardConstants;
import com.cgnexus.cards.dto.CardsDto;
import com.cgnexus.cards.entity.Cards;
import com.cgnexus.cards.service.CardsService;
import com.cgnexus.cards.service.exception.CardAlreadyExistsException;
import com.cgnexus.cards.service.exception.ResourceNotFoundException;
import com.cgnexus.cards.service.mapper.CardsMapper;
import com.cgnexus.cards.service.repository.CardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class CardsServiceImpl implements CardsService {

    private final CardRepository cardsRepository;
    
    @Override
    public CardsDto fetchCardDetails(String mobileNumber) {
        Cards cards = cardsRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Card", "mobileNumber", mobileNumber)
        );
        return CardsMapper.mapToCardsDto(cards, new CardsDto());

    }
}

```

```java
package com.cgnexus.cards.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String resourceName, String fieldName, String fieldValue) {
        super(String.format("%s not found with the given input data %s : '%s'", resourceName, fieldName, fieldValue));
    }
}

```

```java
package com.cgnexus.cards.service.exception;

import com.cgnexus.cards.dto.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleResourceNotFoundException(ResourceNotFoundException exception,
                                                                            WebRequest webRequest) {
        ErrorResponseDto errorResponseDTO = new ErrorResponseDto(
                webRequest.getDescription(false),
                HttpStatus.NOT_FOUND,
                exception.getMessage(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorResponseDTO, HttpStatus.NOT_FOUND);
    }

}

```

```java

```

```java

```

```java

```

## 028 Deep dive and demo of Loans microservice

## 029 Deep dive and demo of Cards microservice