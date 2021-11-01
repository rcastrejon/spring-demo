package com.rcastrejon.springdemo.dao;

import java.util.List;
import java.util.Optional;

public interface Dao<T> {
    Optional<T> get(int id);

    List<T> getAll();

    int create(T t);

    int update(int id, T t);

    int delete(int id);
}
