package com.gen.com.Insurance_portal.common.mappers;

import com.gen.com.Insurance_portal.entites.ProductCategory;
import com.gen.com.Insurance_portal.models.RequestModels.CreateProductCategoryModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductCategoryMapper {
    ProductCategoryMapper INSTANCE = Mappers.getMapper(ProductCategoryMapper.class);

    ProductCategory createProductCategoryModelToProductCategory(CreateProductCategoryModel productCategoryModel);
}
