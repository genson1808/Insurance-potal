package com.gen.com.Insurance_portal.models.RequestModels;

import com.gen.com.Insurance_portal.common.constanst.Constants;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "CreatePartnerModel")
public class CreatePartnerModel {

    @Schema(name = "name", type = "String", required = true)
    @NotBlank(message = "name is required!")
    private String name;

    @Schema(name = "email", type = "String", required = true)
    @Email(message = "email invalid!")
    @NotBlank(message = "email is required!")
    private String email;

    @Schema(name = "phoneNumber", type = "String", required = true)
    @NotBlank(message = "phoneNumber is required!")
    @Pattern(regexp = Constants.patternPhone, message = "phoneNumber Invalid")
    private String phoneNumber;

    @Schema(name = "introductionContent", type = "String", required = false)
    private String introductionContent;

    @Schema(name = "appellation", type = "String", required = true)
    @NotBlank(message = "appellation is required!")
    private String appellation;

    @Schema(name = "contact", type = "String", required = true)
    @NotBlank(message = "contact is required!")
    private String contact;

    @Schema(name = "code", type = "String", required = true)
    @NotBlank(message = "code is required!")
    private String code;

    @Schema(name = "hotline", type = "String", required = false)
    private String hotline;

    @Schema(name = "images", type = "String", required = false)
    private String images;
}
