package com.gen.com.Insurance_portal.services.impls;

import com.gen.com.Insurance_portal.entites.ClaimsInfo;
import com.gen.com.Insurance_portal.repositories.ClaimsInfoRepository;
import com.gen.com.Insurance_portal.services.IClaimsInfoService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClaimsInfoService extends AbstractService<ClaimsInfo> implements IClaimsInfoService {
    private final ClaimsInfoRepository claimsInfoRepository;

    public ClaimsInfoService(ClaimsInfoRepository claimsInfoRepository) {
        super(claimsInfoRepository);
        this.claimsInfoRepository = claimsInfoRepository;
    }

    @Override
    public Optional<ClaimsInfo> findByCustomerCodeAndProductCode(String customerCode, String productCode) {
        return claimsInfoRepository.findByCustomerCodeAndProductCode(customerCode, productCode);
    }

    @Override
    public Optional<ClaimsInfo> findClaimsInfoByCustomerIdAndContractId(Long customerId, Long contractId) {
        return claimsInfoRepository.findClaimsInfoByCustomerIdAndContractId(customerId, contractId);
    }

}
