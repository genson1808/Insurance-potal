package com.gen.com.Insurance_portal.services.impls;

import com.gen.com.Insurance_portal.entites.Authorities;
import com.gen.com.Insurance_portal.repositories.AuthoritiesRepositoty;
import com.gen.com.Insurance_portal.services.IAuthoritiesService;
import org.springframework.stereotype.Service;

@Service
public class AuthoritiesService extends AbstractService<Authorities> implements IAuthoritiesService {
    private final AuthoritiesRepositoty authoritiesRepositoty;

    public AuthoritiesService(AuthoritiesRepositoty authoritiesRepositoty) {
        super(authoritiesRepositoty);
        this.authoritiesRepositoty = authoritiesRepositoty;
    }

    @Override
    public Authorities findByCode(String code) {
        return authoritiesRepositoty.findByCode(code);
    }
}
