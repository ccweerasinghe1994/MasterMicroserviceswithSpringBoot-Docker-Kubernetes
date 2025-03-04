package com.cgnexus.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Schema(
    name = "ErrorResponse",
    description = "Schema to hold error response information"
)
public class ErrorResponseDTO {
    @Schema(
        description = "Path of the API that returned the error",
        example = "/api/accounts"
    )
    private String apiPath;

    @Schema(
        description = "HTTP status code of the error",
        example = "404"
    )
    private HttpStatus errorCode;

    @Schema(
        description = "Message of the error",
        example = "Resource not found"
    )
    private String errorMessage;

    @Schema(
        description = "Timestamp of the error",
        example = "2024-01-01 10:00:00"
    )
    private LocalDateTime errorTime;

}
