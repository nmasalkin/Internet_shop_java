package controller;

import model.Customer;
import model.Order;
import model.Product;
import service.OrderService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class OrderController {

    private OrderService orderService = new OrderService();

    private Scanner scanner = new Scanner(System.in);

    public void add() {
        System.out.println("Введите ID заказчика:");
        int customerId = Integer.parseInt(scanner.nextLine());
        if (!CustomerController.getCustomerService().getById(customerId).isPresent()) {
            System.out.println("Заказчик с данным ID не найден.");
            return;
        }
        System.out.println("Введите ID товаров:");
        List<Product> products = new ArrayList<>();
        String[] productIds = scanner.nextLine().split(" ");
        for (String productId : productIds) {
            if (ProductController.getProductService().getById(Integer.parseInt(productId)).isPresent()) {
                products.add(ProductController.getProductService().getById(Integer.parseInt(productId)).get());
            } else {
                System.out.println("Товар с ID " + productId + " не  найден.");
                return;
            }
        }
        System.out.println("Создан заказ:\n" + orderService.add(new Order(CustomerController.getCustomerService().getById(customerId).get(), products)));
    }

    public void getById() {
        System.out.println("Введите ID заказа:");
        int id = Integer.parseInt(scanner.nextLine());
        Optional<Order> order = orderService.getById(id);
        if (order.isPresent()) {
            System.out.println(order.get());
        } else {
            System.out.println("Заказ с ID " + id + " не найден.");
        }
    }

    public void update() {
        System.out.println("Введите ID заказа:");
        int id = Integer.parseInt(scanner.nextLine());
        Optional<Order> orderOptional = orderService.getById(id);
        if (!orderOptional.isPresent()) {
            System.out.println("Заказ с ID " + id + " не найден.");
            return;
        }
        System.out.println("Введите ID заказчика:");
        int customerId = Integer.parseInt(scanner.nextLine());
        if (!CustomerController.getCustomerService().getById(customerId).isPresent()) {
            System.out.println("Заказчик с данным ID не найден.");
            return;
        }
        System.out.println("Введите ID товаров:");
        List<Product> products = new ArrayList<>();
        String[] productIds = scanner.nextLine().split(" ");
        for (String productId : productIds) {
            if (ProductController.getProductService().getById(Integer.parseInt(productId)).isPresent()) {
                products.add(ProductController.getProductService().getById(Integer.parseInt(productId)).get());
            } else {
                System.out.println("Товар с ID " + productId + " не  найден.");
                return;
            }
        }
        Order order = new Order(CustomerController.getCustomerService().getById(customerId).get(), products);
        order.setId(id);
        System.out.println("Обновленный заказ:\n" + orderService.update(order));
    }

    public void delete() {
        System.out.println("Введите ID заказа который хотите удалить:");
        int id = Integer.parseInt(scanner.nextLine());
        Optional<Order> orderOptional = orderService.getById(id);
        if (orderOptional.isPresent()) {
            orderService.delete(orderOptional.get());
            System.out.println("Заказ: " + orderOptional.get() + " удален.");
        } else {
            System.out.println("Заказ с ID " + id + " не найден.");
        }
    }

    public void getAll() {
        List<Order> orderList = orderService.getAll();
        if (orderList.isEmpty()) {
            System.out.println("Заказов нет.");
        } else {
            System.out.println("Данные всех заказов:");
            for (Order order : orderService.getAll()) {
                System.out.println(order);
            }
        }
    }
}