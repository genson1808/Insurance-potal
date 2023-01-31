package com.gen.com.Insurance_portal.services;

import com.gen.com.Insurance_portal.entites.ClaimsInfo;

import java.util.Optional;

public interface IClaimsInfoService extends IAbstractService<ClaimsInfo> {
    Optional<ClaimsInfo> findByCustomerCodeAndProductCode(String customerCode, String productCode);
    Optional<ClaimsInfo> findClaimsInfoByCustomerIdAndContractId(Long customerId, Long contractId);
}
