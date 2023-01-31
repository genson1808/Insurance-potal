package com.gen.com.Insurance_portal.common.mappers;

import com.gen.com.Insurance_portal.entites.Role;
import com.gen.com.Insurance_portal.models.responseModels.RoleResponseModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RoleMapper {
    RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);

    RoleResponseModel roleToRoleResponse(Role role);
}
