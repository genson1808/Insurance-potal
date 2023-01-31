package com.gen.com.Insurance_portal.controllers.user;

import com.gen.com.Insurance_portal.models.responseModels.ResponseObjectModel;
import com.gen.com.Insurance_portal.services.IProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/u/product")
public class UProductController {
    private final IProductService productService;

    public UProductController(IProductService productService) {
        this.productService = productService;
    }

    @PreAuthorize("permitAll()")
    @GetMapping
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(new ResponseObjectModel(true, productService.getList()), HttpStatus.OK);
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/{code}")
    public ResponseEntity<?> getOne(@PathVariable String code) {
        return new ResponseEntity<>(
                new ResponseObjectModel(true, productService.findByCode(code)),
                HttpStatus.OK);
    }

}
