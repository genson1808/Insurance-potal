package com.gen.com.Insurance_portal.repositories;

import com.gen.com.Insurance_portal.entites.AuthoritiesGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthoritiesGroupRepository extends JpaRepository<AuthoritiesGroup, Long> {
}
