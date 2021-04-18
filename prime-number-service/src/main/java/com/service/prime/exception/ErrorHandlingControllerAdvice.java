// Copyright AB Trav och Galopp (556180-4161)

package com.service.prime.exception;


import com.service.prime.model.ResponseType;
import com.service.prime.model.ServiceResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@RestControllerAdvice
@RestController
public class ErrorHandlingControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ServiceResponse> handleAllExceptions(Exception ex, WebRequest request) {
        ServiceResponse serviceResponse = new ServiceResponse();
        serviceResponse.setMessage(ex.getMessage());
        serviceResponse.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        serviceResponse.setResponseType(ResponseType.FAILURE);
        return new ResponseEntity<>(serviceResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
