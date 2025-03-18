package com.cgnexus.accounts.service.client;

import com.cgnexus.accounts.dto.LoansDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "loans")
public interface LoansFeignClient {

    @GetMapping(value = "/api/fetch", consumes = "application/json")
    public ResponseEntity<LoansDto> fetchLoanDetails(String mobileNumber);
}
