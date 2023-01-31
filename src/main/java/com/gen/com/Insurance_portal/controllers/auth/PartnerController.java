package com.gen.com.Insurance_portal.controllers.auth;

import com.gen.com.Insurance_portal.entites.Partner;
import com.gen.com.Insurance_portal.models.RequestModels.CreatePartnerModel;
import com.gen.com.Insurance_portal.models.RequestModels.PartnerStatusRequest;
import com.gen.com.Insurance_portal.models.RequestModels.UpdateProductProviderModel;
import com.gen.com.Insurance_portal.models.responseModels.ResponseMessageModel;
import com.gen.com.Insurance_portal.models.responseModels.ResponseObjectModel;
import com.gen.com.Insurance_portal.services.IPartnerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/partner")
public class PartnerController {

    private final IPartnerService partnerService;

    public PartnerController(IPartnerService partnerService) {
        this.partnerService = partnerService;
    }

    @PreAuthorize(value = "hasRole('Partner_Create')")
    @Operation(summary = "Required Header { Authorization : bearer key }",security = { @SecurityRequirement(name = "bearer key") })
    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid CreatePartnerModel productProviderModel) {
        partnerService.save(productProviderModel);
        return new ResponseEntity<>(new ResponseMessageModel(true), HttpStatus.OK);
    }

    @PreAuthorize(value = "hasRole('Partner_List')")
    @Operation(summary = "Required Header { Authorization : bearer key }",security = { @SecurityRequirement(name = "bearer key") })
    @GetMapping
    public ResponseEntity<?> getAll() {
        List<Partner> providers = partnerService.findAll();
        return new ResponseEntity<>(new ResponseObjectModel(true, providers), HttpStatus.OK);
    }

    @PreAuthorize(value = "hasRole('Partner_Detail')")
    @Operation(summary = "Required Header { Authorization : bearer key }",security = { @SecurityRequirement(name = "bearer key") })
    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id) {
        return new ResponseEntity<>(
                new ResponseObjectModel(true, partnerService.findById(id)),
                HttpStatus.OK);
    }

    @PreAuthorize(value = "hasRole('Partner_Edit')")
    @Operation(summary = "Required Header { Authorization : bearer key }",security = { @SecurityRequirement(name = "bearer key") })
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody @Valid UpdateProductProviderModel productProviderModel,
                                    @PathVariable Long id) {

        partnerService.update(productProviderModel, id);

        return new ResponseEntity<>(new ResponseMessageModel(true), HttpStatus.OK);
    }


//    @PreAuthorize(value = "hasRole('Partner_Edit')")
//    @Operation(summary = "Required Header { Authorization : bearer key }",security = { @SecurityRequirement(name = "bearer key") })
//    @DeleteMapping("/{id}")
//    public ResponseEntity<?> delete(@PathVariable Long id) {
//
//        partnerService.delete(id);
//
//        return new ResponseEntity<>(new ResponseMessageModel(true), HttpStatus.OK);
//    }


    @PreAuthorize(value = "hasRole('Partner_Status')")
    @Operation(summary = "Required Header { Authorization : bearer key }",security = { @SecurityRequirement(name = "bearer key") })
    @PostMapping("/active/{id}")
    public ResponseEntity<?> active(@PathVariable Long id) {

        partnerService.active(id);

        return new ResponseEntity<>(new ResponseMessageModel(true), HttpStatus.OK);
    }

    @PreAuthorize(value = "hasRole('Partner_Status')")
    @Operation(summary = "Required Header { Authorization : bearer key }",security = { @SecurityRequirement(name = "bearer key") })
    @PostMapping("/partnerStatus/{id}")
    public ResponseEntity<?> status(@PathVariable Long id, @RequestBody PartnerStatusRequest statusRequest) {

        partnerService.status(id, statusRequest);

        return new ResponseEntity<>(new ResponseMessageModel(true), HttpStatus.OK);
    }


}
