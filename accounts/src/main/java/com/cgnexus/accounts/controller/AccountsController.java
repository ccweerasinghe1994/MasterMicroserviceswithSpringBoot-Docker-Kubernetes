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
