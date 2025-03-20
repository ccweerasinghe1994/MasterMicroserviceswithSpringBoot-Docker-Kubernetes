package com.cgnexus.cards.controller;


import com.cgnexus.cards.constants.CardConstants;
import com.cgnexus.cards.dto.CardsContactInfoDTO;
import com.cgnexus.cards.dto.CardsDto;
import com.cgnexus.cards.dto.ErrorResponseDto;
import com.cgnexus.cards.dto.ResponseDTO;
import com.cgnexus.cards.service.CardsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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

@RestController
@RequestMapping(value = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@Tag(
        name = "CRUD REST APIs for Cards",
        description = "Create, Read, Update and Delete card details"
)
@RequiredArgsConstructor
@Validated
@Slf4j
public class CardsController {

    private final CardsService cardsService;
    private final Environment environment;
    private final CardsContactInfoDTO accountContactInfoDTO;

    @Value("${build.version}")
    private String buildVersion;

    @Operation(
            summary = "Create Card REST API",
            description = "REST API to create new card"
    )
    @ApiResponses({


            @ApiResponse(
                    responseCode = "201",
                    description = "HTTP Status CREATED",
                    useReturnTypeSchema = true
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "HTTP Status BAD REQUEST",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> createCard(
            @Parameter(
                    description = "Mobile number of the customer",
                    example = "9876543210",
                    schema = @Schema(
                            type = "string",
                            format = "string",
                            pattern = "(^$|[0-9]{10})"
                    )
            )
            @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number should be valid")
            @RequestParam String mobileNumber) {
        cardsService.createCard(mobileNumber);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDTO(Integer.toString(HttpStatus.CREATED.value()), "Card created successfully"));
    }

    @Operation(
            summary = "Fetch Card Details REST API",
            description = "REST API to fetch card details based on mobile number"
    )
    @ApiResponses(
            {
                    @ApiResponse(
                            responseCode = "200",
                            description = "HTTP Status OK",
                            useReturnTypeSchema = true
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "HTTP Status NOT FOUND",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorResponseDto.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "HTTP Status BAD REQUEST",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorResponseDto.class)
                            )
                    )
            }
    )
    @GetMapping("/fetch")
    public ResponseEntity<CardsDto> fetchCardDetails(
            @RequestHeader(value = "cgnexus-correlation-id") String correlationId,
            @Parameter(
                    description = "Mobile number of the customer",
                    example = "9876543210",
                    schema = @Schema(
                            type = "string",
                            format = "string",
                            pattern = "(^$|[0-9]{10})"
                    )
            )
            @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number should be valid")
            @RequestParam("mobileNumber") String mobileNumber) {
        log.debug("cgnexus-correlation-id found: {}", correlationId);
        CardsDto cardsDto = cardsService.fetchCardDetails(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK).body(cardsDto);
    }

    @Operation(
            summary = "Update Card Details REST API",
            description = "REST API to update card details based on mobile number"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "Expectation Failed"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    @PutMapping("/card")
    public ResponseEntity<ResponseDTO> updateCardDetails(
            @RequestHeader(value = "cgnexus-correlation-id") String correlationId,
            @Valid @RequestBody CardsDto cardsDto
    ) {
        log.debug("cgnexus-correlation-id found: {}", correlationId);
        boolean isUpdated = cardsService.updateCard(cardsDto);
        if (isUpdated) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDTO(CardConstants.STATUS_200, CardConstants.MESSAGE_200));
        } else {
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDTO(CardConstants.STATUS_417, CardConstants.MESSAGE_417_UPDATE));
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDTO> deleteCardDetails(
            @RequestHeader(value = "cgnexus-correlation-id") String correlationId,
            @RequestParam
            @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
            String mobileNumber
    ) {
        log.debug("cgnexus-correlation-id found: {}", correlationId);
        boolean isDeleted = cardsService.deleteCard(mobileNumber);
        if (isDeleted) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDTO(CardConstants.STATUS_200, CardConstants.MESSAGE_200));
        } else {
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDTO(CardConstants.STATUS_417, CardConstants.MESSAGE_417_DELETE));
        }
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
    public ResponseEntity<String> getBuildVersion(@RequestHeader(value = "cgnexus-correlation-id") String correlationId) {
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
    public ResponseEntity<String> getJavaVersion(@RequestHeader(value = "cgnexus-correlation-id") String correlationId) {
        log.debug("cgnexus-correlation-id found: {}", correlationId);
        return ResponseEntity.ok(environment.getProperty("JAVA_HOME"));
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
    public ResponseEntity<CardsContactInfoDTO> getContactInformation(@RequestHeader(value = "cgnexus-correlation-id") String correlationId) {
        log.debug("cgnexus-correlation-id found: {}", correlationId);
        return ResponseEntity.ok(accountContactInfoDTO);
    }

}
