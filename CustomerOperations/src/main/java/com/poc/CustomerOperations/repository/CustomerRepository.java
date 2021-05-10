package com.poc.CustomerOperations.repository;

import com.poc.CustomerOperations.domain.CustomerDb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerDb, Integer> {

    CustomerDb findByCustomerId(Integer customerId);
}
