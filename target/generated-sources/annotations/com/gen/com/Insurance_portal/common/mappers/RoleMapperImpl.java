package com.gen.com.Insurance_portal.common.mappers;

import com.gen.com.Insurance_portal.entites.Role;
import com.gen.com.Insurance_portal.models.responseModels.RoleResponseModel;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-01-25T11:44:09+0700",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 1.8.0_265 (AdoptOpenJDK)"
)
public class RoleMapperImpl implements RoleMapper {

    @Override
    public RoleResponseModel roleToRoleResponse(Role role) {
        if ( role == null ) {
            return null;
        }

        RoleResponseModel roleResponseModel = new RoleResponseModel();

        roleResponseModel.setName( role.getName() );

        return roleResponseModel;
    }
}
