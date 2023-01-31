package com.gen.com.Insurance_portal.repositories;

import com.gen.com.Insurance_portal.common.enums.ContractStatus;
import com.gen.com.Insurance_portal.entites.Contract;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContractRepository extends JpaRepository<Contract, Long> {
    Boolean existsByCode(String code);
    Page<Contract> findAllByCode(String code, Pageable pageable);
    Page<Contract> findAllByCodeAndCustomerCode(String code, String customerCode, Pageable pageable);

    Page<Contract> findAllByCustomerCode(String customerCode, Pageable pageable);

    Page<Contract> findAllByStatus(ContractStatus status, Pageable pageable);
    Page<Contract> findAllByStatusAndCustomerCode(ContractStatus status, String customerCode, Pageable pageable);

    Optional<Contract> findByCode(String code);
}
