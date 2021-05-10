package com.poc.CustomerDetailsOperations.service;

import com.poc.CustomerDetailsOperations.dao.CustomerDetailsDao;
import com.poc.CustomerDetailsOperations.domain.CustomerDetailsDb;
import com.poc.CustomerDetailsOperations.dto.CustomerDetails;
import com.poc.CustomerDetailsOperations.repository.CustomerDetailsRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class CustomerDetailsServiceTest {

    @MockBean
    private CustomerDetailsRepository customerDetailsRepository;

    @MockBean
    private CustomerDetailsDao customerDetailsDao;

    @Autowired
    private CustomerDetailsServiceImpl customerDetailsService;

    @Test
    void retrieveCustomerDetailsByCustomerIdSuccess() {
        List<CustomerDetailsDb> customerDetailsDbs = new ArrayList<>();
        Mockito.when(customerDetailsDao.retrieveCustomerDetailsByCustomerId(1)).thenReturn(customerDetailsDbs);
        assertThat(customerDetailsService.retrieveCustomerDetailsByCustomerId(1)).isInstanceOf(List.class);
        assertNotNull(customerDetailsService.retrieveCustomerDetailsByCustomerId(1));
    }

}