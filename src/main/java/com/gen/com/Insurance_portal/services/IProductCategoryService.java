package com.gen.com.Insurance_portal.services;

import com.gen.com.Insurance_portal.entites.ProductCategory;
import com.gen.com.Insurance_portal.models.RequestModels.CreateProductCategoryModel;

public interface IProductCategoryService extends IAbstractService<ProductCategory> {
    void create(CreateProductCategoryModel productModel);
    void update(CreateProductCategoryModel productModel, Long id);
}
