package com.poc.CustomerWrapper.dto;

import java.util.List;

public class CustomerExceptionResponse {

    private List<ExceptionResponse> exceptionResponse;

    public CustomerExceptionResponse(List<ExceptionResponse> exceptionResponse) {
        this.exceptionResponse = exceptionResponse;
    }
    public CustomerExceptionResponse() {
    }

    public List<ExceptionResponse> getExceptionResponse() {
        return exceptionResponse;
    }

    public void setExceptionResponse(List<ExceptionResponse> exceptionResponse) {
        this.exceptionResponse = exceptionResponse;
    }
}
