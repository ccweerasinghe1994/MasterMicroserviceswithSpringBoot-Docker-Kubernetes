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
