package com.poc.CustomerWrapper.controller;

import com.poc.CustomerWrapper.annotations.LogAround;
import com.poc.CustomerWrapper.dto.Customer;
import com.poc.CustomerWrapper.dto.CustomerDetails;
import com.poc.CustomerWrapper.dto.CustomerList;
import com.poc.CustomerWrapper.service.CustomerWrapperService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import java.util.List;
import java.util.Map;

/**
 * Wrapper controller class to consume Customer and CustomerDetails requests
 */
@RestController
@RequestMapping("/customermanagement")
public class CustomerWrapperController {

    @Autowired
    CustomerWrapperService customerWrapperService;

    /**
     * Retrieving all customers and customer details
     * @return List<Customer>
     */
    @GetMapping("/customers")
    @LogAround
    @Produces("application/json")
    @ApiOperation(value="Retrieve all customers and customer details")
    @ApiResponses(value={ @ApiResponse(code = 200, message = "Successful response"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 500, message = "Internal Server Error") })
    public List<CustomerList> retrieveCustomers() {
        return customerWrapperService.retrieveCustomers();
    }

    /**
     * Retreiving customer and customer details with given customer ID
     * @param customerId
     * @return Object
     */
    @GetMapping("/customers/{customerId}")
    @LogAround
    @Produces("application/json")
    @ApiOperation(value="Retrieve customer and customer details with given customer ID")
    @ApiResponses(value={ @ApiResponse(code = 200, message = "Successful response"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 500, message = "Internal Server Error") })
    public Object retrieveCustomerById(@PathVariable String customerId) {
        return customerWrapperService.retrieveCustomerById(customerId);
    }

    /**
     * Recording customer and customer details
     * @param customer
     * @return Map<String, Object>
     */
    @PostMapping("/customers")
    @LogAround
    @Produces("application/json")
    @Consumes("application/json")
    @ApiOperation(value="Record customer and customer details")
    @ApiResponses(value={ @ApiResponse(code = 200, message = "Successful response"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 500, message = "Internal Server Error") })
    public Map<String, Object> recordCustomer(@RequestBody Customer customer) {
        return customerWrapperService.recordCustomer(customer);
    }

    /**
     * Updating customer with given customer ID
     * @param customerId
     * @param customer
     * @return Map<String, Object>
     */
    @PutMapping("/customers/{customerId}")
    @LogAround
    @Produces("application/json")
    @Consumes("application/json")
    @ApiOperation(value="Update the customer with given customer ID")
    @ApiResponses(value={ @ApiResponse(code = 200, message = "Successful response"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 500, message = "Internal Server Error") })
    public Map<String, Object> updateCustomer(@PathVariable String customerId, @RequestBody Customer customer) {
        return customerWrapperService.updateCustomer(customerId, customer);
    }

    /**
     * Deleting customer and customer details with given customer ID
     * @param customerId
     * @return Map<String, Object>
     */
    @DeleteMapping("/customers/{customerId}")
    @LogAround
    @Produces("application/json")
    @ApiOperation(value="Delete customer and customer details with given customer ID")
    @ApiResponses(value={ @ApiResponse(code = 200, message = "Successful response"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 500, message = "Internal Server Error") })
    public Map<String, Object> deleteCustomerById(@PathVariable String customerId) {
        return customerWrapperService.deleteCustomer(customerId);
    }

    /**
     * Retreiving all customer details
     * @return List<CustomerDetails>
     */
    @GetMapping("/customerdetails")
    @LogAround
    @Produces("application/json")
    @ApiOperation(value="Retrieve all customer details")
    @ApiResponses(value={ @ApiResponse(code = 200, message = "Successful response"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 500, message = "Internal Server Error") })
    public List<CustomerDetails> retrieveCustomerDetails() {
        return customerWrapperService.retrieveCustomerDetails();
    }

    /**
     * Retrieving customer details with customer details id
     * @param customerDetailsId
     * @return Object
     */
    @GetMapping("/customerdetails/{customerDetailsId}")
    @LogAround
    @Produces("application/json")
    @ApiOperation(value="Retrieve customer details with given customer details ID")
    @ApiResponses(value={ @ApiResponse(code = 200, message = "Successful response"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 500, message = "Internal Server Error") })
    public Object retrieveCustomerDetailsByCustomerDetailsId(@PathVariable String customerDetailsId) {
        return customerWrapperService.retrieveCustomerDetailsByCustomerDetailsId(customerDetailsId);
    }

    /**
     * Recording customer details
     * @param customerDetails
     * @return Map<String, Object>
     */
    @PostMapping("/customerdetails")
    @LogAround
    @Produces("application/json")
    @Consumes("application/json")
    @ApiOperation(value="Record customer details")
    @ApiResponses(value={ @ApiResponse(code = 200, message = "Successful response"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 500, message = "Internal Server Error") })
    public Map<String, Object> recordCustomerDetails(@RequestBody CustomerDetails customerDetails) {
        return customerWrapperService.recordCustomerDetails(customerDetails);
    }

    /**
     * Updating customer details with given customer details ID
     * @param customerDetailsId
     * @param customerDetails
     * @return Map<String, Object>
     */
    @PutMapping("/customerdetails/{customerDetailsId}")
    @LogAround
    @Produces("application/json")
    @Consumes("application/json")
    @ApiOperation(value="Update the customer details with given customer details ID")
    @ApiResponses(value={ @ApiResponse(code = 200, message = "Successful response"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 500, message = "Internal Server Error") })
    public Map<String, Object> updateCustomerDetails(@PathVariable String customerDetailsId, @RequestBody CustomerDetails customerDetails) {
        return customerWrapperService.updateCustomerDetails(customerDetailsId, customerDetails);
    }

    /**
     * Deleting customer details with given  customer details ID
     * @param customerDetailsId
     * @return Map<String, Object>
     */
    @DeleteMapping("/customerdetails/{customerDetailsId}")
    @LogAround
    @Produces("application/json")
    @ApiOperation(value="Delete customer details with given customer details ID")
    @ApiResponses(value={ @ApiResponse(code = 200, message = "Successful response"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 500, message = "Internal Server Error") })
    public Map<String, Object> deleteCustomerDetailsById(@PathVariable String customerDetailsId) {
        return customerWrapperService.deleteCustomerDetails(customerDetailsId);
    }

}
