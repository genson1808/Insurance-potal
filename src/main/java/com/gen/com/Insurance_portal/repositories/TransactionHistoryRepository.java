package com.gen.com.Insurance_portal.repositories;

import com.gen.com.Insurance_portal.entites.TransactionHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TransactionHistoryRepository extends JpaRepository<TransactionHistory, Long> {
    Optional<TransactionHistory> findByTransactionCode(String code);
    Optional<TransactionHistory> findByTransactionCodeAndCustomerCode(String transactionCode, String customerCode);
    Page<TransactionHistory> findAllByContractCode(String code, Pageable pageable);
    Page<TransactionHistory> findAllByCustomerCode(String customerCodeode, Pageable pageable);
    Page<TransactionHistory> findAllByCustomerCodeAndContractCode(String customerCode, String contractCode, Pageable pageable);
}
