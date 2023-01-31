package com.gen.com.Insurance_portal.services;

import com.gen.com.Insurance_portal.common.enums.ClaimsStatus;
import com.gen.com.Insurance_portal.entites.Claims;
import com.gen.com.Insurance_portal.models.RequestModels.ParamsModel;
import com.gen.com.Insurance_portal.models.RequestModels.RequiredClaimsModel;
import com.gen.com.Insurance_portal.models.responseModels.ResponseRequiredClaim;

public interface IClaimsService extends IAbstractService<Claims> {
    ResponseRequiredClaim requiredClaims(RequiredClaimsModel requiredClaimsModel);
    Object UGetList(ParamsModel paramsModel, String token);
    Object GetList(ParamsModel paramsModel, String token);
    void status(Long id, ClaimsStatus status);
    Claims findByContractCode(String code);
}
