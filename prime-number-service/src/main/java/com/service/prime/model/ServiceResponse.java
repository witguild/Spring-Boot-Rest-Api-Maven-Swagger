package com.service.prime.model;

import java.util.ArrayList;
import java.util.List;

public class ServiceResponse {

    private int statusCode;

    private ResponseType responseType;

    private String message;

    private List<Long> primeNumbers = new ArrayList<>();

    public ServiceResponse(){
    }

    public ServiceResponse(int statusCode, ResponseType responseType, String message) {
        this.statusCode = statusCode;
        this.responseType = responseType;
        this.message = message;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public ResponseType getResponseType() {
        return responseType;
    }

    public void setResponseType(ResponseType responseType) {
        this.responseType = responseType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Long> getPrimeNumbers() {
        return primeNumbers;
    }

    public void setPrimeNumbers(List<Long> primeNumbers) {
        this.primeNumbers = primeNumbers;
    }
}
