package com.cgnexus.accounts.exception;

import com.cgnexus.accounts.dto.ErrorResponseDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {


    /**
     * Handle validation errors when method arguments fail validation.
     *
     * @param ex      the exception containing validation errors.
     * @param headers the headers to be written to the response.
     * @param status  the status to be written to the response.
     * @param request the current web request.
     * @return a response entity with error details.
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Map<String, String> validationErrors = new HashMap<>();
        List<ObjectError> validationErrorList = ex.getBindingResult().getAllErrors();

        validationErrorList.forEach(objectError -> {
            String fieldName = ((FieldError) objectError).getField();
            String validationMessage = objectError.getDefaultMessage();
            validationErrors.put(fieldName, validationMessage);
        });

        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(request.getDescription(false), HttpStatus.BAD_REQUEST, validationErrors.toString(), LocalDateTime.now());

        return new ResponseEntity<>(errorResponseDTO, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handle validation errors when method arguments fail validation.
     *
     * @param ex      the exception containing validation errors.
     * @param request the current web request.
     * @return a response entity with error details.
     */
    @ExceptionHandler(CustomerAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDTO> handleCustomerAlreadyExistsException(CustomerAlreadyExistsException ex, WebRequest request) {
        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(request.getDescription(false), HttpStatus.BAD_REQUEST, ex.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(errorResponseDTO, HttpStatus.BAD_REQUEST);
    }


    /**
     * Handle validation errors when method arguments fail validation.
     *
     * @param exception the exception containing validation errors.
     * @param request   the current web request.
     * @return a response entity with error details.
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleCustomerNotFoundException(ResourceNotFoundException exception, WebRequest request) {
        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(request.getDescription(false), HttpStatus.BAD_REQUEST, exception.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(errorResponseDTO, HttpStatus.NOT_FOUND);
    }

    /**
     * Handle all other exceptions.
     *
     * @param ex      the exception to handle.
     * @param request the current web request.
     * @return a response entity with error details.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> handleAllExceptions(Exception ex, WebRequest request) {
        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(request.getDescription(false), HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(errorResponseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}