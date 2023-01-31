package com.gen.com.Insurance_portal.common.mappers;

import com.gen.com.Insurance_portal.entites.Partner;
import com.gen.com.Insurance_portal.models.RequestModels.CreatePartnerModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductProviderMapper {
    ProductProviderMapper INSTANCE = Mappers.getMapper(ProductProviderMapper.class);

    Partner createProductProviderModelToProductProvider(CreatePartnerModel productProviderModel);
}
