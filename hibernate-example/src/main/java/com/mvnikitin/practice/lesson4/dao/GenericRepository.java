package com.mvnikitin.practice.lesson4.dao;

import java.util.List;

public interface GenericRepository<T, E> {

    E save(E entity);

    List<E> saveAll(List<E> entities);

    void deleteById(T id);

    void deleteAll();

    E findById(T id);

    List<E> findAll();
}
