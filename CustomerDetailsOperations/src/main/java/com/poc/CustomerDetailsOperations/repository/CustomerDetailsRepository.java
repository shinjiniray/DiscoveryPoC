package com.poc.CustomerDetailsOperations.repository;

import com.poc.CustomerDetailsOperations.domain.CustomerDetailsDb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerDetailsRepository extends JpaRepository<CustomerDetailsDb, Integer> {

    CustomerDetailsDb findByCustomerDetailsId(Integer customerDetailsId);

    List<CustomerDetailsDb> findCustomerDetailsByCustomerId(Integer customerId);

    void deleteCustomerDetailsByCustomerId(Integer customerId);

    boolean existsByCustomerId(Integer customerId);
}
