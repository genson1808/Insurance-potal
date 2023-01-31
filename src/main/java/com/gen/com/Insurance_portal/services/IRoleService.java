package com.gen.com.Insurance_portal.services;

import com.gen.com.Insurance_portal.entites.Role;
import java.util.Set;

public interface IRoleService extends IAbstractService<Role> {

    Role findByName(String name);

    Set<Role> findRolesByNameIn(Set<String> roleNames);
}
