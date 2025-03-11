package com.cgnexus.cards.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(
        name = "Response",
        description = "Schema to hold response information"
)
public class ResponseDTO {

    @Schema(
            description = "HTTP status code of the response",
            example = "200"
    )
    private String statusCode;

    @Schema(
            description = "Message of the response",
            example = "Operation successful"
    )
    private String statusMessage;
}
