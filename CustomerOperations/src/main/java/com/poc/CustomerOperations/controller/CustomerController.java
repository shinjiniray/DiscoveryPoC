package com.poc.CustomerOperations.controller;

import com.poc.CustomerOperations.annotations.LogAround;
import com.poc.CustomerOperations.dto.Customer;
import com.poc.CustomerOperations.exceptionhandler.ServiceException;
import com.poc.CustomerOperations.service.CustomerService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import java.util.List;

/**
 * Customer operations controller class
 */
@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    /**
     * Retrieving all customers
     * @return List<Customer>
     */
    @GetMapping("")
    @LogAround
    @Produces("application/json")
    @ApiOperation(value="Retrieve all customers")
    @ApiResponses(value={ @ApiResponse(code = 200, message = "Successful response"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 500, message = "Internal Server Error") })
    public List<Customer> retrieveCustomers() {
        return customerService.retrieveAllCustomers();
    }

    /**
     * Retrieving customer with given customer ID
     * @param customerId
     * @return Customer
     */
    @GetMapping("/{customerId}")
    @LogAround
    @Produces("application/json")
    @ApiOperation(value="Retrieve customer with given ID")
    @ApiResponses(value={ @ApiResponse(code = 200, message = "Successful response"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 500, message = "Internal Server Error") })
    public Customer retrieveCustomerById(@PathVariable Integer customerId) throws ServiceException {
        return customerService.retrieveCustomerById(customerId);
    }

    /**
     * Recording customer
     * @param customer
     * @return Customer
     */
    @PostMapping("")
    @LogAround
    @Produces("application/json")
    @Consumes("application/json")
    @ApiOperation(value="Record customer")
    @ApiResponses(value={ @ApiResponse(code = 200, message = "Successful response"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 500, message = "Internal Server Error") })
    public Customer recordCustomer(@RequestBody Customer customer) {
        Customer insertedCustomer=customerService.recordCustomer(customer);
        return insertedCustomer;
    }

    /**
     * Updating customer with given customer ID
     * @param customerId
     * @param customer
     * @return Boolean
     */
    @PutMapping("{customerId}")
    @LogAround
    @Produces("application/json")
    @Consumes("application/json")
    @ApiOperation(value="Update customer with given customer ID")
    @ApiResponses(value={ @ApiResponse(code = 200, message = "Successful response"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 500, message = "Internal Server Error") })
    public Boolean updateCustomer(@PathVariable Integer customerId, @RequestBody Customer customer) {
        return customerService.updateCustomer(customerId, customer);
    }

    /**
     * Deleting customer with given customer ID
     * @param customerId
     * @return
     */
    @DeleteMapping("{customerId}")
    @LogAround
    @Produces("application/json")
    @ApiOperation(value="Delete customer with given customer ID")
    @ApiResponses(value={ @ApiResponse(code = 200, message = "Successful response"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 500, message = "Internal Server Error") })
    public Boolean deleteCustomerById(@PathVariable Integer customerId) {
        return customerService.deleteCustomer(customerId);
    }

}
