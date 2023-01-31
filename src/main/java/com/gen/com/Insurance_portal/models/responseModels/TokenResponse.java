package com.gen.com.Insurance_portal.models.responseModels;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TokenResponse {
    private String tokenType = "Bearer ";
    private String accessToken;
    private String refreshToken;
    private Object infor;

    public TokenResponse(String accessToken, String refreshToken , Object userInfor) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.infor = userInfor;
    }

}
