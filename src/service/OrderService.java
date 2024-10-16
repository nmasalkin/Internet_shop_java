package service;

import model.Order;
import repository.OrderRepository;

import java.util.List;

public class OrderService {

    private OrderRepository orderRepository = new OrderRepository();

    public void add(Order order){
        orderRepository.add(order);
    }

    public Order get(int id){
        return orderRepository.get(id);
    }

    public void update(int id, Order newOrder){
        orderRepository.update(id, newOrder);
    }

    public void delete(int id){
        orderRepository.delete(id);
    }

    public List<Order> getAll(){
        return orderRepository.getAll();
    }
}