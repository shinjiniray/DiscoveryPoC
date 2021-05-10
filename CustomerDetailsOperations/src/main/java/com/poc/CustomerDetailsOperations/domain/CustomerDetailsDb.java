package com.poc.CustomerDetailsOperations.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

@Entity
@Table(name="CustomerDetails")
@ApiModel(value="Customer Details model")
public class CustomerDetailsDb {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="CustomerDetailsId", nullable=false)
    @ApiModelProperty(notes = "Unique customer details ID")
    private int customerDetailsId;

    @Column(name="CustomerId")
    @ApiModelProperty(notes = "Customer ID")
    private int customerId;

    @Column(name="Address")
    @ApiModelProperty(notes = "Customer address")
    private String address;

    @Column(name="Phone")
    @ApiModelProperty(notes = "Customer phone")
    private String phone;

    @Column(name="Email")
    @ApiModelProperty(notes = "Customer email")
    private String email;

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
