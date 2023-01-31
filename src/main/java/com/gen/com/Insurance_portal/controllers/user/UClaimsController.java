package com.gen.com.Insurance_portal.controllers.user;

import com.gen.com.Insurance_portal.models.RequestModels.ParamsModel;
import com.gen.com.Insurance_portal.models.RequestModels.RequiredClaimsModel;
import com.gen.com.Insurance_portal.services.IClaimsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/u/claims")
public class UClaimsController {
    private final IClaimsService claimsService;

    public UClaimsController(IClaimsService claimsService) {
        this.claimsService = claimsService;
    }

    @PostMapping
    public ResponseEntity<?> requiredClaims(@RequestBody RequiredClaimsModel requiredClaimsModel) {
        return new ResponseEntity<>(claimsService.requiredClaims(requiredClaimsModel), HttpStatus.OK);
    }


    @Operation(summary = "Required Header { Authorization : bearer key }",security = { @SecurityRequirement(name = "bearer key") })
    @GetMapping
    public ResponseEntity<?> getAllFormat(
            @Parameter(name = "filter", description = "optional field, filter by contractCode")
            @RequestParam(required = false) String filter,
            @Parameter(name = "page", description = "optional field, The default page is 1")
            @RequestParam(defaultValue = "1") int page,
            @Parameter(name = "size", description = "optional field, the size of a default page is 10")
            @RequestParam(defaultValue = "10") int size,
            @Parameter(name = "sort", description = "optional field, syntax=field...,{desc/asc}")
            @RequestParam(defaultValue = "id,desc") String[] sort,
            @RequestHeader (name="Authorization") String token) {

        Object response = claimsService.UGetList(new ParamsModel(filter, page, size, sort), token);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
