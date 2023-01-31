package com.gen.com.Insurance_portal.common.mappers;

import com.gen.com.Insurance_portal.entites.Claims;
import com.gen.com.Insurance_portal.entites.ClaimsInfo;
import com.gen.com.Insurance_portal.models.RequestModels.RequiredClaimsModel;
import com.gen.com.Insurance_portal.models.responseModels.ClaimsInfoModel;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-01-25T11:44:08+0700",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 1.8.0_265 (AdoptOpenJDK)"
)
public class ClaimsMapperImpl implements ClaimsMapper {

    @Override
    public Claims requiredClaimsModelToClaim(RequiredClaimsModel requiredClaimsModel) {
        if ( requiredClaimsModel == null ) {
            return null;
        }

        Claims claims = new Claims();

        claims.setContractCode( requiredClaimsModel.getContractCode() );
        claims.setName( requiredClaimsModel.getName() );
        claims.setNumberPlate( requiredClaimsModel.getNumberPlate() );
        claims.setRepaintFee( requiredClaimsModel.getRepaintFee() );
        claims.setRepaintFeeAmount( requiredClaimsModel.getRepaintFeeAmount() );
        claims.setBringingFee( requiredClaimsModel.getBringingFee() );
        claims.setBringingFeeAmount( requiredClaimsModel.getBringingFeeAmount() );
        claims.setRearViewMirror( requiredClaimsModel.getRearViewMirror() );
        claims.setRearViewMirrorAmount( requiredClaimsModel.getRearViewMirrorAmount() );
        claims.setComponentFee( requiredClaimsModel.getComponentFee() );
        claims.setComponentFeeAmount( requiredClaimsModel.getComponentFeeAmount() );
        claims.setScratchedFee( requiredClaimsModel.getScratchedFee() );
        claims.setScratchedFeeAmount( requiredClaimsModel.getScratchedFeeAmount() );
        claims.setEmployeeName( requiredClaimsModel.getEmployeeName() );
        claims.setEmployeePhoneNumber( requiredClaimsModel.getEmployeePhoneNumber() );
        claims.setNote( requiredClaimsModel.getNote() );

        return claims;
    }

    @Override
    public ClaimsInfoModel claimsToClaimsInto(ClaimsInfo claimsInfo) {
        if ( claimsInfo == null ) {
            return null;
        }

        ClaimsInfoModel claimsInfoModel = new ClaimsInfoModel();

        claimsInfoModel.setProductCode( claimsInfo.getProductCode() );
        claimsInfoModel.setCustomerCode( claimsInfo.getCustomerCode() );
        claimsInfoModel.setContractCode( claimsInfo.getContractCode() );
        claimsInfoModel.setComponentFee( claimsInfo.getComponentFee() );
        claimsInfoModel.setNumberComponent( claimsInfo.getNumberComponent() );
        claimsInfoModel.setScratchedFee( claimsInfo.getScratchedFee() );
        claimsInfoModel.setNumberScratched( claimsInfo.getNumberScratched() );
        claimsInfoModel.setRepaintFee( claimsInfo.getRepaintFee() );
        claimsInfoModel.setNumberRepaint( claimsInfo.getNumberRepaint() );
        claimsInfoModel.setBringingFee( claimsInfo.getBringingFee() );
        claimsInfoModel.setNumberBringing( claimsInfo.getNumberBringing() );
        claimsInfoModel.setRearViewMirror( claimsInfo.getRearViewMirror() );
        claimsInfoModel.setNumberRearViewMirror( claimsInfo.getNumberRearViewMirror() );
        claimsInfoModel.setMaximumCompensation( claimsInfo.getMaximumCompensation() );

        return claimsInfoModel;
    }
}
