package com.poc.CustomerOperations.dao;

import com.poc.CustomerOperations.domain.CustomerDb;

import javax.transaction.Transactional;
import java.util.List;

public interface CustomerDao {

    @Transactional
    public List<CustomerDb> retrieveAllCustomers();
    @Transactional
    public CustomerDb retrieveCustomerById(Integer customerId);
    @Transactional
    public CustomerDb recordCustomer(CustomerDb customer);
    @Transactional
    public Boolean updateCustomer(Integer customerId, CustomerDb customer);
    @Transactional
    public Boolean deleteCustomer(Integer customerId);
}
