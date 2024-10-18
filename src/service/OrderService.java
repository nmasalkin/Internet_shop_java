package service;

import model.Order;
import repository.OrderRepository;

import java.util.List;
import java.util.Optional;

public class OrderService {

    private OrderRepository orderRepository = new OrderRepository();

    public Order add(Order order){
        return orderRepository.add(order);
    }

    public Optional<Order> getById(int id){
        return orderRepository.getById(id);
    }

    public Order update(Order newOrder){
        return orderRepository.update(newOrder);
    }

    public Optional<Order> delete(Order order){
         return orderRepository.delete(order);
    }

    public List<Order> getAll(){
        return orderRepository.getAll();
    }
}