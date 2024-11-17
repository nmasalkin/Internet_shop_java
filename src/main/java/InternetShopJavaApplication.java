import controller.ConsoleController;
import controller.CustomerController;
import controller.OrderController;
import controller.ProductController;
import repository.CustomerRepositoryImpl;
import repository.OrderProductsRepositoryImpl;
import repository.OrderRepositoryImpl;
import repository.ProductRepositoryImpl;
import service.CustomerService;
import service.OrderProductsService;
import service.OrderService;
import service.ProductService;

import java.sql.SQLException;
import java.util.Scanner;

public class InternetShopJavaApplication {

    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        CustomerRepositoryImpl customerRepositoryImpl = new CustomerRepositoryImpl();
        CustomerService customerService = new CustomerService(customerRepositoryImpl);
        CustomerController customerController = new CustomerController(customerService, scanner);

        ProductRepositoryImpl productRepositoryImpl = new ProductRepositoryImpl();
        ProductService productService = new ProductService(productRepositoryImpl);
        ProductController productController = new ProductController(productService, scanner);

        OrderProductsRepositoryImpl orderProductsRepositoryImpl = new OrderProductsRepositoryImpl();
        OrderProductsService orderProductsService = new OrderProductsService(orderProductsRepositoryImpl);

        OrderRepositoryImpl orderRepositoryImpl = new OrderRepositoryImpl();
        OrderService orderService = new OrderService(orderRepositoryImpl);
        OrderController orderController = new OrderController(orderService, customerService, productService, orderProductsService, scanner);

        ConsoleController consoleController = new ConsoleController(customerController, orderController, productController, scanner);

        consoleController.start();
    }
}
