package com.gen.com.Insurance_portal.controllers.auth;

import com.gen.com.Insurance_portal.common.enums.ContractStatus;
import com.gen.com.Insurance_portal.entites.Claims;
import com.gen.com.Insurance_portal.entites.Contract;
import com.gen.com.Insurance_portal.exceptions.NotFoundEntityException;
import com.gen.com.Insurance_portal.models.RequestModels.ParamsModel;
import com.gen.com.Insurance_portal.models.RequestModels.RequestStatus;
import com.gen.com.Insurance_portal.models.responseModels.ResponseMessageModel;
import com.gen.com.Insurance_portal.services.IContractService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/contract")
public class ContractController {
    private final IContractService contractService;

    public ContractController(IContractService contractService) {
        this.contractService = contractService;
    }

    @PreAuthorize(value = "hasRole('Contract_List')")
    @Operation(summary = "Required Header { Authorization : bearer key }",security = { @SecurityRequirement(name = "bearer key") })
    @GetMapping
    public ResponseEntity<?> getAllFormat(
            @Parameter(name = "filter", description = "optional field, structure ?filter={code or status},{value} \n" +
                    "-> if status: value in {Active, Expired, Cancelled, Inactive}")
            @RequestParam(required = false) String filter,
            @Parameter(name = "page", description = "optional field, The default page is 1")
            @RequestParam(defaultValue = "1") int page,
            @Parameter(name = "size", description = "optional field, the size of a default page is 10")
            @RequestParam(defaultValue = "10") int size,
            @Parameter(name = "sort", description = "optional field, syntax=field...,{desc/asc}")
            @RequestParam(defaultValue = "id,desc") String[] sort) {

        Object response = contractService.getList(new ParamsModel(filter, page, size, sort), false);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "Required Header { Authorization : bearer key }",security = { @SecurityRequirement(name = "bearer key") })
    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable Long id) {
        Contract contract = contractService.findById(id).orElseThrow(() -> new NotFoundEntityException(id, "Contract"));
        return new ResponseEntity<>(contract, HttpStatus.OK);
    }

    @Operation(summary = "Required Header { Authorization : bearer key }",security = { @SecurityRequirement(name = "bearer key") },
            description = "RequestStatus value in {Active, Expired, Cancelled, Inactive}")
    @PostMapping("/status/{code}")
    public ResponseEntity<?> status(@PathVariable String code, @RequestBody RequestStatus requestStatus) {

        contractService.changeStatus(code, ContractStatus.valueOf(requestStatus.getStatus()));

        return new ResponseEntity<>(new ResponseMessageModel(true), HttpStatus.OK);
    }
}
