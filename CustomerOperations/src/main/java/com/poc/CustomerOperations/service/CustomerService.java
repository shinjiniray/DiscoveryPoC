package com.poc.CustomerOperations.service;

import com.poc.CustomerOperations.dto.Customer;
import com.poc.CustomerOperations.exceptionhandler.ServiceException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public interface CustomerService {

    public List<Customer> retrieveAllCustomers();
    public Customer retrieveCustomerById(Integer customerId) throws ServiceException;
    public Customer recordCustomer(Customer customer);
    public Boolean updateCustomer(Integer customerId, Customer customer);
    public Boolean deleteCustomer(Integer customerId);
}
