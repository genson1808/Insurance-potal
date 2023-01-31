package com.gen.com.Insurance_portal.repositories;

import com.gen.com.Insurance_portal.entites.Authorities;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthoritiesRepositoty extends JpaRepository<Authorities, Long> {
    Authorities findByCode (String code);
}
