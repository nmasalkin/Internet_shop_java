package repository;

import java.util.List;
import java.util.Optional;

public interface OrderProductsRepository<T> {

    T add(T obj);

    T update(T obj);

    boolean delete(T obj);

    Optional<T> findById(int order_id, int product_id);

    List<T> findAllByOrderId(int order_id);

    List<T> findAll();
}
