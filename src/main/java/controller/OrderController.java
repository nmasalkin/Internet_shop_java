package controller;

import model.Order;
import model.OrderProducts;
import model.Product;
import service.CustomerService;
import service.OrderProductsService;
import service.OrderService;
import service.ProductService;

import java.util.*;

public class OrderController {

    private OrderService orderService;
    private CustomerService customerService;
    private ProductService productService;
    private OrderProductsService orderProductsService;
    private Scanner scanner;

    public OrderController(OrderService orderService, CustomerService customerService, ProductService productService, OrderProductsService orderProductsService, Scanner scanner) {
        this.orderService = orderService;
        this.customerService = customerService;
        this.productService = productService;
        this.orderProductsService = orderProductsService;
        this.scanner = scanner;
    }

    protected void add() {
        System.out.println("Введите ID заказчика:");
        int customerId = Integer.parseInt(scanner.nextLine());
        if (!customerService.findById(customerId).isPresent()) {
            System.out.println("Заказчик с данным ID не найден.");
            return;
        }

        System.out.println("Введите ID товара и кол-во через пробел(для остановки введите - стоп):");
        List<OrderProducts> orderProductsList = new ArrayList<>();
        String[] input = scanner.nextLine().split(" ");
        Optional<Product> optionalProduct;
        while (!input[0].toLowerCase().equals("стоп")) {
            optionalProduct = productService.findById(Integer.parseInt(input[0]));
            if (optionalProduct.isPresent()) {
                orderProductsList.add(new OrderProducts(0, optionalProduct.get().getId(), optionalProduct.get().getPrice(), Integer.parseInt(input[1])));
            } else {
                System.out.println("Товар с ID " + input[0] + " не  найден.");
                return;
            }
            input = scanner.nextLine().split(" ");
        }

        Order order = orderService.add(new Order(customerId));
        for (OrderProducts orderProducts : orderProductsList) {
            orderProducts.setOrderId(order.getId());
            orderProductsService.add(orderProducts);
        }

        System.out.println("Заказ с ID = " + order.getId() + " создан.");
    }

    protected void update() {
        System.out.println("Введите ID заказа:");
        int id = Integer.parseInt(scanner.nextLine());
        Optional<Order> orderOptional = orderService.findById(id);
        if (!orderOptional.isPresent()) {
            System.out.println("Заказ с ID " + id + " не найден.");
            return;
        }

        System.out.println("Введите ID заказчика:");
        int customerId = Integer.parseInt(scanner.nextLine());
        if (!customerService.findById(customerId).isPresent()) {
            System.out.println("Заказчик с данным ID не найден.");
            return;
        }

        System.out.println("Введите ID товара, цену и кол-во через пробел(для остановки введите - стоп):");
        List<OrderProducts> orderProductsList = new ArrayList<>();
        String[] input = scanner.nextLine().split(" ");
        Optional<Product> optionalProduct;
        while (!input[0].toLowerCase().equals("стоп")) {
            optionalProduct = productService.findById(Integer.parseInt(input[0]));
            if (optionalProduct.isPresent()) {
                orderProductsList.add(new OrderProducts(0, Integer.parseInt(input[0]), Integer.parseInt(input[1]), Integer.parseInt(input[2])));
            } else {
                System.out.println("Товар с ID " + input[0] + " не  найден.");
                return;
            }
            input = scanner.nextLine().split(" ");
        }

        for (OrderProducts orderProducts : orderProductsService.findAllByOrderId(id)) {
            orderProductsService.delete(orderProducts);
        }

        Order order = new Order(customerId);
        order.setId(id);
        orderService.update(order);
        for (OrderProducts orderProducts : orderProductsList) {
            orderProducts.setOrderId(order.getId());
            orderProductsService.add(orderProducts);
        }

        System.out.println("Заказ с ID = " + order.getId() + " изменен.");
    }

    protected void delete() {
        System.out.println("Введите ID заказа который хотите удалить:");
        int id = Integer.parseInt(scanner.nextLine());
        Optional<Order> orderOptional = orderService.findById(id);
        if (orderOptional.isPresent()) {
            for (OrderProducts orderProducts : orderProductsService.findAllByOrderId(id)) {
                orderProductsService.delete(orderProducts);
            }

            orderService.delete(orderOptional.get());

            System.out.println("Заказ с ID = " + id + " удален.");
        } else {
            System.out.println("Заказ с ID " + id + " не найден.");
        }
    }

    protected void findById() {
        System.out.println("Введите ID заказа:");
        int id = Integer.parseInt(scanner.nextLine());
        Optional<Order> order = orderService.findById(id);
        if (order.isPresent()) {
            System.out.println(order.get());
            for (OrderProducts orderProducts : orderProductsService.findAllByOrderId(id)) {
                System.out.println("    " + orderProducts);
            }
        } else {
            System.out.println("Заказ с ID " + id + " не найден.");
        }
    }

    protected void findAll() {
        List<Order> orderList = orderService.findAll();
        if (orderList.isEmpty()) {
            System.out.println("Заказов нет.");
        } else {
            System.out.println("Данные всех заказов:");
            for (Order order : orderService.findAll()) {
                System.out.println(order);
                for (OrderProducts orderProducts : orderProductsService.findAllByOrderId(order.getId())) {
                    System.out.println("    " + orderProducts);
                }
            }
        }
    }
}
