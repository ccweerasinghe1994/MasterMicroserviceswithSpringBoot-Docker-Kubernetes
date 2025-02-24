package com.cgnexus.accounts.exception;

import com.cgnexus.accounts.dto.ErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomerAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDTO> handleCustomerAlreadyExistsException(CustomerAlreadyExistsException ex, WebRequest request) {
        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(request.getDescription(false), HttpStatus.BAD_REQUEST, ex.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(errorResponseDTO, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleCustomerNotFoundException(ResourceNotFoundException exception, WebRequest request) {
        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(request.getDescription(false), HttpStatus.BAD_REQUEST, exception.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(errorResponseDTO, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> handleAllExceptions(Exception ex, WebRequest request) {
        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(request.getDescription(false), HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(errorResponseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}