package com.gen.com.Insurance_portal.services.impls;

import com.gen.com.Insurance_portal.entites.ClaimsConfigClient;
import com.gen.com.Insurance_portal.repositories.ClaimsConfigClientRepository;
import com.gen.com.Insurance_portal.services.IClaimsConfigClientService;
import org.springframework.stereotype.Service;

@Service
public class ClaimsConfigClientService extends AbstractService<ClaimsConfigClient> implements IClaimsConfigClientService {
    private final ClaimsConfigClientRepository claimsConfigClientRepository;

    public ClaimsConfigClientService(ClaimsConfigClientRepository claimsConfigClientRepository) {
        super(claimsConfigClientRepository);
        this.claimsConfigClientRepository = claimsConfigClientRepository;
    }
}
