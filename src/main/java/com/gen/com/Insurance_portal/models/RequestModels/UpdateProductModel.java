package com.gen.com.Insurance_portal.models.RequestModels;

import com.gen.com.Insurance_portal.common.enums.GenderApply;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateProductModel {

    @Schema(name = "name", type = "String", required = true)
    private String name;

    @Schema(name = "code", type = "String", required = true)
    private String code;


    @Schema(name = "detailedDescription", type = "String", required = true)
    private String detailedDescription;

    @Schema(name = "productCategoryId", type = "Long", required = true)
    private Long productCategoryId;

    @Schema(name = "genderApply", type = "String", required = true, description = "value in {'ALL', 'Male', 'FEMALE'}")
    private GenderApply genderApply;

    @Schema(name = "priceObj", type = "Double", required = true)
    @Min(value = 1, message = "priceObj must be greater than 0")
    private Double priceObj;

    @Schema(name = "EffectiveDateRangeSelectionNumber", type = "Integer", required = true)
    private Integer EffectiveDateRangeSelectionNumber;

    @Schema(name = "shortDescription", type = "String", required = true)
    private String shortDescription;

    @Schema(name = "avatarImage", required = true)
    private String avatarImage;


    @Schema(name = "bannerImage", required = true)
    private String bannerImage;


    @Schema(name = "insuredRule", required = true)
    private String insuredRule;
}
