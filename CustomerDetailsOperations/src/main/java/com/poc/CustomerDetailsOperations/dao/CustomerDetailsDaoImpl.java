package com.poc.CustomerDetailsOperations.dao;

import com.poc.CustomerDetailsOperations.annotations.LogAround;
import com.poc.CustomerDetailsOperations.domain.CustomerDetailsDb;
import com.poc.CustomerDetailsOperations.repository.CustomerDetailsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

@Component
public class CustomerDetailsDaoImpl implements CustomerDetailsDao {

    public final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    CustomerDetailsRepository customerDetailsRepository;

    @Transactional
    @LogAround
    public List<CustomerDetailsDb> retrieveCustomerDetailsByCustomerId(Integer customerId) {
        return customerDetailsRepository.findCustomerDetailsByCustomerId(customerId);
    }

    @Transactional
    @LogAround
    public List<CustomerDetailsDb> retrieveCustomerDetails() {
        return customerDetailsRepository.findAll();
    }

    @Transactional
    @LogAround
    public CustomerDetailsDb retrieveCustomerDetailsByCustomerDetailsId(Integer customerDetailsId) {
        return customerDetailsRepository.findByCustomerDetailsId(customerDetailsId);
    }

    @Transactional
    @LogAround
    public List<CustomerDetailsDb> recordAllCustomerDetails(List<CustomerDetailsDb> customerDetails) {
        return customerDetailsRepository.saveAll(customerDetails);
    }

    @Transactional
    @LogAround
    public CustomerDetailsDb recordCustomerDetails(CustomerDetailsDb customerDetails) {
        return customerDetailsRepository.save(customerDetails);
    }

    @Transactional
    @LogAround
    public boolean updateCustomerDetails(Integer customerDetailsId, CustomerDetailsDb customerDetails) {
        if(customerDetailsRepository.findByCustomerDetailsId(customerDetailsId)!=null) {
            customerDetails.setCustomerDetailsId(customerDetailsId);
            customerDetailsRepository.save(customerDetails);
            return true;
        } else
            return false;
    }

    @Transactional
    @LogAround
    public boolean deleteCustomerDetailsByCustomerId(Integer customerId) {
        customerDetailsRepository.deleteCustomerDetailsByCustomerId(customerId);
        return true;
    }

    @Transactional
    @LogAround
    public boolean deleteCustomerDetailsByCustomerDetailsId(Integer customerDetailsId) {
        if(customerDetailsRepository.findByCustomerDetailsId(customerDetailsId)!=null) {
            customerDetailsRepository.findById(customerDetailsId).get();
            customerDetailsRepository.deleteById(customerDetailsId);
            return true;
        } else
            return false;
    }
}
