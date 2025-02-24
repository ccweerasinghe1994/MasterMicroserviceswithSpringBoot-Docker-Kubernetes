package com.cgnexus.accounts.controller;

import com.cgnexus.accounts.dto.CustomerDTO;
import com.cgnexus.accounts.dto.ResponseDTO;
import com.cgnexus.accounts.service.IAccountService;
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

@RestController
@RequestMapping(value = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@RequiredArgsConstructor
@Validated
public class AccountsController {
    private final IAccountService accountService;

    @PostMapping("/account")
    public ResponseEntity<ResponseDTO> createAccount(@Valid @RequestBody CustomerDTO customerDTO) {
        accountService.createAccount(customerDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDTO(STATUS_201, MESSAGE_201));
    }

    @GetMapping("/account")
    public ResponseEntity<CustomerDTO> fetchAccountDetails(
            @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number should be valid")
            @RequestParam("mobile-number") String mobileNumber
    ) {
        CustomerDTO customerDTO = accountService.fetchAccountByMobileNumber(mobileNumber);
        return ResponseEntity.ok(customerDTO);
    }

    @PutMapping("/account")
    public ResponseEntity<ResponseDTO> updateAccount(@Valid @RequestBody CustomerDTO customerDTO) {
        boolean isUpdated = accountService.updateAccount(customerDTO);

        if (isUpdated) {
            return ResponseEntity.ok(new ResponseDTO(STATUS_200, MESSAGE_200));
        } else {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ResponseDTO(STATUS_500, MESSAGE_500));
        }
    }

    @DeleteMapping("/account")
    public ResponseEntity<ResponseDTO> deleteAccount(
            @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number should be valid")
            @RequestParam("mobile-number") String mobileNumber
    ) {
        accountService.deleteAccount(mobileNumber);
        return ResponseEntity.ok(new ResponseDTO(STATUS_200, MESSAGE_200));

    }
}
