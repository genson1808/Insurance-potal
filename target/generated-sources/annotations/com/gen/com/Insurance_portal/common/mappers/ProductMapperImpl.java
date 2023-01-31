package com.gen.com.Insurance_portal.common.mappers;

import com.gen.com.Insurance_portal.entites.Product;
import com.gen.com.Insurance_portal.models.RequestModels.CreateProductModel;
import com.gen.com.Insurance_portal.models.RequestModels.UpdateProductModel;
import com.gen.com.Insurance_portal.models.responseModels.ProductDetailModel;
import com.gen.com.Insurance_portal.models.responseModels.ResponseProductModel;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-01-25T11:44:08+0700",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 1.8.0_265 (AdoptOpenJDK)"
)
public class ProductMapperImpl implements ProductMapper {

    @Override
    public Product createProductModelToProduct(CreateProductModel productModel) {
        if ( productModel == null ) {
            return null;
        }

        Product product = new Product();

        product.setGenderApply( productModel.getGenderApply() );
        product.setName( productModel.getName() );
        product.setCode( productModel.getCode() );
        product.setShortDescription( productModel.getShortDescription() );
        product.setDetailedDescription( productModel.getDetailedDescription() );
        product.setEffectiveDateRangeSelectionNumber( productModel.getEffectiveDateRangeSelectionNumber() );
        product.setPriceObj( productModel.getPriceObj() );
        product.setComponentFee( productModel.getComponentFee() );
        product.setNumberComponent( productModel.getNumberComponent() );
        product.setScratchedFee( productModel.getScratchedFee() );
        product.setNumberScratched( productModel.getNumberScratched() );
        product.setRepaintFee( productModel.getRepaintFee() );
        product.setNumberRepaint( productModel.getNumberRepaint() );
        product.setBringingFee( productModel.getBringingFee() );
        product.setNumberBringing( productModel.getNumberBringing() );
        product.setRearViewMirror( productModel.getRearViewMirror() );
        product.setNumberRearViewMirror( productModel.getNumberRearViewMirror() );

        return product;
    }

    @Override
    public Product updateProductModelToProduct(UpdateProductModel productModel) {
        if ( productModel == null ) {
            return null;
        }

        Product product = new Product();

        product.setGenderApply( productModel.getGenderApply() );
        product.setName( productModel.getName() );
        product.setCode( productModel.getCode() );
        product.setShortDescription( productModel.getShortDescription() );
        product.setDetailedDescription( productModel.getDetailedDescription() );
        product.setEffectiveDateRangeSelectionNumber( productModel.getEffectiveDateRangeSelectionNumber() );
        product.setPriceObj( productModel.getPriceObj() );

        return product;
    }

    @Override
    public ResponseProductModel ProductModelToProductResponse(Product productModel) {
        if ( productModel == null ) {
            return null;
        }

        ResponseProductModel responseProductModel = new ResponseProductModel();

        responseProductModel.setName( productModel.getName() );
        responseProductModel.setCode( productModel.getCode() );

        return responseProductModel;
    }

    @Override
    public ProductDetailModel ProductToProductDetailModel(Product productModel) {
        if ( productModel == null ) {
            return null;
        }

        ProductDetailModel productDetailModel = new ProductDetailModel();

        productDetailModel.setId( productModel.getId() );
        productDetailModel.setName( productModel.getName() );
        productDetailModel.setCode( productModel.getCode() );
        productDetailModel.setShortDescription( productModel.getShortDescription() );
        productDetailModel.setDetailedDescription( productModel.getDetailedDescription() );
        productDetailModel.setAvatarImage( productModel.getAvatarImage() );
        productDetailModel.setBannerImage( productModel.getBannerImage() );
        productDetailModel.setIndemnityTemplate( productModel.getIndemnityTemplate() );
        productDetailModel.setIndemnityInstruction( productModel.getIndemnityInstruction() );
        productDetailModel.setIndemnityInstructionContent( productModel.getIndemnityInstructionContent() );
        productDetailModel.setEffectiveDateType( productModel.getEffectiveDateType() );
        productDetailModel.setFeeType( productModel.getFeeType() );
        productDetailModel.setProductStatus( productModel.getProductStatus() );
        productDetailModel.setGenderApply( productModel.getGenderApply() );
        productDetailModel.setIsActive( productModel.getIsActive() );
        productDetailModel.setDuplicateBuyerInfo( productModel.getDuplicateBuyerInfo() );
        productDetailModel.setHideBeneficiary( productModel.getHideBeneficiary() );
        productDetailModel.setEffectiveDateRangeSelectionNumber( productModel.getEffectiveDateRangeSelectionNumber() );
        productDetailModel.setEnableIndemnity( productModel.getEnableIndemnity() );
        productDetailModel.setIsSelfInsurance( productModel.getIsSelfInsurance() );
        productDetailModel.setHideBuyerInfo( productModel.getHideBuyerInfo() );
        productDetailModel.setVideoUrl( productModel.getVideoUrl() );
        productDetailModel.setPriceObj( productModel.getPriceObj() );
        productDetailModel.setInsuredRule( productModel.getInsuredRule() );
        productDetailModel.setComponentFee( productModel.getComponentFee() );
        productDetailModel.setNumberComponent( productModel.getNumberComponent() );
        productDetailModel.setScratchedFee( productModel.getScratchedFee() );
        productDetailModel.setNumberScratched( productModel.getNumberScratched() );
        productDetailModel.setRepaintFee( productModel.getRepaintFee() );
        productDetailModel.setNumberRepaint( productModel.getNumberRepaint() );
        productDetailModel.setBringingFee( productModel.getBringingFee() );
        productDetailModel.setNumberBringing( productModel.getNumberBringing() );
        productDetailModel.setRearViewMirror( productModel.getRearViewMirror() );
        productDetailModel.setNumberRearViewMirror( productModel.getNumberRearViewMirror() );

        return productDetailModel;
    }
}
