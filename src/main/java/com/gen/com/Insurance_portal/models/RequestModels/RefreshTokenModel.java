package com.gen.com.Insurance_portal.models.RequestModels;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class RefreshTokenModel {
    @NotEmpty(message = "refreshToken is required!")
    private String refreshToken;
}
