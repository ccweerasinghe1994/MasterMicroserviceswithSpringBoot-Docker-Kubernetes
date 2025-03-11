package com.cgnexus.cards.controller;


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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDTO());
    }

}


//    createCard
//    fetchCardDetails using mobileNumber
//    updateCardDetails
// deleteCardDetails
