package com.gen.com.Insurance_portal.models.RequestModels;

import com.gen.com.Insurance_portal.common.constanst.Constants;
import com.gen.com.Insurance_portal.common.enums.Gender;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserModel {

    @NotBlank(message = "phoneNumber is required!")
    @Pattern(regexp = Constants.patternPhone, message = "phoneNumber Invalid")
    private String phoneNumber;

    @NotBlank(message = "givenName is required!")
    private String givenName;

    @NotBlank(message = "surname is required!")
    private String surname;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dob;

    @Schema(description = "value in: {0:Male, 1:FEMALE, 2:NONE}", type = "Integer")
    private Gender gender;

    @Email(message = "email invalid!")
    @NotBlank(message = "email is required!")
    private String email;

    @NotBlank(message = "idNumber is required!")
    private String idNumber;

    @NotBlank(message = "address is required!")
    private String address;

    @Pattern(regexp = Constants.patternPass, message = "password Invalid")
    private String password;

    @NotBlank(message = "username is required!")
    private String username;

}
