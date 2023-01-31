package com.gen.com.Insurance_portal.models.responseModels;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseCustomerUserInfor {
    private Long id;

    private String username;

    private String email;

    private String avatar;

    private String surname;

    private String givenName;

    private String phoneNumber;

    private Long customerId;

    private RoleResponseModel role;
}
