package com.gen.com.Insurance_portal.services;

import com.gen.com.Insurance_portal.entites.CarBrand;
import com.gen.com.Insurance_portal.models.RequestModels.CarBrandModel;
import com.gen.com.Insurance_portal.models.RequestModels.UpdateCarBrandModel;

import java.util.Optional;

public interface ICarBrandService extends IAbstractService<CarBrand> {
    void create(CarBrandModel carBrandModel);
    void update(UpdateCarBrandModel carBrandModel, String code);
    Optional<CarBrand> findByCarBrandCode(String code);

}
