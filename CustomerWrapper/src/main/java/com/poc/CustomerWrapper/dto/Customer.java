package com.poc.CustomerWrapper.dto;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class Customer {

    @ApiModelProperty(notes = "Unique customer ID")
    private int customerId;
    @ApiModelProperty(notes = "Customer first name")
    private String firstName;
    @ApiModelProperty(notes = "Customer last name")
    private String lastName;
    @ApiModelProperty(notes = "Customer date of birth")
    private String dateOfBirth;
    @ApiModelProperty(notes = "Customer details")
    private List<CustomerDetails> customerDetails;
    private List<ExceptionResponse> exceptionResponse;

    public Customer(int customerId, String firstName, String lastName, String dateOfBirth, List<CustomerDetails> customerDetails) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.customerDetails = customerDetails;
    }
    public Customer() {
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public List<CustomerDetails> getCustomerDetails() {
        return customerDetails;
    }

    public void setCustomerDetails(List<CustomerDetails> customerDetails) {
        this.customerDetails = customerDetails;
    }

    public List<ExceptionResponse> getExceptionResponse() {
        return exceptionResponse;
    }

    public void setExceptionResponse(List<ExceptionResponse> exceptionResponse) {
        this.exceptionResponse = exceptionResponse;
    }
}
