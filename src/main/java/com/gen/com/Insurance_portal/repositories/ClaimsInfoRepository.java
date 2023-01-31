package com.gen.com.Insurance_portal.repositories;

import com.gen.com.Insurance_portal.entites.ClaimsInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClaimsInfoRepository extends JpaRepository<ClaimsInfo, Long> {
    Optional<ClaimsInfo> findByCustomerCodeAndProductCode(String customerCode, String productCode);
    Optional<ClaimsInfo> findClaimsInfoByCustomerIdAndContractId(Long customerId, Long contractId);
}
