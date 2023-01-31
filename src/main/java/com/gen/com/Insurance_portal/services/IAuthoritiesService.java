package com.gen.com.Insurance_portal.services;

import com.gen.com.Insurance_portal.entites.Authorities;

public interface IAuthoritiesService extends IAbstractService<Authorities> {
    Authorities findByCode (String code);
}
