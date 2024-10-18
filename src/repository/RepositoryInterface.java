package repository;

import java.util.List;
import java.util.Optional;

public interface RepositoryInterface<T> {

    T add(T object);

    Optional<T> getById(int id);

    T update(T object);

    Optional<T> delete(T object);

    List<T> getAll();
}