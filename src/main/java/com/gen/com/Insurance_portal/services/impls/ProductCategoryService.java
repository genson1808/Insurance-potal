package com.gen.com.Insurance_portal.services.impls;

import com.gen.com.Insurance_portal.common.mappers.ProductCategoryMapper;
import com.gen.com.Insurance_portal.entites.ProductCategory;
import com.gen.com.Insurance_portal.exceptions.NotFoundEntityException;
import com.gen.com.Insurance_portal.models.RequestModels.CreateProductCategoryModel;
import com.gen.com.Insurance_portal.repositories.ProductCategoryRepository;
import com.gen.com.Insurance_portal.services.IProductCategoryService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;

@Service
public class ProductCategoryService extends AbstractService<ProductCategory> implements IProductCategoryService {

    private final ProductCategoryRepository productCategoryRepository;

    public ProductCategoryService(ProductCategoryRepository productCategoryRepository) {
        super(productCategoryRepository);
        this.productCategoryRepository = productCategoryRepository;
    }


    @Override
    public void create(CreateProductCategoryModel productModel) {
        ProductCategory productCategory = ProductCategoryMapper.INSTANCE
                .createProductCategoryModelToProductCategory(productModel);
        this.save(productCategory);
    }

    @Override
    public void update(CreateProductCategoryModel productModel, Long id) {
        ProductCategory productCategory = this.findById(id)
                .orElseThrow(() -> new NotFoundEntityException(id, "ProductCategory"));

        if (!Strings.isBlank(productModel.getName())){
            productCategory.setName(productModel.getName());
        }
        if (!Strings.isBlank(productModel.getDescription())){
            productCategory.setDescription(productModel.getDescription());
        }
        this.update(productCategory);
    }
}
