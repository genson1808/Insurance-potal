package com.gen.com.Insurance_portal.services.impls;

import com.gen.com.Insurance_portal.entites.AuthoritiesGroup;
import com.gen.com.Insurance_portal.repositories.AuthoritiesGroupRepository;
import com.gen.com.Insurance_portal.services.IAuthoritiesGroupService;
import org.springframework.stereotype.Service;

@Service
public class AuthoritiesGroupService extends AbstractService<AuthoritiesGroup> implements IAuthoritiesGroupService {

    private final AuthoritiesGroupRepository authoritiesGroupRepository;

    public AuthoritiesGroupService(AuthoritiesGroupRepository authoritiesGroupRepository) {
        super(authoritiesGroupRepository);
        this.authoritiesGroupRepository = authoritiesGroupRepository;
    }
}
