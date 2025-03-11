package com.cgnexus.cards.controller;


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
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/v1/", produces = {MediaType.APPLICATION_JSON_VALUE})
@Tag(
        name = "CRUD REST APIs for Cards",
        description = "Create, Read, Update and Delete card details"
)
@RequiredArgsConstructor
@Validated
public class CardsController {

    private final CardsService cardsService;

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
    @PostMapping("/card")
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
    @GetMapping("/card")
    public ResponseEntity<CardsDto> fetchCardDetails(
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
            @RequestParam("mobile-number") String mobileNumber) {
        CardsDto cardsDto = cardsService.fetchCardDetails(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK).body(cardsDto);
    }

}

//    fetchCardDetails using mobileNumber
//    updateCardDetails
// deleteCardDetails
