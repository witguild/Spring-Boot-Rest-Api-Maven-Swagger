package com.service.prime.controller;

import com.service.prime.model.ResponseType;
import com.service.prime.model.ServiceResponse;
import com.service.prime.service.PrimeNumberServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
public class PrimeNumberController {

    @Autowired
    private PrimeNumberServiceImpl primeNumberService;

    private static final String INVALID_UPPER_LIMIT = "%d upper limit in request is less than minimum required limit 2";
    private static final String PRIME_NUMBER_FOUND_MESSAGE = "Found %d prime numbers for %d upper limit";
    private static final String REQUEST_PROCESSED_LOGS = "Request successfully processed with %d upper limit found %d prime numbers";
    private static final Logger log = LoggerFactory.getLogger(PrimeNumberController.class);
    private static final String INSIDE_GET_PRIME_NUMBER_CONTROLLER_LOGS = "Request inside controller with %d upper limit";


    @org.springframework.beans.factory.annotation.Autowired
    public PrimeNumberController(PrimeNumberServiceImpl primeNumberService) {
        this.primeNumberService = primeNumberService;
    }

    @GetMapping(path = "/list/{upper_limit}")
    @Operation(summary = "get prime number list",
            description = "calculate and return a collection of all prime numbers up to a specified upper limit in number",
            tags = {"prime-number"})
    public ResponseEntity<ServiceResponse> getPrimeNumbers(@PathVariable("upper_limit") long upperLimit) {
        log.debug(INSIDE_GET_PRIME_NUMBER_CONTROLLER_LOGS, upperLimit);

        // checking if the upper limit to find the prime number is valid to process
        if (upperLimit > 1) {

            // calling get prime numbers method to process the request
            List<Long> primeNumbers = primeNumberService.getPrimeNumbers(upperLimit);

            // constructing service response for success
            ServiceResponse serviceResponse = new ServiceResponse(HttpStatus.OK.value(), ResponseType.SUCCESS,
                    String.format(PRIME_NUMBER_FOUND_MESSAGE, primeNumbers.size(), upperLimit));

            serviceResponse.setPrimeNumbers(primeNumbers);

            log.debug(REQUEST_PROCESSED_LOGS, upperLimit, primeNumbers.size());

            return new ResponseEntity<ServiceResponse>(serviceResponse, HttpStatus.OK);
        } else {

            log.error(INVALID_UPPER_LIMIT);

            // constructing service response for failure/error
            ServiceResponse serviceResponse = new ServiceResponse(HttpStatus.BAD_REQUEST.value(), ResponseType.FAILURE,
                    String.format(INVALID_UPPER_LIMIT, upperLimit));

            return new ResponseEntity<ServiceResponse>(serviceResponse, HttpStatus.BAD_REQUEST);
        }
    }

}
