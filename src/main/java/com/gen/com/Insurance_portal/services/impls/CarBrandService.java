package com.gen.com.Insurance_portal.services.impls;

import com.gen.com.Insurance_portal.entites.CarBrand;
import com.gen.com.Insurance_portal.exceptions.MessageException;
import com.gen.com.Insurance_portal.exceptions.NotFoundEntityExceptionByCode;
import com.gen.com.Insurance_portal.models.RequestModels.CarBrandModel;
import com.gen.com.Insurance_portal.models.RequestModels.UpdateCarBrandModel;
import com.gen.com.Insurance_portal.repositories.CarBrandRepository;
import com.gen.com.Insurance_portal.services.ICarBrandService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CarBrandService extends AbstractService<CarBrand> implements ICarBrandService {
    private final CarBrandRepository carBrandRepository;

    public CarBrandService(CarBrandRepository carBrandRepository) {
        super(carBrandRepository);
        this.carBrandRepository = carBrandRepository;
    }

    @Override
    public void create(CarBrandModel carBrandModel) {

        CarBrand carBrand = new CarBrand();

        if(carBrandRepository.existsCarBrandByCarBrandCode(carBrandModel.getCarBrandCode())) {
            throw new MessageException("code already exists");
        }
        carBrand.setCarBrand(carBrandModel.getCarBrand());
        carBrand.setCarBrandCode(carBrandModel.getCarBrandCode());
        carBrand.setModels(carBrandModel.getModels());
        save(carBrand);
    }

    @Override
    public void update(UpdateCarBrandModel carBrandModel, String code) {
        CarBrand carBrand = carBrandRepository.findByCarBrandCode(code)
                .orElseThrow(() -> new NotFoundEntityExceptionByCode(code, "CarBrand"));

        if(carBrandRepository.existsCarBrandByCarBrandCodeAndIdNot(carBrandModel.getCarBrandCode(), carBrand.getId())) {
            throw new MessageException("code already exists");
        }

        if (Strings.isNotEmpty(carBrandModel.getCarBrand())){
            carBrand.setCarBrand(carBrandModel.getCarBrand());
        }
        if (Strings.isNotEmpty(carBrandModel.getCarBrandCode())){
            carBrand.setCarBrandCode(carBrandModel.getCarBrandCode());
        }
        if (Strings.isNotEmpty(carBrandModel.getModels())){
            carBrand.setModels(carBrandModel.getModels());
        }
        update(carBrand);
    }

    @Override
    public Optional<CarBrand> findByCarBrandCode(String code) {
        return carBrandRepository.findByCarBrandCode(code);
    }


}
