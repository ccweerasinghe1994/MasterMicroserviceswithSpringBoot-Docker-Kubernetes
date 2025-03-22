package com.cgnexus.accounts.controller;

import com.cgnexus.accounts.dto.AccountContactInfoDTO;
import com.cgnexus.accounts.dto.CustomerDTO;
import com.cgnexus.accounts.dto.ErrorResponseDTO;
import com.cgnexus.accounts.dto.ResponseDTO;
import com.cgnexus.accounts.service.IAccountService;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
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
@Slf4j
public class AccountsController {
    private final IAccountService accountService;

    private final Environment environment;

    private final AccountContactInfoDTO accountContactInfoDTO;

    @Value("${build.version}")
    private String buildVersion;

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
    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> createAccount(
            @RequestHeader(value = "cgnexus-correlation-id") String correlationId,
            @Valid @RequestBody CustomerDTO customerDTO) {
        log.debug("cgnexus-correlation-id found: {}", correlationId);
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
    @GetMapping("/fetch")
    public ResponseEntity<CustomerDTO> fetchAccountDetails(
            @RequestHeader(value = "cgnexus-correlation-id") String correlationId,
            @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number should be valid")
            @RequestParam("mobileNumber") String mobileNumber
    ) {
        log.debug("cgnexus-correlation-id found: {}", correlationId);
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
    public ResponseEntity<ResponseDTO> updateAccount(
            @RequestHeader(value = "cgnexus-correlation-id") String correlationId,
            @Valid @RequestBody CustomerDTO customerDTO
    ) {
        log.debug("cgnexus-correlation-id found: {}", correlationId);
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
            @RequestHeader(value = "cgnexus-correlation-id") String correlationId,
            @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number should be valid")
            @RequestParam("mobile-number") String mobileNumber
    ) {
        log.debug("cgnexus-correlation-id found: {}", correlationId);
        accountService.deleteAccount(mobileNumber);
        return ResponseEntity.ok(new ResponseDTO(STATUS_200, MESSAGE_200));
    }

    @Operation(
            summary = "Get Build Version REST API",
            description = "REST API to get the build version"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status INTERNAL SERVER ERROR"
            )
    })
    @GetMapping("/build-info")
    @Retry(name = "getBuildVersion", fallbackMethod = "getBuildVersionFallBack")
    public ResponseEntity<String> getBuildVersion(@RequestHeader(value = "cgnexus-correlation-id") String correlationId) {
        log.debug("getBuildVersion method called");
        log.debug("cgnexus-correlation-id found: {}", correlationId);
        throw new NullPointerException();
//        return ResponseEntity.ok(buildVersion);
    }

    public ResponseEntity<String> getBuildVersionFallBack(@RequestHeader(value = "cgnexus-correlation-id") String correlationId, Throwable throwable) {
        log.debug("getBuildVersionFallBack method called");
        log.debug("cgnexus-correlation-id found: {}", correlationId);
        return ResponseEntity.ok(buildVersion);
    }


    @Operation(
            summary = "Get Java Version REST API",
            description = "REST API to get the Java version"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status INTERNAL SERVER ERROR"
            )
    })
    @GetMapping("/java-version")
    @RateLimiter(name = "getJavaVersion", fallbackMethod = "getJavaVersionFallback")
    public ResponseEntity<String> getJavaVersion(@RequestHeader(value = "cgnexus-correlation-id") String correlationId) {
        log.debug("cgnexus-correlation-id found: {}", correlationId);
        return ResponseEntity.ok(environment.getProperty("JAVA_HOME"));
    }

    public ResponseEntity<String> getJavaVersionFallback(@RequestHeader(value = "cgnexus-correlation-id") String correlationId, Throwable throwable) {
        log.debug("cgnexus-correlation-id found: {}", correlationId);
        return ResponseEntity.ok("Java version 21");
    }

    @Operation(
            summary = "Get Java Version REST API",
            description = "REST API to get the Java version"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status INTERNAL SERVER ERROR"
            )
    })
    @GetMapping("/contact-info")
    public ResponseEntity<AccountContactInfoDTO> getContactInformation(@RequestHeader(value = "cgnexus-correlation-id") String correlationId) {
        log.debug("cgnexus-correlation-id found: {}", correlationId);
        return ResponseEntity.ok(accountContactInfoDTO);
    }
}
