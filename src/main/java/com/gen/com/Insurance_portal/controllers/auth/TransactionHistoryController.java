package com.gen.com.Insurance_portal.controllers.auth;

import com.gen.com.Insurance_portal.models.RequestModels.ParamsModel;
import com.gen.com.Insurance_portal.models.RequestModels.TransactionHistoryModel;
import com.gen.com.Insurance_portal.models.responseModels.ResponseMessageModel;
import com.gen.com.Insurance_portal.models.responseModels.TransactionHistoryResponse;
import com.gen.com.Insurance_portal.services.ITransactionHistoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/transaction-history")
public class TransactionHistoryController {

    private final ITransactionHistoryService transactionHistoryService;

    public TransactionHistoryController(ITransactionHistoryService transactionHistoryService) {
        this.transactionHistoryService = transactionHistoryService;
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid TransactionHistoryModel transactionHistoryModel) {
        TransactionHistoryResponse response = transactionHistoryService.create(transactionHistoryModel);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PreAuthorize(value = "hasRole('HistoryTransaction_list')")
    @Operation(summary = "Required Header { Authorization : bearer key }",security = { @SecurityRequirement(name = "bearer key") })
    @GetMapping
    public ResponseEntity<?> getAllFormat(
            @Parameter(name = "filter", description = "optional field, filter by policyCode")
            @RequestParam(required = false) String filter,
            @Parameter(name = "page", description = "optional field, The default page is 1")
            @RequestParam(defaultValue = "1") int page,
            @Parameter(name = "size", description = "optional field, the size of a default page is 10")
            @RequestParam(defaultValue = "10") int size,
            @Parameter(name = "sort", description = "optional field, syntax=field...,{desc/asc}")
            @RequestParam(defaultValue = "id,desc") String[] sort) {

        Object response = transactionHistoryService.getList(new ParamsModel(filter, page, size, sort), false);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
