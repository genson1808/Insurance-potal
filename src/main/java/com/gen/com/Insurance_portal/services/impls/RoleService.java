package com.gen.com.Insurance_portal.services.impls;

import com.gen.com.Insurance_portal.entites.Role;
import com.gen.com.Insurance_portal.repositories.RoleRepository;
import com.gen.com.Insurance_portal.services.IRoleService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class RoleService extends AbstractService<Role> implements IRoleService {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        super(roleRepository);
        this.roleRepository = roleRepository;
    }

    @Override
    public Role findByName(String name) {
        return roleRepository.findByName(name);
    }

    @Override
    public Set<Role> findRolesByNameIn(Set<String> roleNames) {
        return null;
    }
}
