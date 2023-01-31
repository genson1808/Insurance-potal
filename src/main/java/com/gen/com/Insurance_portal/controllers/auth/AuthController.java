package com.gen.com.Insurance_portal.controllers.auth;

import com.gen.com.Insurance_portal.common.enums.RegisterStatus;
import com.gen.com.Insurance_portal.models.RequestModels.*;
import com.gen.com.Insurance_portal.models.responseModels.ResponseMessageModel;
import com.gen.com.Insurance_portal.services.IAuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final IAuthService authService;

    public AuthController(IAuthService authService) {
        this.authService = authService;
    }

    // authenticate authenticate account
    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(
            @RequestBody @Valid UsernameAndPasswordAuthentication authenticationModel) throws Exception {

        return new ResponseEntity<>(authService.authenticate(authenticationModel), HttpStatus.OK);
    }

    // registerCustomer register for customer ----->
    @PostMapping("/u/register")
    public ResponseEntity<?> registerCustomer(@Valid @RequestBody CreateUserModel userModel) {

        RegisterStatus result = authService.registerCustomer(userModel);

        if (result == RegisterStatus.ExistUser) {
            return new ResponseEntity<>(
                    new ResponseMessageModel(false),
                    HttpStatus.BAD_REQUEST
            );
        }else if (result == RegisterStatus.None) {
            return new ResponseEntity<>(
                    new ResponseMessageModel(false),
                    HttpStatus.BAD_REQUEST
            );
        }

        return new ResponseEntity<>(
                new ResponseMessageModel(true),
                HttpStatus.OK
        );
    }

    @PostMapping("/partner/register")
    public ResponseEntity<?> registerPartner(@Valid @RequestBody CreateProviderModel providerModel) {

        RegisterStatus result = authService.registerPartner(providerModel);

        if (result == RegisterStatus.ExistUser) {
            return new ResponseEntity<>(
                    new ResponseMessageModel(false),
                    HttpStatus.BAD_REQUEST
            );
        }else if (result == RegisterStatus.None) {
            return new ResponseEntity<>(
                    new ResponseMessageModel(false),
                    HttpStatus.BAD_REQUEST
            );
        }

        return new ResponseEntity<>(
                new ResponseMessageModel(true),
                HttpStatus.OK
        );
    }

    @Operation(summary = "Required Header { Authorization : bearer key }",security = { @SecurityRequirement(name = "bearer key") })
    // refresh refresh token ----->
    @PostMapping("/refresh")
    public ResponseEntity<?> refresh(@RequestBody @Valid RefreshTokenModel refreshModel) {

        return new ResponseEntity<>(authService.refreshToken(refreshModel.getRefreshToken()), HttpStatus.OK);
    }

    // resendCode resend virify code --->
    @PostMapping("/resendCode")
    public ResponseEntity<?> resendCode(@Valid  @RequestBody CreateUserModel userModel) {

        RegisterStatus result = authService.registerCustomer(userModel);

        return new ResponseEntity<>(
                new ResponseMessageModel(true),
                HttpStatus.OK
        );
    }

    // verify verify code to active account -->
    @PostMapping("/verify")
    public ResponseEntity<?> verify(@Valid @RequestBody UserModelActive modelActive) {

        Boolean ok = authService.activeUser(modelActive);

        if (!ok) {
            return new ResponseEntity<>(
                    new ResponseMessageModel(false),
                    HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(new ResponseMessageModel(true), HttpStatus.OK);
    }

    @Operation(summary = "Required Header { Authorization : bearer key }",security = { @SecurityRequirement(name = "bearer key") })
    @PutMapping
    public ResponseEntity<?> update(@RequestHeader (name="Authorization") String token,
                                    @Valid @RequestBody UpdateUserModel updateUserModel) {

        Boolean ok = authService.updateInfo(token, updateUserModel);

        if (!ok) {
            return new ResponseEntity<>(
                    new ResponseMessageModel(false),
                    HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(
                new ResponseMessageModel(true),
                HttpStatus.OK);
    }




}
