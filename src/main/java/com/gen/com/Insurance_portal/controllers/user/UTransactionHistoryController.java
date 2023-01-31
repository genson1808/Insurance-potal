package com.gen.com.Insurance_portal.controllers.user;

import com.gen.com.Insurance_portal.models.RequestModels.ParamsModel;
import com.gen.com.Insurance_portal.services.ITransactionHistoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/u/transaction-history")
public class UTransactionHistoryController {

    private final ITransactionHistoryService transactionHistoryService;

    public UTransactionHistoryController(ITransactionHistoryService transactionHistoryService) {
        this.transactionHistoryService = transactionHistoryService;
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

        Object response = transactionHistoryService.UGetList(new ParamsModel(filter, page, size, sort), token);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

//    @Operation(summary = "Required Header { Authorization : bearer key }",security = { @SecurityRequirement(name = "bearer key") })
//    @GetMapping("/{code}")
//    public ResponseEntity<?> getOne(@PathVariable String code) {
//        return new ResponseEntity<>(
//                new ResponseObjectModel(true, transactionHistoryService.findByTransactionCode(code)),
//                HttpStatus.OK);
//    }
//
//    @Operation(summary = "Required Header { Authorization : bearer key }",security = { @SecurityRequirement(name = "bearer key") })
//    @GetMapping("/{id}")
//    public ResponseEntity<?> getOneById(@PathVariable Long id) {
//        return new ResponseEntity<>(
//                new ResponseObjectModel(true, transactionHistoryService.findById(id)
//                        .orElseThrow(() -> new NotFoundEntityException(id, "TransactionHistory"))),
//                HttpStatus.OK);
//    }
}