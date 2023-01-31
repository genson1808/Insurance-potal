package com.gen.com.Insurance_portal.services.impls;

import com.gen.com.Insurance_portal.entites.CarConfigClient;
import com.gen.com.Insurance_portal.repositories.CarConfigClientRepository;
import com.gen.com.Insurance_portal.services.ICarConfigClientService;
import org.springframework.stereotype.Service;

@Service
public class CarConfigClientService extends AbstractService<CarConfigClient> implements ICarConfigClientService {
    private final CarConfigClientRepository carConfigClientRepository;

    public CarConfigClientService(CarConfigClientRepository carConfigClientRepository) {
        super(carConfigClientRepository);
        this.carConfigClientRepository = carConfigClientRepository;
    }
}
