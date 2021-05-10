package com.poc.CustomerDetailsOperations.dao;

import com.poc.CustomerDetailsOperations.domain.CustomerDetailsDb;

import javax.transaction.Transactional;
import java.util.List;

public interface CustomerDetailsDao {

    @Transactional
    public List<CustomerDetailsDb> retrieveCustomerDetailsByCustomerId(Integer customerId);
    @Transactional
    public List<CustomerDetailsDb> retrieveCustomerDetails();
    @Transactional
    public CustomerDetailsDb retrieveCustomerDetailsByCustomerDetailsId(Integer customerDetailsId);
    @Transactional
    public List<CustomerDetailsDb> recordAllCustomerDetails(List<CustomerDetailsDb> customerDetails);
    @Transactional
    public CustomerDetailsDb recordCustomerDetails(CustomerDetailsDb customerDetails);
    @Transactional
    public boolean updateCustomerDetails(Integer customerDetailsId, CustomerDetailsDb customerDetails);
    @Transactional
    public boolean deleteCustomerDetailsByCustomerId(Integer customerId);
    @Transactional
    public boolean deleteCustomerDetailsByCustomerDetailsId(Integer customerDetailsId);
}
