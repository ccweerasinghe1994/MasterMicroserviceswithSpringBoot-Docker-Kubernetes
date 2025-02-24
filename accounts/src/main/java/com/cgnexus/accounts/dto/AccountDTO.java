package com.cgnexus.accounts.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class AccountDTO {
    @NotEmpty(message = "Account number is required")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Account number should be valid")
    private Long accountNumber;

    @NotEmpty(message = "Account type is required")
    private String accountType;

    @NotEmpty(message = "Branch address is required")
    private String branchAddress;
}
