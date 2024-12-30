package ru.vsu.cs.masalkin.internet_shop_java.repository;

import java.util.List;
import java.util.Optional;

public interface Repository<T> {

    T add (T obj);
    T update (T obj);
    boolean delete (T obj);
    Optional<T> findById (int id);
    List<T> findAll ();
}
