package com.poc.CustomerWrapper.service;

import com.poc.CustomerWrapper.annotations.LogAround;
import com.poc.CustomerWrapper.constants.AppConstants;
import com.poc.CustomerWrapper.dto.Customer;
import com.poc.CustomerWrapper.dto.CustomerDetails;
import com.poc.CustomerWrapper.dto.CustomerExceptionResponse;
import com.poc.CustomerWrapper.dto.CustomerList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.*;

/**
 * Wrapper service class to call Customer and CustomerDetails Operations APIs
 */
@Component
public class CustomerWrapperServiceImpl implements CustomerWrapperService {

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Autowired
    RestTemplate restTemplate;

    @Value("${customer.base.url}")
    private String customerBaseUrl;

    @Value("${customer.url.id}")
    private String customerUrlId;

    @Value("${customerdetails.base.url}")
    private String customerDetailsBaseUrl;

    @Value("${customerdetails.url.id}")
    private String customerDetailsUrlId;

    @Value("${customerdetails.extended.url}")
    private String customerDetailsExtendedUrl;

    @Value("${customerdetails.extended.url.id}")
    private String customerDetailsExtendedUrlId;

    public final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    /**
     * Retrieving customers
     * @return List<Customer>
     */
    @LogAround
    public List<CustomerList> retrieveCustomers() {
        LOGGER.info("Retrieving all customers");
        try {
            ResponseEntity<CustomerList[]> customerResponseEntity = restTemplate.getForEntity(customerBaseUrl, CustomerList[].class);
            List<CustomerList> customers = Arrays.asList(customerResponseEntity.getBody());
            for(int i=0; i<customers.size(); i++) {
                ResponseEntity<CustomerDetails[]> customerDetailsResponseEntity = restTemplate.getForEntity(customerDetailsBaseUrl+customerDetailsExtendedUrlId.replace("{id}", String.valueOf(customers.get(i).getCustomerId())), CustomerDetails[].class);
                List<CustomerDetails> customerDetails = Arrays.asList(customerDetailsResponseEntity.getBody());
                customers.get(i).setCustomerDetails(customerDetails);
            }
            return customers;
        } catch (RestClientException e) {
            LOGGER.error("Exception");
            return Collections.emptyList();
        }
    }

    /**
     * Retrieving customer by customer ID
     * @param customerId
     * @return Object
     */
    @LogAround
    public Object retrieveCustomerById(String customerId) {
        LOGGER.info("Retrieving customer with customer ID: "+customerId);
        try {
            ResponseEntity<Customer> customerResponseEntity = restTemplate.getForEntity(customerBaseUrl+customerUrlId.replace("{id}", customerId), Customer.class);
            Customer customer = customerResponseEntity.getBody();
            if(customer.getExceptionResponse()!=null) {
                CustomerExceptionResponse customerExceptionResponse = new CustomerExceptionResponse();
                customerExceptionResponse.setExceptionResponse(customer.getExceptionResponse());
                return customerExceptionResponse;
            } else {
                ResponseEntity<CustomerDetails[]> customerDetailsResponseEntity = restTemplate.getForEntity(customerDetailsBaseUrl+customerDetailsExtendedUrlId.replace("{id}", customerId), CustomerDetails[].class);
                List<CustomerDetails> customerDetails = Arrays.asList(customerDetailsResponseEntity.getBody());
                customer.setCustomerDetails(customerDetails);
                return customer;
            }
        } catch (RestClientException e) {
            LOGGER.error("Exception");
            e.printStackTrace();
            return errorMessage(AppConstants.ERROR_TYPE, AppConstants.GET_ERROR_CODE, AppConstants.GETCUSTOMERBYID_ERROR_MESSAGE);
        }
    }

    /**
     * Retrieving Customer details
     * @return List<CustomerDetails>
     */
    @LogAround
    public List<CustomerDetails> retrieveCustomerDetails() {
        try {
            LOGGER.info("Retrieving all customer details");
            ResponseEntity<CustomerDetails[]> customerDetailsResponseEntity = restTemplate.getForEntity(customerDetailsBaseUrl, CustomerDetails[].class);
            List customerDetails = Arrays.asList(customerDetailsResponseEntity.getBody());
            return customerDetails;
        } catch (RestClientException e) {
            LOGGER.error("Exception");
            return Collections.emptyList();
        }
    }

    /**
     * Retrieving CustomerDetails By CustomerDetails ID
     * @param customerDetailsId
     * @return Object
     */
    @LogAround
    public Object retrieveCustomerDetailsByCustomerDetailsId(String customerDetailsId) {
        try{
            LOGGER.info("Retrieving customer details with customer details ID: "+customerDetailsId);
            CustomerDetails customerDetails = restTemplate.getForObject(customerDetailsBaseUrl+customerDetailsUrlId.replace("{id}", customerDetailsId), CustomerDetails.class);
            if(customerDetails!=null)
                return customerDetails;
            else
                return errorMessage(AppConstants.ERROR_TYPE, AppConstants.GET_ERROR_CODE, AppConstants.GETCUSTOMERDETAILSBYCUSTOMERDETAILSID_ERROR_MESSAGE);
        } catch (RestClientException e) {
            LOGGER.error("Exception");
            return errorMessage(AppConstants.ERROR_TYPE, AppConstants.GET_ERROR_CODE, AppConstants.GETCUSTOMERDETAILSBYCUSTOMERDETAILSID_ERROR_MESSAGE);
        }
    }

    /**
     * Recording new Customer
     * @param customer
     * @return Map<String, Object>
     */
    @LogAround
    public Map<String, Object> recordCustomer(Customer customer) {
        try {
            LOGGER.info("Recording customer and customer details");
            Customer insertedCustomer = restTemplate.postForObject(customerBaseUrl, customer, Customer.class);
            if(insertedCustomer!=null) {
                List<CustomerDetails> customerDetails = customer.getCustomerDetails();
                for(int i=0; i<customerDetails.size(); i++)
                    customerDetails.get(i).setCustomerId(insertedCustomer.getCustomerId());
                List<CustomerDetails> insertedCustomerDetails = restTemplate.postForObject(customerDetailsBaseUrl+customerDetailsExtendedUrl, customerDetails, List.class);
                if(insertedCustomerDetails!=null)
                    return successMessage(insertedCustomer.getCustomerId(), AppConstants.INSERTCUSTOMER_SUCCESS_MESSAGE);
                else
                    return errorMessage(AppConstants.ERROR_TYPE, AppConstants.INSERT_ERROR_CODE, AppConstants.INSERTCUSTOMER_CUSTOMERDETAILS_ERROR_MESSAGE);
            } else {
                LOGGER.error("Customer is null");
                return errorMessage(AppConstants.ERROR_TYPE, AppConstants.INSERT_ERROR_CODE, AppConstants.INSERTCUSTOMER_CUSTOMER_ERROR_MESSAGE);
            }

        } catch (RestClientException e) {
            LOGGER.error("Exception");
            return errorMessage(AppConstants.ERROR_TYPE, AppConstants.INSERT_ERROR_CODE, AppConstants.INSERTCUSTOMER_CUSTOMER_ERROR_MESSAGE);
        }
    }

    /**
     *  Recording CustomerDetails with given Customer object
     * @param customerDetails
     * @return Map<String, Object>
     */
    @LogAround
    public Map<String, Object> recordCustomerDetails(CustomerDetails customerDetails) {
        try {
            LOGGER.info("Recording customer details");
            Customer customer = restTemplate.getForObject(customerBaseUrl+customerUrlId.replace("{id}", String.valueOf(customerDetails.getCustomerId())), Customer.class);
            if(customer!=null) {
                CustomerDetails insertedCustomerDetails = restTemplate.postForObject(customerDetailsBaseUrl, customerDetails, CustomerDetails.class);
                if (insertedCustomerDetails!=null)
                    return successMessage(insertedCustomerDetails.getCustomerDetailsId(), AppConstants.INSERTCUSTOMERDETAILS_SUCCESS_MESSAGE);
                else
                    return errorMessage(AppConstants.ERROR_TYPE, AppConstants.INSERT_ERROR_CODE, AppConstants.INSERTCUSTOMERDETAILS_ERROR_MESSAGE);
            } else{
                LOGGER.error("Customer does not exist");
                return errorMessage(AppConstants.ERROR_TYPE, AppConstants.INSERT_ERROR_CODE, AppConstants.INSERTCUSTOMERDETAILS_ERROR_MESSAGE);
            }
        } catch (RestClientException e) {
            LOGGER.error("Exception");
            return errorMessage(AppConstants.ERROR_TYPE, AppConstants.INSERT_ERROR_CODE, AppConstants.INSERTCUSTOMERDETAILS_ERROR_MESSAGE);
        }
    }

    /**
     * Updating Customer with given customer ID and Customer object
     * @param customerId
     * @param customer
     * @return Map<String, Object>
     */
    @LogAround
    public Map<String, Object> updateCustomer(String customerId, Customer customer) {
        try {
            LOGGER.info("Updating customer");
            HttpEntity<Customer> customerEntity = new HttpEntity(customer);
            ResponseEntity<Boolean> customerResponse = restTemplate.exchange(customerBaseUrl+customerUrlId.replace("{id}", customerId), HttpMethod.PUT, customerEntity, Boolean.class);
            if(customerResponse.getBody())
                return successMessage(Integer.valueOf(customerId), AppConstants.UPDATECUSTOMER_SUCCESS_MESSAGE);
            else {
                LOGGER.error("Error in updating customer");
                return errorMessage(AppConstants.ERROR_TYPE, AppConstants.UPDATE_ERROR_CODE, AppConstants.UPDATECUSTOMER_ERROR_MESSAGE);
            }
        } catch (RestClientException e) {
            LOGGER.error("Exception");
            return errorMessage(AppConstants.ERROR_TYPE, AppConstants.UPDATE_ERROR_CODE, AppConstants.UPDATECUSTOMER_ERROR_MESSAGE);
        }
    }

    /**
     * Updating customerDetails with given customer ID and customer detail object
     * @param customerDetailsId
     * @param customerDetails
     * @return Map<String, Object>
     */
    @LogAround
    public Map<String, Object> updateCustomerDetails(String customerDetailsId, CustomerDetails customerDetails) {
        try {
            LOGGER.info("Updating customer details");
            Customer customer = restTemplate.getForObject(customerBaseUrl+customerUrlId.replace("{id}", String.valueOf(customerDetails.getCustomerId())), Customer.class);
            if(customer!=null) {
                HttpEntity<CustomerDetails> customerDetailsEntity = new HttpEntity(customerDetails);
                ResponseEntity<Boolean> customerDetailsResponse = restTemplate.exchange(customerDetailsBaseUrl+customerDetailsUrlId.replace("{id}", customerDetailsId), HttpMethod.PUT, customerDetailsEntity, Boolean.class);
                if (customerDetailsResponse.getBody())
                    return successMessage(Integer.valueOf(customerDetailsId), AppConstants.UPDATECUSTOMERDETAILS_SUCCESS_MESSAGE);
                else {
                    LOGGER.error("Error in updating customer details");
                    return errorMessage(AppConstants.ERROR_TYPE, AppConstants.UPDATE_ERROR_CODE, AppConstants.UPDATECUSTOMERDETAILS_ERROR_MESSAGE);
                }
            } else {
                LOGGER.error("Customer does not exist");
                return errorMessage(AppConstants.ERROR_TYPE, AppConstants.UPDATE_ERROR_CODE, AppConstants.UPDATECUSTOMERDETAILS_ERROR_MESSAGE);
            }
        } catch (RestClientException e) {
            LOGGER.error("Exception");
            return errorMessage(AppConstants.ERROR_TYPE, AppConstants.UPDATE_ERROR_CODE, AppConstants.UPDATECUSTOMERDETAILS_ERROR_MESSAGE);
        }
    }

    /**
     * Delete Customer with given customer ID
     * @param customerId
     * @return Map<String, Object>
     */
    @LogAround
    public Map<String, Object> deleteCustomer(String customerId) {
        try {
            LOGGER.info("Deleting customer");
            ResponseEntity<Boolean> customerResponse = restTemplate.exchange(customerBaseUrl+customerUrlId.replace("{id}", customerId), HttpMethod.DELETE, null, Boolean.class);
            if(customerResponse.getBody()) {
                ResponseEntity<Boolean> customerDetialsResponse = restTemplate.exchange(customerDetailsBaseUrl+customerDetailsExtendedUrlId.replace("{id}", customerId), HttpMethod.DELETE, null, Boolean.class);
                if(customerDetialsResponse.getBody())
                    return successMessage(Integer.valueOf(customerId), AppConstants.DELETECUSTOMER_SUCCESS_MESSAGE);
                else {
                    LOGGER.info("Error in deleting customer details");
                    return errorMessage(AppConstants.ERROR_TYPE, AppConstants.UPDATE_ERROR_CODE, AppConstants.DELETECUSTOMER_ERROR_MESSAGE);
                }
            } else {
                LOGGER.error("Error in deleting customer");
                return errorMessage(AppConstants.ERROR_TYPE, AppConstants.UPDATE_ERROR_CODE, AppConstants.DELETECUSTOMER_ERROR_MESSAGE);
            }
        } catch (RestClientException e) {
            e.printStackTrace();
            LOGGER.error("Exception");
            return errorMessage(AppConstants.ERROR_TYPE, AppConstants.UPDATE_ERROR_CODE, AppConstants.DELETECUSTOMER_ERROR_MESSAGE);
        }
    }

    /**
     * DeleteCustomerDetails with given customerdetails ID
     * @param customerDetailsId
     * @return Map<String, Object>
     */
    @LogAround
    public Map<String, Object> deleteCustomerDetails(String customerDetailsId) {
        try {
            LOGGER.info("Deleting customer details");
            ResponseEntity<Boolean> customerDetailsResponse = restTemplate.exchange(customerDetailsBaseUrl+customerDetailsUrlId.replace("{id}", customerDetailsId), HttpMethod.DELETE, null, Boolean.class);
            if(customerDetailsResponse.getBody())
                return successMessage(Integer.valueOf(customerDetailsId), AppConstants.DELETECUSTOMERDETAILS_SUCCESS_MESSAGE);
            else {
                LOGGER.error("Error in deleting customer details");
                return errorMessage(AppConstants.ERROR_TYPE, AppConstants.UPDATE_ERROR_CODE, AppConstants.DELETECUSTOMERDETAILS_ERROR_MESSAGE);
            }
        } catch (RestClientException e) {
            LOGGER.error("Exception");
            return errorMessage(AppConstants.ERROR_TYPE, AppConstants.UPDATE_ERROR_CODE, AppConstants.DELETECUSTOMERDETAILS_ERROR_MESSAGE);
        }
    }

    /**
     * Returns success message to be displayed
     * @param id
     * @param message
     * @return Map<String, Object>
     */
    public Map<String, Object> successMessage(Integer id, String message) {
        Map<String, Object> result = new HashMap<>();
        result.put("ID", id);
        result.put("message", message);
        return result;
    }

    /**
     * Returns error message to be displayed
     * @param type
     * @param code
     * @param message
     * @return Map<String, Object>
     */
    public Map<String, Object> errorMessage(String type, String code, String message) {
        Map<String, Object> result = new HashMap<>();
        result.put("type", type);
        result.put("code", code);
        result.put("message", message);
        return result;
    }
}