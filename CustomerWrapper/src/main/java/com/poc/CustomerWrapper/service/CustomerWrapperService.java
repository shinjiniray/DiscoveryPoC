package com.poc.CustomerWrapper.service;

import com.poc.CustomerWrapper.dto.Customer;
import com.poc.CustomerWrapper.dto.CustomerDetails;
import com.poc.CustomerWrapper.dto.CustomerList;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface CustomerWrapperService {

    public List<CustomerList> retrieveCustomers();
    public Object retrieveCustomerById(String customerId);
    public Map<String, Object> recordCustomer(Customer customer);
    public Map<String, Object> updateCustomer(String customerId, Customer customer);
    public Map<String, Object> deleteCustomer(String customerId);
    public List<CustomerDetails> retrieveCustomerDetails();
    public Object retrieveCustomerDetailsByCustomerDetailsId(String customerDetailsId);
    public Map<String, Object> recordCustomerDetails(CustomerDetails customerDetails);
    public Map<String, Object> updateCustomerDetails(String customerDetailsId, CustomerDetails customerDetails);
    public Map<String, Object> deleteCustomerDetails(String customerDetailsId);

}
