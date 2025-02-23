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
