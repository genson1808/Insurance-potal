package com.gen.com.Insurance_portal.repositories;

import com.gen.com.Insurance_portal.entites.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Page<Customer> findAllByCustomerCode(String code, Pageable pageable);

}
