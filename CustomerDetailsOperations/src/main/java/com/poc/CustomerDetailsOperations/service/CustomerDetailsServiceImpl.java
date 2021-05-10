package com.poc.CustomerDetailsOperations.service;

import com.poc.CustomerDetailsOperations.annotations.LogAround;
import com.poc.CustomerDetailsOperations.dao.CustomerDetailsDao;
import com.poc.CustomerDetailsOperations.domain.CustomerDetailsDb;
import com.poc.CustomerDetailsOperations.dto.CustomerDetails;
import com.poc.CustomerDetailsOperations.repository.CustomerDetailsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.poc.CustomerDetailsOperations.common.CommonUtils;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Customer Details operations service class
 */
@Component
public class CustomerDetailsServiceImpl implements CustomerDetailsService {

    public final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    CustomerDetailsDao customerDetailsDao;

    /**
     /**
     * Retreiving CustomerDetails with given customer ID
     * @param customerId
     * @return List<CustomerDetails>
     */
    @Transactional
    @LogAround
    public List<CustomerDetails> retrieveCustomerDetailsByCustomerId(Integer customerId) {
        LOGGER.info("Retrieving customer details with customer ID: "+customerId);
        List<CustomerDetails> customerDetails = new ArrayList<>();
        List<CustomerDetailsDb> customerDetailsDb = customerDetailsDao.retrieveCustomerDetailsByCustomerId(customerId);
        for(int i=0; i<customerDetailsDb.size(); i++) {
            try {
                customerDetails.add(CommonUtils.transformObject(customerDetailsDb.get(i), CustomerDetails.class));
            } catch (Exception e) {
                return Collections.emptyList();
            }
        }
        return customerDetails;
    }

    /**
     * Retreiving CustomerDetails
     * @return List<CustomerDetails>
     */
    @Transactional
    @LogAround
    public List<CustomerDetails> retrieveCustomerDetails() {
        LOGGER.info("Retrieving all customer details");
        List<CustomerDetails> customerDetails = new ArrayList<>();
        List<CustomerDetailsDb> customerDetailsDb = customerDetailsDao.retrieveCustomerDetails();
        for(int i=0; i<customerDetailsDb.size(); i++) {
            try {
                customerDetails.add(CommonUtils.transformObject(customerDetailsDb.get(i), CustomerDetails.class));
            } catch (Exception e) {
                return Collections.emptyList();
            }
        }
        return customerDetails;
    }

    /**
     * Retreiving CustomerDetails with given Customer details ID
     * @param customerDetailsId
     * @return CustomerDetails
     */
    @LogAround
    public CustomerDetails retrieveCustomerDetailsByCustomerDetailsId(Integer customerDetailsId) {
        LOGGER.info("Retrieving customer details with customer details ID: "+customerDetailsId);
        try {
            return CommonUtils.transformObject(customerDetailsDao.retrieveCustomerDetailsByCustomerDetailsId(customerDetailsId), CustomerDetails.class);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Recording all customer details from customer
     * @param customerDetails
     * @return List<CustomerDetails>
     */
    @Transactional
    @LogAround
    public List<CustomerDetails> recordAllCustomerDetails(List<CustomerDetails> customerDetails) {
        LOGGER.info("Recording list of customer details");
        try {
            List<CustomerDetailsDb> customerDetailsDb = new ArrayList<>();
            for(int i=0; i<customerDetails.size(); i++)
                customerDetailsDb.add(CommonUtils.transformObject(customerDetails.get(i), CustomerDetailsDb.class));
            customerDetailsDb = customerDetailsDao.recordAllCustomerDetails(customerDetailsDb);
            customerDetails.clear();
            for(int i=0; i<customerDetailsDb.size(); i++)
                customerDetails.add(CommonUtils.transformObject(customerDetailsDb.get(i), CustomerDetails.class));
            return customerDetails;
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }

    /**
     * Recording  new Customer detail
     * @param customerDetails
     * @return CustomerDetails
     */
    @Transactional
    @LogAround
    public CustomerDetails recordCustomerDetails(CustomerDetails customerDetails) {
        LOGGER.info("Recording customer details");
        try {
            return CommonUtils.transformObject(customerDetailsDao.recordCustomerDetails(CommonUtils.transformObject(customerDetails, CustomerDetailsDb.class)), CustomerDetails.class);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Updating Customer details with given customer ID and Customer details object
     * @param customerDetailsId
     * @param customerDetails
     * @return boolean
     */
    @Transactional
    @LogAround
    public boolean updateCustomerDetails(Integer customerDetailsId, CustomerDetails customerDetails) {
        LOGGER.info("Updating customer details with customer details ID: "+customerDetailsId);
        try {
            return customerDetailsDao.updateCustomerDetails(customerDetailsId, CommonUtils.transformObject(customerDetails, CustomerDetailsDb.class));
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Deleting CustomerDetails with given customer ID
     * @param customerId
     * @return boolean
     */
    @Transactional
    @LogAround
    public boolean deleteCustomerDetailsByCustomerId(Integer customerId) {
        LOGGER.info("Deleting CustomerDetails with customer ID: "+customerId);
        return customerDetailsDao.deleteCustomerDetailsByCustomerId(customerId);
    }

    /**
     * Deleting CustomerDetails with given customerdetails ID
     * @param customerDetailsId
     * @return boolean
     */
    @Transactional
    @LogAround
    public boolean deleteCustomerDetailsByCustomerDetailsId(Integer customerDetailsId) {
        LOGGER.info("Deleting customer details with customer details ID: "+customerDetailsId);
        return customerDetailsDao.deleteCustomerDetailsByCustomerDetailsId(customerDetailsId);
    }

}
