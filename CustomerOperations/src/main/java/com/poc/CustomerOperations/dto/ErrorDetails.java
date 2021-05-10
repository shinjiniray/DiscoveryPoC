package com.poc.CustomerOperations.dto;

import com.poc.CustomerOperations.exceptionhandler.ExceptionResponse;

import java.util.List;

public class ErrorDetails {

    private List<ExceptionResponse> exceptionResponse;

    public List<ExceptionResponse> getExceptionResponse() {
        return exceptionResponse;
    }

    public void setExceptionResponse(List<ExceptionResponse> exceptionResponse) {
        this.exceptionResponse = exceptionResponse;
    }
}
