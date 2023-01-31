package com.gen.com.Insurance_portal.models.responseModels;

import com.gen.com.Insurance_portal.common.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerResponseModel {

    private Long id;

    private String customerCode;

    private String surname;

    private String givenName;

    private String username;

    private String phoneNumber;

    private String email;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dod;

    private Gender gender = Gender.NONE;

    private String address;

    private String company;

    private Boolean isActive;


}
