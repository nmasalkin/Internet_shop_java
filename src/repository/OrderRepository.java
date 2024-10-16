package repository;

import model.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderRepository implements RepositoryInterface<Order> {

    public static List<Order> orders = new ArrayList<Order>();

    private static int id = 1;


    @Override
    public void add(Order order) {
        order.setId(id++);
        orders.add(order);
    }

    @Override
    public Order get(int id) {
        for (Order order : orders) {
            if (order.getId() == id) {
                return order;
            }
        }
        return null;
    }

    @Override
    public void update(int id, Order newOrder) {
        for (Order order : orders) {
            if (order.getId() == id) {
                newOrder.setId(order.getId());
                orders.set(orders.indexOf(order), newOrder);
                break;
            }
        }
    }

    @Override
    public void delete(int id) {
        orders.remove(get(id));
    }

    @Override
    public List<Order> getAll() {
        return orders;
    }
}