package com.poc.CustomerDetailsOperations.controller;

import com.poc.CustomerDetailsOperations.annotations.LogAround;
import com.poc.CustomerDetailsOperations.dto.CustomerDetails;
import com.poc.CustomerDetailsOperations.service.CustomerDetailsService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import java.util.ArrayList;
import java.util.List;

/**
 * Customer Details operations controller class
 */
@RestController
@RequestMapping("/customerdetails")
public class CustomerDetailsController {

    @Autowired
    private CustomerDetailsService customerDetailsService;

    /**
     * Retrieving customer details with given customer ID
     * @param customerId
     * @return List<CustomerDetails>
     */
    @GetMapping("/customer/{customerId}")
    @LogAround
    @Produces("application/json")
    @ApiOperation(value="Retrieve customer details with given customer ID")
    @ApiResponses(value={ @ApiResponse(code = 200, message = "Successful response"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 500, message = "Internal Server Error") })
    public List<CustomerDetails> retrieveCustomerDetailsByCustomerId(@PathVariable Integer customerId) {
        return customerDetailsService.retrieveCustomerDetailsByCustomerId(customerId);
    }

    /**
     * Retrieving all customer details
     * @return List<CustomerDetails>
     */
    @GetMapping("")
    @LogAround
    @Produces("application/json")
    @ApiOperation(value="Retrieve all customer details")
    @ApiResponses(value={ @ApiResponse(code = 200, message = "Successful response"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 500, message = "Internal Server Error") })
    public List<CustomerDetails> retrieveCustomerDetails() {
        return customerDetailsService.retrieveCustomerDetails();
    }

    /**
     * Retrieving customer details with given customer details ID
     * @param customerDetailsId
     * @return
     */
    @GetMapping("/{customerDetailsId}")
    @LogAround
    @Produces("application/json")
    @ApiOperation(value="Retrieve customer details with given customer details ID")
    @ApiResponses(value={ @ApiResponse(code = 200, message = "Successful response"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 500, message = "Internal Server Error") })
    public CustomerDetails retrieveCustomerDetailsByCustomerDetailsId(@PathVariable Integer customerDetailsId) {
        return customerDetailsService.retrieveCustomerDetailsByCustomerDetailsId(customerDetailsId);
    }

    /**
     * Recording customer details from customer
     * @param customerDetails
     * @return List<CustomerDetails>
     */
    @PostMapping("/customer")
    @LogAround
    @Produces("application/json")
    @Consumes("application/json")
    @ApiOperation(value="Record customer details from customer")
    @ApiResponses(value={ @ApiResponse(code = 200, message = "Successful response"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 500, message = "Internal Server Error") })
    public List<CustomerDetails> recordAllCustomerDetails(@RequestBody List<CustomerDetails> customerDetails) {
        List<CustomerDetails> insertedCustomerDetails = customerDetailsService.recordAllCustomerDetails(customerDetails);
        return insertedCustomerDetails;
    }

    /**
     * Recording customer details
     * @param customerDetails
     * @return CustomerDetails
     */
    @PostMapping("")
    @LogAround
    @Produces("application/json")
    @Consumes("application/json")
    @ApiOperation(value="Record customer details")
    @ApiResponses(value={ @ApiResponse(code = 200, message = "Successful response"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 500, message = "Internal Server Error") })
    public CustomerDetails recordCustomerDetails(@RequestBody CustomerDetails customerDetails) {
        return customerDetailsService.recordCustomerDetails(customerDetails);
    }

    /**
     * Updating customer details with given customer details ID
     * @param customerDetailsId
     * @param customerDetails
     * @return Boolean
     */
    @PutMapping("/{customerDetailsId}")
    @LogAround
    @Produces("application/json")
    @Consumes("application/json")
    @ApiOperation(value="Update customer details with given customer details ID")
    @ApiResponses(value={ @ApiResponse(code = 200, message = "Successful response"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 500, message = "Internal Server Error") })
    public Boolean updateCustomerDetails(@PathVariable Integer customerDetailsId, @RequestBody CustomerDetails customerDetails) {
        return customerDetailsService.updateCustomerDetails(customerDetailsId, customerDetails);
    }

    /**
     * Deleting customer details with given customer ID
     * @param customerId
     * @return Boolean
     */
    @DeleteMapping("/customer/{customerId}")
    @LogAround
    @Produces("application/json")
    @ApiOperation(value="Delete customer details with given customer ID")
    @ApiResponses(value={ @ApiResponse(code = 200, message = "Successful response"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 500, message = "Internal Server Error") })
    public Boolean deleteCustomerDetailsByCustomerId(@PathVariable Integer customerId) {
        return customerDetailsService.deleteCustomerDetailsByCustomerId(customerId);
    }

    /**
     * Deleting customer details with given customer details ID
     * @param customerDetailsId
     * @return Boolean
     */
    @DeleteMapping("/{customerDetailsId}")
    @LogAround
    @Produces("application/json")
    @ApiOperation(value="Delete customer details with given customer details ID")
    @ApiResponses(value={ @ApiResponse(code = 200, message = "Successful response"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 500, message = "Internal Server Error") })
    public Boolean deleteCustomerDetailsByCustomerDetailsId(@PathVariable Integer customerDetailsId) {
        return customerDetailsService.deleteCustomerDetailsByCustomerDetailsId(customerDetailsId);
    }

}
