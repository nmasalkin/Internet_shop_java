package controller;

import model.Order;
import model.Product;
import service.OrderService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OrderController {

    private OrderService orderService = new OrderService();

    private Scanner scanner = new Scanner(System.in);

    public void add() {
        System.out.println("Введите ID заказчика:");
        int customerId = Integer.parseInt(scanner.nextLine());
        System.out.println("Введите ID товаров:");
        List<Product> products = new ArrayList<>();
        String[] productIds = scanner.nextLine().split(" ");
        for (String productId : productIds) {
            products.add(ProductController.getProductService().get(Integer.parseInt(productId)));
        }
        orderService.add(new Order(CustomerController.getCustomerService().get(customerId), products));
    }

    public void get() {
        System.out.println("Введите ID заказа:");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.println(orderService.get(id));
    }

    public void update() {
        System.out.println("Введите ID заказа:");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.println("Введите ID заказчика:");
        int customerId = Integer.parseInt(scanner.nextLine());
        System.out.println("Введите ID товаров(разделяя пробелом):");
        List<Product> products = new ArrayList<>();
        String[] productIds = scanner.nextLine().split(" ");
        for (String productId : productIds) {
            products.add(ProductController.getProductService().get(Integer.parseInt(productId)));
        }
        orderService.update(id, new Order(CustomerController.getCustomerService().get(customerId), products));
    }

    public void delete() {
        System.out.println("Введите ID заказа который хотите удалить:");
        orderService.delete(Integer.parseInt(scanner.nextLine()));
    }

    public void getAll() {
        System.out.println("Данные всех заказов:");
        for (Order order : orderService.getAll()) {
            System.out.println(order);
        }
    }
}