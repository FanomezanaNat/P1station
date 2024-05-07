package com.hei.p1sation.repository;

import java.util.List;

public interface CrudRepository<T> {
    T save(T toSave);
    T findByName(String name);
    void deleteByName(String name);
    List<T> findAll();

}