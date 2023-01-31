package com.gen.com.Insurance_portal.services.impls;

import com.gen.com.Insurance_portal.entites.SysAdmin;
import com.gen.com.Insurance_portal.repositories.SysAdminRepository;
import com.gen.com.Insurance_portal.services.ISysAdminService;
import org.springframework.stereotype.Service;

@Service
public class SysAdminService extends AbstractService<SysAdmin> implements ISysAdminService {
    private final SysAdminRepository sysAdminRepository;

    public SysAdminService(SysAdminRepository sysAdminRepository) {
        super(sysAdminRepository);
        this.sysAdminRepository = sysAdminRepository;
    }
}
