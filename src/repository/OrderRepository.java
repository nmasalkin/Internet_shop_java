package repository;

import model.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderRepository implements RepositoryInterface<Order> {

    public static List<Order> orders = new ArrayList<Order>();

    private static int id = 1;


    @Override
    public Order add(Order order) {
        order.setId(id++);
        orders.add(order);
        return order;
    }

    @Override
    public Optional<Order> getById(int id) {
        for (Order order : orders) {
            if (order.getId() == id) {
                return Optional.of(order);
            }
        }
        return Optional.empty();
    }

    @Override
    public Order update(Order newOrder) {
        if (newOrder.getId() == 0) {
            return null;
        } else {
            for (Order order : orders) {
                if (order.getId() == newOrder.getId()) {
                    orders.set(orders.indexOf(order), newOrder);
                    return newOrder;
                }
            }
        }
        return null;
    }

    @Override
    public Optional<Order> delete(Order order) {
        if (orders.contains(order)) {
            orders.remove(order);
            return Optional.of(order);
        }
        return Optional.empty();
    }

    @Override
    public List<Order> getAll() {
        return orders;
    }
}