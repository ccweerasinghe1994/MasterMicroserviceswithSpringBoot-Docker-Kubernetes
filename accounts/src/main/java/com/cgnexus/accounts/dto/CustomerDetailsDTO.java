package com.cgnexus.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(
        name = "CustomerDetails",
        description = "Schema to hold Customer,Cards,Loans and Account  information"
)
public class CustomerDetailsDTO {

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

    @Schema(
            description = "Loans details of the customer"
    )
    private LoansDto loansDto;

    @Schema(
            description = "Cards details of the customer"
    )
    private CardsDto cardsDto;

}
