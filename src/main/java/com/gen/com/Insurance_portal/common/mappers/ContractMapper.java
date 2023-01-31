package com.gen.com.Insurance_portal.common.mappers;

import com.gen.com.Insurance_portal.entites.Contract;
import com.gen.com.Insurance_portal.models.responseModels.UContractModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ContractMapper {
    ContractMapper INSTANCE = Mappers.getMapper(ContractMapper.class);

    @Mapping(source = "productName", target = "product")
    UContractModel ContractToUContractModel(Contract contract);
}
