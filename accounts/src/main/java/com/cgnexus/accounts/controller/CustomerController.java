package com.cgnexus.accounts.controller;

import com.cgnexus.accounts.dto.CustomerDetailsDTO;
import com.cgnexus.accounts.dto.ErrorResponseDTO;
import com.cgnexus.accounts.service.ICustomerDetailsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "Fetch Customer Details REST APIs",
        description = "REST API to fetch customer details"
)
@RestController
@RequestMapping(value = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@RequiredArgsConstructor
@Validated
@Slf4j
public class CustomerController {

    private final ICustomerDetailsService iCustomerDetailsService;

    @Operation(
            summary = "Fetch Account,Customer,Loans and Cards Details REST API",
            description = "REST API to fetch Account,Customer,Loans and Cards Details based on mobile number"
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
    @GetMapping("/fetchCustomerDetails")
    public ResponseEntity<CustomerDetailsDTO> fetchCustomerDetails(
            @RequestHeader(value = "cgnexus-correlation-id") String correlationId,
            @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number should be valid")
            @RequestParam("mobileNumber") String mobileNumber
    ) {
        log.debug("fetch customer details by mobile number: {} stated", mobileNumber);
        CustomerDetailsDTO customerDetailsDTO = iCustomerDetailsService.fetchCustomerDetailsByMobileNumber(correlationId, mobileNumber);
        log.debug("fetch customer details by mobile number: {} completed", mobileNumber);
        return ResponseEntity.ok(customerDetailsDTO);
    }
}
