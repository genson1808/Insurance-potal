package com.gen.com.Insurance_portal.common.mappers;

import com.gen.com.Insurance_portal.entites.Claims;
import com.gen.com.Insurance_portal.entites.ClaimsInfo;
import com.gen.com.Insurance_portal.models.RequestModels.RequiredClaimsModel;
import com.gen.com.Insurance_portal.models.responseModels.ClaimsInfoModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ClaimsMapper {
    ClaimsMapper INSTANCE = Mappers.getMapper(ClaimsMapper.class);

    Claims requiredClaimsModelToClaim(RequiredClaimsModel requiredClaimsModel);

    ClaimsInfoModel claimsToClaimsInto(ClaimsInfo claimsInfo);
}
