package com.gen.com.Insurance_portal.controllers.auth;

import com.gen.com.Insurance_portal.entites.Claims;
import com.gen.com.Insurance_portal.exceptions.NotFoundEntityException;
import com.gen.com.Insurance_portal.models.RequestModels.ClaimsStatusModel;
import com.gen.com.Insurance_portal.models.RequestModels.ParamsModel;
import com.gen.com.Insurance_portal.models.responseModels.ResponseMessageModel;
import com.gen.com.Insurance_portal.services.IClaimsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/claims")
public class ClaimsController {
    private final IClaimsService claimsService;

    public ClaimsController(IClaimsService claimsService) {
        this.claimsService = claimsService;
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

        Object response = claimsService.GetList(new ParamsModel(filter, page, size, sort), token);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "Required Header { Authorization : bearer key }",security = { @SecurityRequirement(name = "bearer key") })
    @PostMapping("/status/{id}")
    public ResponseEntity<?> status(@PathVariable Long id, @RequestBody ClaimsStatusModel statusRequest) {
        claimsService.status(id, statusRequest.getStatus());
        return new ResponseEntity<>(new ResponseMessageModel(true), HttpStatus.OK);
    }

    @Operation(summary = "Required Header { Authorization : bearer key }",security = { @SecurityRequirement(name = "bearer key") })
    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable Long id) {
        Claims claims = claimsService.findById(id).orElseThrow(() -> new NotFoundEntityException(id, "Claims"));
        return new ResponseEntity<>(claims, HttpStatus.OK);
    }

    @Operation(summary = "Required Header { Authorization : bearer key }",security = { @SecurityRequirement(name = "bearer key") })
    @GetMapping("/by-contract-code/{contractCode}")
    public ResponseEntity<?> getByContractCode(@PathVariable String contractCode) {
        Claims claims = claimsService.findByContractCode(contractCode);
        return new ResponseEntity<>(claims, HttpStatus.OK);
    }
}
