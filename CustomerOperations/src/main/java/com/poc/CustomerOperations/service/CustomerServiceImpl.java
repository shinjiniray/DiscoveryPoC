package com.poc.CustomerOperations.service;

import com.poc.CustomerOperations.exceptionhandler.ServiceException;
import com.poc.CustomerOperations.annotations.LogAround;
import com.poc.CustomerOperations.common.CommonUtils;
import com.poc.CustomerOperations.dao.CustomerDao;
import com.poc.CustomerOperations.domain.CustomerDb;
import com.poc.CustomerOperations.dto.Customer;
import com.poc.CustomerOperations.exceptionhandler.ServiceException;
import com.poc.CustomerOperations.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Customer operations service class
 */
@Component
public class CustomerServiceImpl implements CustomerService {

    public final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    CustomerDao customerDao;

    /**
     * Retrieving All customers
     * @return List<Customer>
     */
    @Transactional
    @LogAround
    public List<Customer> retrieveAllCustomers() {
        LOGGER.info("Retrieving all customers");
        List<Customer> customers = new ArrayList<>();
        List<CustomerDb> customersDb = customerDao.retrieveAllCustomers();
        for(int i=0; i<customersDb.size();i++) {
            try {
                customers.add(CommonUtils.transformObject(customersDb.get(i), Customer.class));
            } catch (Exception e) {
                return Collections.emptyList();
            }
        }
        return customers;
    }

    /**
     * Retrieving customer by ID
     * @param customerId
     * @return Customer
     */
    @LogAround
    public Customer retrieveCustomerById(Integer customerId) throws ServiceException {
        LOGGER.info("Retrieving customer with ID: "+customerId);
        try {
                Customer customer = CommonUtils.transformObject(customerDao.retrieveCustomerById(customerId), Customer.class);
            if(customer!=null)
                return customer;
            else
                throw new ServiceException("Error", "ERR_0011", "Data not found");
        } catch (Exception e) {
            throw new ServiceException("Error", "ERR_0011", "Error in retrieving customer");
        }
    }

    /**
     * Recording new Customer
     * @param customer
     * @return Customer
     */
    @Transactional
    @LogAround
    public Customer recordCustomer(Customer customer) {
        LOGGER.info("Recording customer");
        try {
            return CommonUtils.transformObject(customerDao.recordCustomer(CommonUtils.transformObject(customer, CustomerDb.class)), Customer.class);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Updating Customer with given customer id and customer object
     * @param customerId
     * @param customer
     * @return Boolean
     */
    @Transactional
    @LogAround
    public Boolean updateCustomer(Integer customerId, Customer customer) {
        LOGGER.info("Updating customer with customer ID: "+customerId);
        try {
            return customerDao.updateCustomer(customerId, CommonUtils.transformObject(customer, CustomerDb.class));
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Deleting Customer with given customer id
     * @param customerId
     * @return Boolean
     */
    @Transactional
    @LogAround
    public Boolean deleteCustomer(Integer customerId) {
        LOGGER.info("Deleting customer with customer ID: "+customerId);
        return customerDao.deleteCustomer(customerId);
    }

}
