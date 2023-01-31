package com.gen.com.Insurance_portal.common.mappers;

import com.gen.com.Insurance_portal.entites.Partner;
import com.gen.com.Insurance_portal.models.RequestModels.CreatePartnerModel;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-01-25T11:44:08+0700",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 1.8.0_265 (AdoptOpenJDK)"
)
public class ProductProviderMapperImpl implements ProductProviderMapper {

    @Override
    public Partner createProductProviderModelToProductProvider(CreatePartnerModel productProviderModel) {
        if ( productProviderModel == null ) {
            return null;
        }

        Partner partner = new Partner();

        partner.setName( productProviderModel.getName() );
        partner.setCode( productProviderModel.getCode() );
        partner.setEmail( productProviderModel.getEmail() );
        partner.setPhoneNumber( productProviderModel.getPhoneNumber() );
        partner.setHotline( productProviderModel.getHotline() );
        partner.setIntroductionContent( productProviderModel.getIntroductionContent() );
        partner.setAppellation( productProviderModel.getAppellation() );
        partner.setContact( productProviderModel.getContact() );

        return partner;
    }
}
