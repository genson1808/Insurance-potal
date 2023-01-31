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
@Schema(name = "CreateProviderModel")
public class CreateProviderModel {

    @NotBlank(message = "phoneNumber is required!")
    @Pattern(regexp = Constants.patternPhone, message = "phoneNumber Invalid")
    private String phoneNumber;

    @NotBlank(message = "name is required!")
    private String name;

    private String introductionContent;

    private String avatarImage;

    @NotBlank(message = "appellation is required!")
    private String appellation;

    @NotBlank(message = "code is required!")
    private String code;

    @NotBlank(message = "contact is required!")
    private String contact;

    @Email(message = "email invalid!")
    @NotBlank(message = "email is required!")
    private String email;

    @NotBlank(message = "address is required!")
    private String address;

    @Pattern(regexp = Constants.patternPass, message = "password Invalid")
    private String password;

    @NotBlank(message = "username is required!")
    private String username;

    @NotBlank(message = "hotline is required!")
    private String hotline;
}
