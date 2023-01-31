package com.gen.com.Insurance_portal.repositories;

import com.gen.com.Insurance_portal.common.enums.ProductStatus;
import com.gen.com.Insurance_portal.entites.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByCode (String code);
    Boolean existsByNameOrCode(String name, String code);
    Boolean existsByNameOrCodeAndIdNot(String name, String code, Long id);
    Boolean existsByCodeAndIdIsNot(String code, Long id);
    List<Product> findProductsByProductStatus(ProductStatus status);
}
