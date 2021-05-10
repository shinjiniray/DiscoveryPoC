package com.poc.CustomerWrapper.dto;

public class ExceptionResponse {

    private String errorMessage;
    private String code;
    private String type;

    public ExceptionResponse(String errorMessage, String code, String type) {
        this.errorMessage = errorMessage;
        this.code = code;
        this.type = type;
    }
    public ExceptionResponse() {
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
