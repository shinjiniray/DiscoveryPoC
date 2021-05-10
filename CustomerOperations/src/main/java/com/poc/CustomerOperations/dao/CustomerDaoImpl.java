package com.poc.CustomerOperations.dao;

import com.poc.CustomerOperations.annotations.LogAround;
import com.poc.CustomerOperations.domain.CustomerDb;
import com.poc.CustomerOperations.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

@Component
public class CustomerDaoImpl implements CustomerDao {

    @Autowired
    CustomerRepository customerRepository;

    @Transactional
    @LogAround
    public List<CustomerDb> retrieveAllCustomers() {
        return customerRepository.findAll();
    }

    @Transactional
    @LogAround
    public CustomerDb retrieveCustomerById(Integer customerId) {
        return customerRepository.findByCustomerId(customerId);
    }

    @Transactional
    @LogAround
    public CustomerDb recordCustomer(CustomerDb customer) {
        return customerRepository.save(customer);
    }

    @Transactional
    @LogAround
    public Boolean updateCustomer(Integer customerId, CustomerDb customer) {
        if(customerRepository.findByCustomerId(customerId)!=null) {
            customer.setCustomerId(customerId);
            customerRepository.save(customer);
            return true;
        } else
            return false;
    }

    @Transactional
    @LogAround
    public Boolean deleteCustomer(Integer customerId) {
        if(customerRepository.findByCustomerId(customerId)!=null) {
            customerRepository.findById(customerId).get();
            customerRepository.deleteById(customerId);
            return true;
        } else
            return false;
    }
}
