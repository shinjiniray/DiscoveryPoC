package com.poc.CustomerWrapper.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="Customer Details model")
public class CustomerDetails {

    @ApiModelProperty(notes = "Unique customer details ID")
    private int customerDetailsId;
    @ApiModelProperty(notes = "Customer ID")
    private int customerId;
    @ApiModelProperty(notes = "Customer address")
    private String address;
    @ApiModelProperty(notes = "Customer phone")
    private String phone;
    @ApiModelProperty(notes = "Customer email")
    private String email;

    public CustomerDetails(int customerDetailsId, int customerId, String address, String phone, String email) {
        this.customerDetailsId = customerDetailsId;
        this.customerId = customerId;
        this.address = address;
        this.phone = phone;
        this.email = email;
    }
    public CustomerDetails() {
    }

    public int getCustomerDetailsId() {
        return customerDetailsId;
    }

    public void setCustomerDetailsId(int customerDetailsId) {
        this.customerDetailsId = customerDetailsId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
