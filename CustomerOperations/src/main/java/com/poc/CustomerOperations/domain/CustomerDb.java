package com.poc.CustomerOperations.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

@Entity
@Table(name="Customer")
@ApiModel(value="Customer model")
public class CustomerDb {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="CustomerId", nullable=false)
    @ApiModelProperty(notes = "Unique customer ID")
    private int customerId;

    @Column(name="FirstName")
    @ApiModelProperty(notes = "Customer first name")
    private String firstName;

    @Column(name="LastName")
    @ApiModelProperty(notes = "Customer last name")
    private String lastName;

    @Column(name="DateOfBirth")
    @ApiModelProperty(notes = "Customer date of birth")
    private String dateOfBirth;

    public CustomerDb(int customerId, String firstName, String lastName, String dateOfBirth) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
    }
    public CustomerDb() {
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

}
