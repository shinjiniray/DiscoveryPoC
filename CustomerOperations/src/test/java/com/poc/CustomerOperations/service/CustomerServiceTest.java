package com.poc.CustomerOperations.service;

import com.poc.CustomerOperations.dao.CustomerDao;
import com.poc.CustomerOperations.domain.CustomerDb;
import com.poc.CustomerOperations.dto.Customer;
import com.poc.CustomerOperations.exceptionhandler.ServiceException;
import com.poc.CustomerOperations.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import javax.xml.ws.Service;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class CustomerServiceTest {

    @MockBean
    private CustomerRepository customerRepository;

    @MockBean
    private CustomerDao customerDao;

    @Autowired
    private CustomerService customerService;


    @Test
    void retrieveCustomerByIdSuccess() throws ServiceException {
        Mockito.when(customerDao.retrieveCustomerById(1)).thenReturn(new CustomerDb());
        assertThat(customerService.retrieveCustomerById(1)).isInstanceOfAny(Customer.class);
        assertNotNull(customerService.retrieveCustomerById(1));
    }

    @Test
    void retrieveCustomerByIdFailure1() throws ServiceException {
        Mockito.when(customerDao.retrieveCustomerById(1)).thenReturn(null);
        assertThrows(ServiceException.class, () -> {customerService.retrieveCustomerById(1);});
//        assertThat(customerService.retrieveCustomerById(1)).isInstanceOfAny(ServiceException.class);
    }
}