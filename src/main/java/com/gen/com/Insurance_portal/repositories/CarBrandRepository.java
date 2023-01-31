package com.gen.com.Insurance_portal.repositories;

import com.gen.com.Insurance_portal.entites.CarBrand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarBrandRepository extends JpaRepository<CarBrand, Long> {
    Optional<CarBrand> findByCarBrandCode(String code);
    Boolean existsCarBrandByCarBrandCodeAndIdNot(String code, Long id);
    Boolean existsCarBrandByCarBrandCode(String code);
}
