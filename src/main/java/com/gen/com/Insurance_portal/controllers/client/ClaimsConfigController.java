package com.gen.com.Insurance_portal.controllers.client;

import com.gen.com.Insurance_portal.entites.ClaimsConfigClient;
import com.gen.com.Insurance_portal.exceptions.NotFoundEntityException;
import com.gen.com.Insurance_portal.models.responseModels.ResponseMessageModel;
import com.gen.com.Insurance_portal.services.IClaimsConfigClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/configs/claim")
public class ClaimsConfigController {

    private final IClaimsConfigClientService claimsConfigClientService;

    public ClaimsConfigController(IClaimsConfigClientService claimsConfigClientService) {
        this.claimsConfigClientService = claimsConfigClientService;
    }

    @PreAuthorize(value = "permitAll()")
    @GetMapping
    public ResponseEntity<?> getAll(){
        return new ResponseEntity<>(claimsConfigClientService.findAll(), HttpStatus.OK);
    }

    @PreAuthorize(value = "hasRole('All_Authorities')")
    @PostMapping
    public ResponseEntity<?> create( @RequestBody @Valid ClaimsConfigClient claimsConfig){

        claimsConfigClientService.save(claimsConfig);
        return new ResponseEntity<>(new ResponseMessageModel(true), HttpStatus.OK);

    }

    @PreAuthorize(value = "permitAll()")
    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable Long id){
        ClaimsConfigClient claimsConfig = claimsConfigClientService.findById(id)
                .orElseThrow(() -> new NotFoundEntityException(id, "ClaimsConfigClient"));
        return  new ResponseEntity<>(claimsConfig, HttpStatus.OK);
    }

    @PreAuthorize(value = "hasRole('All_Authorities')")
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody ClaimsConfigClient claimsConfigRq){
        ClaimsConfigClient claimsConfig = claimsConfigClientService.findById(id)
                .orElseThrow(() -> new NotFoundEntityException(id, "ClaimsConfigClient"));

        claimsConfig.setClaimConfigFormBanner(claimsConfigRq.getClaimConfigFormBanner());
        claimsConfig.setClaimConfigFormBody(claimsConfigRq.getClaimConfigFormBody());
        claimsConfig.setClaimConfigFormHeader(claimsConfigRq.getClaimConfigFormHeader());

        claimsConfigClientService.update(claimsConfig);
        return  new ResponseEntity<>(new ResponseMessageModel(true), HttpStatus.OK);
    }

    @PreAuthorize(value = "hasRole('All_Authorities')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?>  delete(@PathVariable Long id){
        ClaimsConfigClient claimsConfig = claimsConfigClientService.findById(id)
                .orElseThrow(() -> new NotFoundEntityException(id, "ClaimsConfigClient"));
        claimsConfigClientService.delete(claimsConfig);
        return  new ResponseEntity<>(new ResponseMessageModel(true), HttpStatus.OK);
    }
}
