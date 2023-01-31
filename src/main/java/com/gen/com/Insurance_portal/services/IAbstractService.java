package com.gen.com.Insurance_portal.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

public interface IAbstractService<T> {
    T save(T entity);
    List<T> saveAll(Iterable<T> iterable);
    T update(T entity);
    List<T> findAll();
    List<T> findAll(Sort sort);
    Optional<T> findById(Long id);
    void delete(Long id);
    void delete(T entity);
    Page<T> findAll(Pageable pageable);
}
