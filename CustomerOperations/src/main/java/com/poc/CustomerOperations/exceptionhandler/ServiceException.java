package com.poc.CustomerOperations.exceptionhandler;

public class ServiceException extends Exception {

    private String type;
    private String code;
    private String message;

    public ServiceException(String type, String code, String message) {
        this.type= type;
        this.code = code;
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}

