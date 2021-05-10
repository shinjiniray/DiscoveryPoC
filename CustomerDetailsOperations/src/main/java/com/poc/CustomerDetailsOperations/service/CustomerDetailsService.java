package com.poc.CustomerDetailsOperations.service;

import com.poc.CustomerDetailsOperations.dto.CustomerDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CustomerDetailsService {

    public List<CustomerDetails> retrieveCustomerDetailsByCustomerId(Integer customerId);
    public List<CustomerDetails> retrieveCustomerDetails();
    public CustomerDetails retrieveCustomerDetailsByCustomerDetailsId(Integer customerDetailsId);
    public List<CustomerDetails> recordAllCustomerDetails(List<CustomerDetails> customerDetails);
    public CustomerDetails recordCustomerDetails(CustomerDetails customerDetails);
    public boolean updateCustomerDetails(Integer customerDetailsId, CustomerDetails customerDetails);
    public boolean deleteCustomerDetailsByCustomerId(Integer customerId);
    public boolean deleteCustomerDetailsByCustomerDetailsId(Integer customerDetailsId);

}
