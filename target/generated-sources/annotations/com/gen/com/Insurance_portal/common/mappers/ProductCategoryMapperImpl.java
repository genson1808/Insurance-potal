package com.gen.com.Insurance_portal.common.mappers;

import com.gen.com.Insurance_portal.entites.ProductCategory;
import com.gen.com.Insurance_portal.models.RequestModels.CreateProductCategoryModel;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-01-25T11:44:08+0700",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 1.8.0_265 (AdoptOpenJDK)"
)
public class ProductCategoryMapperImpl implements ProductCategoryMapper {

    @Override
    public ProductCategory createProductCategoryModelToProductCategory(CreateProductCategoryModel productCategoryModel) {
        if ( productCategoryModel == null ) {
            return null;
        }

        ProductCategory productCategory = new ProductCategory();

        productCategory.setName( productCategoryModel.getName() );
        productCategory.setDescription( productCategoryModel.getDescription() );

        return productCategory;
    }
}
