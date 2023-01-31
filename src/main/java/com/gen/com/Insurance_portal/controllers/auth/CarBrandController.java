package com.gen.com.Insurance_portal.controllers.auth;

import com.gen.com.Insurance_portal.entites.CarBrand;
import com.gen.com.Insurance_portal.exceptions.NotFoundEntityExceptionByCode;
import com.gen.com.Insurance_portal.models.RequestModels.CarBrandModel;
import com.gen.com.Insurance_portal.models.RequestModels.UpdateCarBrandModel;
import com.gen.com.Insurance_portal.models.responseModels.ResponseMessageModel;
import com.gen.com.Insurance_portal.services.ICarBrandService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/car-brand")
public class CarBrandController {

    private final ICarBrandService carBrandService;

    public CarBrandController(ICarBrandService carBrandService) {
        this.carBrandService = carBrandService;
    }

    @PreAuthorize(value = "permitAll()")
    @GetMapping
    public ResponseEntity<?> getAll(){
        return new ResponseEntity<>(carBrandService.findAll(), HttpStatus.OK);
    }

    @PreAuthorize(value = "hasRole('All_Authorities')")
    @PostMapping
    public ResponseEntity<?> create( @RequestBody @Valid CarBrandModel carBrand){

        carBrandService.create(carBrand);
        return new ResponseEntity<>(new ResponseMessageModel(true), HttpStatus.OK);

    }

    @PreAuthorize(value = "permitAll()")
    @GetMapping("/{code}")
    public ResponseEntity<?> get(@PathVariable String code){
        CarBrand carBrand = carBrandService.findByCarBrandCode(code)
                .orElseThrow(() -> new NotFoundEntityExceptionByCode(code, "CarBrand"));
        return  new ResponseEntity<>(carBrand, HttpStatus.OK);
    }

    @PreAuthorize(value = "hasRole('All_Authorities')")
    @PutMapping("/{code}")
    public ResponseEntity<?> update(@PathVariable String code, @Valid @RequestBody UpdateCarBrandModel carBrand){

        carBrandService.update(carBrand, code);

        return  new ResponseEntity<>(new ResponseMessageModel(true), HttpStatus.OK);
    }

    @PreAuthorize(value = "hasRole('All_Authorities')")
    @DeleteMapping("/{code}")
    public ResponseEntity<?>  delete(@PathVariable String code){
        CarBrand carBrand = carBrandService.findByCarBrandCode(code)
                .orElseThrow(() -> new NotFoundEntityExceptionByCode(code, "CarBrand"));
        carBrandService.delete(carBrand);
        return  new ResponseEntity<>(new ResponseMessageModel(true), HttpStatus.OK);
    }
}
