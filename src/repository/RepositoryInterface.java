package repository;

import java.util.List;

public interface RepositoryInterface<T> {

    void add(T object);

    T get(int id);

    void update(int id, T object);

    void delete(int id);

    List<T> getAll();
}