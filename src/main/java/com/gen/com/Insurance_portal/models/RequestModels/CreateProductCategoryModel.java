package com.gen.com.Insurance_portal.models.RequestModels;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "CreateProductCategoryModel")
public class CreateProductCategoryModel {

    @Schema(name = "name", type = "String", required = true)
    @NotBlank(message = "name is required!")
    private String name;

    @Schema(name = "description", type = "String", required = true)
    @NotBlank(message = "description is required!")
    private String description;
}
