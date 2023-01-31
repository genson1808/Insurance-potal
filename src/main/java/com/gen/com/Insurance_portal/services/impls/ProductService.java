package com.gen.com.Insurance_portal.services.impls;

import com.gen.com.Insurance_portal.common.enums.ProductStatus;
import com.gen.com.Insurance_portal.common.mappers.ProductMapper;
import com.gen.com.Insurance_portal.entites.Product;
import com.gen.com.Insurance_portal.entites.ProductCategory;
import com.gen.com.Insurance_portal.exceptions.MessageException;
import com.gen.com.Insurance_portal.exceptions.NotFoundEntityException;
import com.gen.com.Insurance_portal.exceptions.NotFoundEntityExceptionByCode;
import com.gen.com.Insurance_portal.models.RequestModels.CreateProductModel;
import com.gen.com.Insurance_portal.models.RequestModels.ProductStatusRequest;
import com.gen.com.Insurance_portal.models.RequestModels.UpdateProductModel;
import com.gen.com.Insurance_portal.models.responseModels.ProductDetailModel;
import com.gen.com.Insurance_portal.models.responseModels.ResponseProductModel;
import com.gen.com.Insurance_portal.repositories.ProductRepository;
import com.gen.com.Insurance_portal.services.IProductCategoryService;
import com.gen.com.Insurance_portal.services.IProductService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Service
public class ProductService extends AbstractService<Product> implements IProductService {
    private final ProductRepository productRepository;
    private final IProductCategoryService productCategoryService;

    public ProductService(ProductRepository productRepository,
                          IProductCategoryService productCategoryService) {

        super(productRepository);
        this.productRepository = productRepository;
        this.productCategoryService = productCategoryService;
    }

    @Override
    public void create(CreateProductModel productModel) throws ExecutionException, InterruptedException {
        Product product = ProductMapper.INSTANCE.createProductModelToProduct(productModel);

        Boolean existsByNameOrCode = productRepository.existsByNameOrCode(productModel.getName(), productModel.getCode());
        if (existsByNameOrCode) {
            throw new MessageException("Name or code already exists.");
        }
        if (productModel.getAvatarImage() != null) {
            product.setAvatarImage(productModel.getAvatarImage());
        }
        if (productModel.getBannerImage() != null) {
            product.setBannerImage(productModel.getBannerImage());
        }
        if (productModel.getInsuredRule() != null) {
            product.setInsuredRule(productModel.getInsuredRule());
        }

        ProductCategory productCategory = productCategoryService.findById(productModel.getProductCategoryId())
                .orElseThrow(() -> new NotFoundEntityException(productModel.getProductCategoryId(), "productCategory"));


        product.setProductCategory(productCategory);

        this.save(product);
    }

    @Override
    public void update(UpdateProductModel productModel, Long id) throws ExecutionException, InterruptedException {
        Product product = findById(id).orElseThrow(() -> new NotFoundEntityException(id, "Product"));

        Boolean existsByCodeAndIdIsNot = false;
        if (!Strings.isBlank(productModel.getName()) || !Strings.isBlank(productModel.getCode())){
            existsByCodeAndIdIsNot = productRepository
                    .existsByCodeAndIdIsNot(productModel.getCode(), id);
        }

        if (existsByCodeAndIdIsNot) {
            throw new MessageException("Name or code already exists.");
        }

        if (!Strings.isBlank(productModel.getCode())) {
            product.setCode(productModel.getCode());
        }
        if (!Strings.isBlank(productModel.getDetailedDescription())) {
            product.setDetailedDescription(productModel.getDetailedDescription());
        }
        if (!Strings.isBlank(productModel.getName())) {
            product.setName(productModel.getName());
        }
        if (!Strings.isBlank(productModel.getShortDescription())) {
            product.setShortDescription(productModel.getShortDescription());
        }
        if (productModel.getPriceObj() != null) {
            product.setPriceObj(productModel.getPriceObj());
        }
        if (productModel.getGenderApply() != null) {
            product.setGenderApply(productModel.getGenderApply());
        }
        if (productModel.getProductCategoryId() != null) {
            ProductCategory productCategory = productCategoryService.findById(productModel.getProductCategoryId())
                    .orElseThrow(() -> new NotFoundEntityException(productModel.getProductCategoryId(), "productCategory"));
            product.setProductCategory(productCategory);
        }
        if (productModel.getEffectiveDateRangeSelectionNumber() != null) {
            product.setEffectiveDateRangeSelectionNumber(productModel.getEffectiveDateRangeSelectionNumber());
        }
        if (productModel.getAvatarImage() != null) {
            product.setAvatarImage(productModel.getAvatarImage());
        }
        if (productModel.getBannerImage() != null) {
            product.setBannerImage(productModel.getBannerImage());
        }
        if (productModel.getInsuredRule() != null) {
            product.setInsuredRule(productModel.getInsuredRule());
        }
        update(product);

    }

    @Override
    public ProductDetailModel findByCode(String code) {
        Product product = productRepository.findByCode(code)
                .orElseThrow(() -> new NotFoundEntityExceptionByCode(code, "Product"));

        ProductDetailModel productDetailModel = ProductMapper.INSTANCE.ProductToProductDetailModel(product);
        productDetailModel.setCategoryId(product.getProductCategory().getId());
        productDetailModel.setCategoryName(product.getProductCategory().getName());

        return productDetailModel;
    }

    @Override
    public List<ResponseProductModel> getList() {
        return productRepository.findProductsByProductStatus(ProductStatus.APPROVED).stream()
                .map(ProductMapper.INSTANCE::ProductModelToProductResponse).collect(Collectors.toList());
    }

    @Override
    public void updateByCode(UpdateProductModel productModel, String code) throws ExecutionException, InterruptedException {
        Product product = productRepository.findByCode(code)
                .orElseThrow(() -> new NotFoundEntityExceptionByCode(code, "Product"));

        Boolean existsByCodeAndIdIsNot = false;
        if (!Strings.isBlank(productModel.getName()) || !Strings.isBlank(productModel.getCode())){
            existsByCodeAndIdIsNot = productRepository
                    .existsByCodeAndIdIsNot(productModel.getCode(), product.getId());
        }

        if (existsByCodeAndIdIsNot) {
            throw new MessageException("Name or code already exists.");
        }

        if (!Strings.isBlank(productModel.getCode())) {
            product.setCode(productModel.getCode());
        }
        if (!Strings.isBlank(productModel.getDetailedDescription())) {
            product.setDetailedDescription(productModel.getDetailedDescription());
        }
        if (!Strings.isBlank(productModel.getName())) {
            product.setName(productModel.getName());
        }
        if (!Strings.isBlank(productModel.getShortDescription())) {
            product.setShortDescription(productModel.getShortDescription());
        }
        if (productModel.getPriceObj() != null) {
            product.setPriceObj(productModel.getPriceObj());
        }
        if (productModel.getGenderApply() != null) {
            product.setGenderApply(productModel.getGenderApply());
        }
        if (productModel.getProductCategoryId() != null) {
            ProductCategory productCategory = productCategoryService.findById(productModel.getProductCategoryId())
                    .orElseThrow(() -> new NotFoundEntityException(productModel.getProductCategoryId(), "productCategory"));
            product.setProductCategory(productCategory);
        }
        if (productModel.getEffectiveDateRangeSelectionNumber() != null) {
            product.setEffectiveDateRangeSelectionNumber(productModel.getEffectiveDateRangeSelectionNumber());
        }

        if (productModel.getAvatarImage() != null) {
            product.setAvatarImage(productModel.getAvatarImage());
        }
        if (productModel.getBannerImage() != null) {
            product.setBannerImage(productModel.getBannerImage());
        }
        if (productModel.getInsuredRule() != null) {
            product.setInsuredRule(productModel.getInsuredRule());
        }
        update(product);
    }

    @Override
    public void deleteByCode(String code) {
        Product product = productRepository.findByCode(code)
                .orElseThrow(() -> new NotFoundEntityExceptionByCode(code, "Product"));
        delete(product);
    }

    @Override
    public void status(String code, ProductStatusRequest statusRequest) {
        Product product = productRepository.findByCode(code)
                .orElseThrow(() -> new NotFoundEntityExceptionByCode(code, "Product"));
        product.setProductStatus(statusRequest.getStatus());
        update(product);
    }
}
