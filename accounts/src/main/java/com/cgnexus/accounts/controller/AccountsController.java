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
