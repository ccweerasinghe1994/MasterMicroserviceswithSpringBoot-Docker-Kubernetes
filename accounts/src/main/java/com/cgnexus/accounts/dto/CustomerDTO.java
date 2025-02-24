package com.cgnexus.accounts.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CustomerDTO {

    @NotEmpty(message = "Name is required")
    @Size(min = 3, max = 50, message = "Name should be between 3 to 50 characters")
    private String name;

    @NotEmpty(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;

    @NotEmpty(message = "Mobile number is required")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number should be valid")
    private String mobileNumber;

    private AccountDTO accountDTO;
}
