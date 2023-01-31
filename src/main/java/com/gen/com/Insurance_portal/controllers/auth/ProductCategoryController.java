package com.gen.com.Insurance_portal.controllers.auth;

import com.gen.com.Insurance_portal.entites.ProductCategory;
import com.gen.com.Insurance_portal.models.RequestModels.CreateProductCategoryModel;
import com.gen.com.Insurance_portal.models.responseModels.ResponseMessageModel;
import com.gen.com.Insurance_portal.models.responseModels.ResponseObjectModel;
import com.gen.com.Insurance_portal.services.IProductCategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/product_category")
public class ProductCategoryController {

    private final IProductCategoryService productCategoryService;

    public ProductCategoryController(IProductCategoryService productCategoryService) {
        this.productCategoryService = productCategoryService;
    }

    @Operation(summary = "Required Header { Authorization : bearer key }",security = { @SecurityRequirement(name = "bearer key") })
    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid CreateProductCategoryModel productCategoryModel) {
        productCategoryService.create(productCategoryModel);
        return new ResponseEntity<>(new ResponseMessageModel(true), HttpStatus.OK);
    }

    @Operation(summary = "Required Header { Authorization : bearer key }",security = { @SecurityRequirement(name = "bearer key") })
    @GetMapping
    public ResponseEntity<?> getAll() {
        List<ProductCategory> categories = productCategoryService.findAll();
        return new ResponseEntity<>(new ResponseObjectModel(true, categories), HttpStatus.OK);
    }

    @Operation(summary = "Required Header { Authorization : bearer key }",security = { @SecurityRequirement(name = "bearer key") })
    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id) {
        return new ResponseEntity<>(
                new ResponseObjectModel(true, productCategoryService.findById(id)),
                HttpStatus.OK);
    }

    @Operation(summary = "Required Header { Authorization : bearer key }",security = { @SecurityRequirement(name = "bearer key") })
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody @Valid CreateProductCategoryModel productProviderModel,
                                    @PathVariable Long id) {

        productCategoryService.update(productProviderModel, id);

        return new ResponseEntity<>(new ResponseMessageModel(true), HttpStatus.OK);
    }


    @Operation(summary = "Required Header { Authorization : bearer key }",security = { @SecurityRequirement(name = "bearer key") })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {

        productCategoryService.delete(id);

        return new ResponseEntity<>(new ResponseMessageModel(true), HttpStatus.OK);
    }

}
