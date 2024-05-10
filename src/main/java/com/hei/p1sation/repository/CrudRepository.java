package com.hei.p1sation.repository;


import java.util.List;

public interface CrudRepository<T> {
    T save(T toSave);
    T getById(String id);
    void deleteById(String id);
    List<T> findAll();

}