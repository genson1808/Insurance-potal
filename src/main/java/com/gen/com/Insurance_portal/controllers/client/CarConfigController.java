package com.gen.com.Insurance_portal.controllers.client;

import com.gen.com.Insurance_portal.entites.CarConfigClient;
import com.gen.com.Insurance_portal.exceptions.NotFoundEntityException;
import com.gen.com.Insurance_portal.models.responseModels.ResponseMessageModel;
import com.gen.com.Insurance_portal.services.ICarConfigClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/configs/car")
public class CarConfigController {
    private final ICarConfigClientService carConfigClientService;

    public CarConfigController(ICarConfigClientService carConfigClientService) {
        this.carConfigClientService = carConfigClientService;
    }


    @PreAuthorize(value = "permitAll()")
    @GetMapping
    public ResponseEntity<?> getAll(){
        return new ResponseEntity<>(carConfigClientService.findAll(), HttpStatus.OK);
    }

    @PreAuthorize(value = "hasRole('All_Authorities')")
    @PostMapping
    public ResponseEntity<?> create( @RequestBody @Valid CarConfigClient carConfigClient){

        carConfigClientService.save(carConfigClient);
        return new ResponseEntity<>(new ResponseMessageModel(true), HttpStatus.OK);

    }

    @PreAuthorize(value = "permitAll()")
    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable Long id){
        CarConfigClient carConfigClient = carConfigClientService.findById(id)
                .orElseThrow(() -> new NotFoundEntityException(id, "CarConfigClient"));
        return  new ResponseEntity<>(carConfigClient, HttpStatus.OK);
    }

    @PreAuthorize(value = "hasRole('All_Authorities')")
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody CarConfigClient carConfigClient){
        CarConfigClient carConfig = carConfigClientService.findById(id)
                .orElseThrow(() -> new NotFoundEntityException(id, "CarConfigClient"));

        carConfig.setCarConfigBannerFile(carConfigClient.getCarConfigBannerFile());
        carConfig.setCarConfigBodyContent(carConfigClient.getCarConfigBodyContent());
        carConfig.setCarConfigHeaderContent(carConfigClient.getCarConfigHeaderContent());

        carConfigClientService.update(carConfig);
        return  new ResponseEntity<>(new ResponseMessageModel(true), HttpStatus.OK);
    }

    @PreAuthorize(value = "hasRole('All_Authorities')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?>  delete(@PathVariable Long id){
        CarConfigClient carConfig = carConfigClientService.findById(id)
                .orElseThrow(() -> new NotFoundEntityException(id, "CarConfigClient"));
        carConfigClientService.delete(carConfig);
        return  new ResponseEntity<>(new ResponseMessageModel(true), HttpStatus.OK);
    }
}
