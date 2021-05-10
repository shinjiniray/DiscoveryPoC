package com.poc.CustomerWrapper.service;

import com.poc.CustomerWrapper.dto.Customer;
import com.poc.CustomerWrapper.dto.CustomerDetails;
import com.poc.CustomerWrapper.dto.CustomerExceptionResponse;
import com.poc.CustomerWrapper.dto.ExceptionResponse;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class CustomerWrapperServiceTest {

    @MockBean
    RestTemplate restTemplate;

    @Autowired
    CustomerWrapperService customerWrapperService;

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


    @Test
    void retrieveCustomerByIdSuccess() {
        Mockito.when(restTemplate.getForEntity(customerBaseUrl+customerUrlId.replace("{id}", "1"), Customer.class)).thenReturn(new ResponseEntity<Customer>(new Customer(), HttpStatus.OK));
        Mockito.when(restTemplate.getForEntity(customerDetailsBaseUrl+customerDetailsExtendedUrlId.replace("{id}", "1"), CustomerDetails[].class)).thenReturn(new ResponseEntity<CustomerDetails[]>(new CustomerDetails[]{}, HttpStatus.OK));
        assertThat(customerWrapperService.retrieveCustomerById("1")).isInstanceOfAny(Customer.class);
        assertNotNull(customerWrapperService.retrieveCustomerById("1"));
    }

    @Test
    void retrieveCustomerByIdFailure1() {
        Mockito.when(restTemplate.getForEntity(customerBaseUrl+customerUrlId.replace("{id}", "1"), Customer.class)).thenThrow(new RestClientException("Rest Client Exception"));
        assertThat(customerWrapperService.retrieveCustomerById("1")).isInstanceOfAny(Map.class);
        assertNotNull(customerWrapperService.retrieveCustomerById("1"));
    }

    @Test
    void retrieveCustomerByIdFailure2() {
        Customer customer = new Customer();
        List<ExceptionResponse> exceptionResponseList = new ArrayList<>();
        customer.setExceptionResponse(exceptionResponseList);
        Mockito.when(restTemplate.getForEntity(customerBaseUrl+customerUrlId.replace("{id}", "1"), Customer.class)).thenReturn(new ResponseEntity<>(customer, HttpStatus.OK));
        assertThat(customerWrapperService.retrieveCustomerById("1")).isInstanceOfAny(CustomerExceptionResponse.class);
        assertNotNull(customerWrapperService.retrieveCustomerById("1"));
    }
}