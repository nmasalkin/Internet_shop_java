package service;

import model.Order;
import repository.OrderRepositoryImpl;

import java.util.List;
import java.util.Optional;

public class OrderService {

    private OrderRepositoryImpl orderRepositoryImpl;

    public OrderService(OrderRepositoryImpl orderRepositoryImpl) {
        this.orderRepositoryImpl = orderRepositoryImpl;
    }

    public Order add(Order obj) {
        return orderRepositoryImpl.add(obj);
    }

    public Order update(Order obj) {
        return orderRepositoryImpl.update(obj);
    }

    public boolean delete(Order obj) {
        return orderRepositoryImpl.delete(obj);
    }

    public Optional<Order> findById(int id) {
        return orderRepositoryImpl.findById(id);
    }

    public List<Order> findAll() {
        return orderRepositoryImpl.findAll();
    }
}
