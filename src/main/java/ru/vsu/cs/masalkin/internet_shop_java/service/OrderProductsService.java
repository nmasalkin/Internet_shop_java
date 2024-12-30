package ru.vsu.cs.masalkin.internet_shop_java.service;

import ru.vsu.cs.masalkin.internet_shop_java.model.OrderProducts;
import ru.vsu.cs.masalkin.internet_shop_java.repository.OrderProductsRepositoryImpl;

import java.util.List;
import java.util.Optional;

public class OrderProductsService {

    private OrderProductsRepositoryImpl orderProductsRepositoryImpl;

    public OrderProductsService(OrderProductsRepositoryImpl orderProductsRepositoryImpl) {
        this.orderProductsRepositoryImpl = orderProductsRepositoryImpl;
    }

    public OrderProducts add(OrderProducts obj) {
        return orderProductsRepositoryImpl.add(obj);
    }

    public OrderProducts update(OrderProducts obj) {
        return orderProductsRepositoryImpl.update(obj);
    }

    public boolean delete(OrderProducts obj) {
        return orderProductsRepositoryImpl.delete(obj);
    }

    public Optional<OrderProducts> findById(int order_id, int product_id) {
        return orderProductsRepositoryImpl.findById(order_id, product_id);
    }

    public List<OrderProducts> findAllByOrderId(int order_id) {
        return orderProductsRepositoryImpl.findAllByOrderId(order_id);
    }

    public List<OrderProducts> findAll() {
        return orderProductsRepositoryImpl.findAll();
    }
}
