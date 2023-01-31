package com.gen.com.Insurance_portal.models.RequestModels;

import com.gen.com.Insurance_portal.common.enums.GenderApply;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "CreateProductModel")
public class CreateProductModel {

    @Schema(name = "name", type = "String", required = true)
    @NotBlank(message = "name is required!")
    private String name;

    @Schema(name = "code", type = "String", required = true)
    @NotBlank(message = "code is required!")
    private String code;

    @Schema(name = "detailedDescription", type = "String", required = true)
    @NotBlank(message = "code is required!")
    private String detailedDescription;

    @Schema(name = "productCategoryId", type = "Long", required = true)
    @NotNull(message = "productCategoryId is required!")
    private Long productCategoryId;

    @Schema(name = "genderApply", type = "String", required = true, description = "value in {'ALL', 'Male', 'FEMALE'}")
    @NotNull(message = "genderApply is required!")
    private GenderApply genderApply;

    @Schema(name = "priceObj", type = "Double", required = true)
    @NotNull(message = "priceObj is required!")
    @Min(value = 1, message = "priceObj must be greater than 0")
    private Double priceObj;

    @Schema(name = "EffectiveDateRangeSelectionNumber", type = "Integer", required = true)
    @NotNull(message = "EffectiveDateRangeSelectionNumber is required!")
    private Integer EffectiveDateRangeSelectionNumber;

    @Schema(name = "shortDescription", type = "String", required = true)
    @NotBlank(message = "shortDescription is required!")
    private String shortDescription;

    @Schema(name = "avatarImage", required = true)
    @NotBlank(message = "avatarImage is required!")
    private String avatarImage;


    @Schema(name = "bannerImage", required = true)
    @NotBlank(message = "bannerImage is required!")
    private String bannerImage;


    @Schema(name = "insuredRule", required = true)
    @NotBlank(message = "insuredRule is required!")
    private String insuredRule;

    @NotNull(message = "componentFee is required!")
    private Integer componentFee;  // bộ phận

    @NotNull(message = "numberComponent is required!")
    private Integer numberComponent;

    @NotNull(message = "scratchedFee is required!")
    private Integer scratchedFee;  // trầy xước

    @NotNull(message = "numberScratched is required!")
    private Integer numberScratched;

    @NotNull(message = "repaintFee is required!")
    private Integer repaintFee;  // sơn lại

    @NotNull(message = "numberRepaint is required!")
    private Integer numberRepaint;

    @NotNull(message = "bringingFee is required!")
    private Integer bringingFee;  // cứu hộ

    @NotNull(message = "numberBringing is required!")
    private Integer numberBringing;

    @NotNull(message = "rearViewMirror is required!")
    private Integer rearViewMirror;  // gương chiếu hậu

    @NotNull(message = "numberRearViewMirror is required!")
    private Integer numberRearViewMirror;

}
