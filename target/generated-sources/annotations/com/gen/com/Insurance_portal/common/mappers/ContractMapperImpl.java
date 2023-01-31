package com.gen.com.Insurance_portal.common.mappers;

import com.gen.com.Insurance_portal.entites.Contract;
import com.gen.com.Insurance_portal.models.responseModels.UContractModel;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-01-25T11:44:08+0700",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 1.8.0_265 (AdoptOpenJDK)"
)
public class ContractMapperImpl implements ContractMapper {

    @Override
    public UContractModel ContractToUContractModel(Contract contract) {
        if ( contract == null ) {
            return null;
        }

        UContractModel uContractModel = new UContractModel();

        uContractModel.setProduct( contract.getProductName() );
        uContractModel.setCode( contract.getCode() );
        uContractModel.setIdNumber( contract.getIdNumber() );
        uContractModel.setStatus( contract.getStatus() );
        uContractModel.setEffectiveDate( contract.getEffectiveDate() );
        uContractModel.setFullName( contract.getFullName() );
        uContractModel.setProductCode( contract.getProductCode() );

        return uContractModel;
    }
}
