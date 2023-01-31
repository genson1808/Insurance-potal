package com.gen.com.Insurance_portal.services;

import com.gen.com.Insurance_portal.common.enums.RegisterStatus;
import com.gen.com.Insurance_portal.entites.User;
import com.gen.com.Insurance_portal.models.RequestModels.*;
import com.gen.com.Insurance_portal.models.responseModels.TokenResponse;

public interface IAuthService {

    TokenResponse authenticate(UsernameAndPasswordAuthentication authenticationModel) throws Exception;

    TokenResponse refreshToken(String refreshToken);

    RegisterStatus registerUser(User user, Boolean isProvider, String partnerCode);

    RegisterStatus registerCustomer(CreateUserModel customerModel);

    RegisterStatus registerPartner(CreateProviderModel providerModel);

    Boolean activeUser(UserModelActive modelActive);

    Boolean resendCode(ResendCodeModel resendCodeModel);

    Boolean updateInfo(String token, UpdateUserModel userModel);

}
