package com.gen.com.Insurance_portal.services;

import com.gen.com.Insurance_portal.common.enums.ContractStatus;
import com.gen.com.Insurance_portal.entites.Contract;
import com.gen.com.Insurance_portal.models.RequestModels.ParamsModel;
import com.gen.com.Insurance_portal.models.responseModels.ClaimsInfoModel;

import java.util.Optional;

public interface IContractService extends IAbstractService<Contract> {
    Boolean existsByCode(String code);
    Optional<Contract> findByCode(String code);
    Object getList(ParamsModel paramsModel, Boolean forUser);
    Object UGetList(ParamsModel paramsModel, String token);
    void changeStatus(String code, ContractStatus contractStatus);
    ClaimsInfoModel getClaimsInfoByContractCode(String code);
}
