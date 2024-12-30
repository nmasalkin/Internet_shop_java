package ru.vsu.cs.masalkin.internet_shop_java.controller;

import java.util.Scanner;

public class ConsoleController {

    private CustomerController customerController;
    private OrderController orderController;
    private ProductController productController;
    private Scanner scanner;

    public ConsoleController(CustomerController customerController, OrderController orderController, ProductController productController, Scanner scanner) {
        this.customerController = customerController;
        this.orderController = orderController;
        this.productController = productController;
        this.scanner = scanner;
    }

    public void start() {
        while (true) {
            System.out.println("--------- Меню ---------");
            System.out.println("1. Управление заказчиками");
            System.out.println("2. Управление заказами");
            System.out.println("3. Управление продуктами");
            System.out.println("0. Выйти");
            System.out.println("Введи значение:");

            switch (Integer.parseInt(scanner.nextLine())) {
                case 1:
                    customerMenu();
                    break;
                case 2:
                    orderMenu();
                    break;
                case 3:
                    productMenu();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("\nНеправильное значение!\n");
            }
        }
    }

    private void customerMenu() {
        while (true) {
            System.out.println("----- Управление заказчиками -----");
            System.out.println("1. Добавить нового заказчика");
            System.out.println("2. Изменить данные заказчика");
            System.out.println("3. Удалить заказчика");
            System.out.println("4. Получить данные заказчика");
            System.out.println("5. Получить данные всех заказчиков");
            System.out.println("0. Вернутся в главное меню");
            System.out.println("Введите значение:");

            switch (Integer.parseInt(scanner.nextLine())) {
                case 1:
                    customerController.add();
                    break;
                case 2:
                    customerController.update();
                    break;
                case 3:
                    customerController.delete();
                    break;
                case 4:
                    customerController.findById();
                    break;
                case 5:
                    customerController.findAll();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("\nНеправильное значение!\n");
            }
        }
    }

    private void orderMenu() {
        while (true) {
            System.out.println("----- Управление заказами -----");
            System.out.println("1. Добавить новый заказ");
            System.out.println("2. Изменить данные в заказе");
            System.out.println("3. Удалить заказ");
            System.out.println("4. Получить данные по заказу");
            System.out.println("5. Получить данные по всем заказам");
            System.out.println("0. Вернутся в главное меню");
            System.out.println("Введите значение:");

            switch (Integer.parseInt(scanner.nextLine())) {
                case 1:
                    orderController.add();
                    break;
                case 2:
                    orderController.update();
                    break;
                case 3:
                    orderController.delete();
                    break;
                case 4:
                    orderController.findById();
                    break;
                case 5:
                    orderController.findAll();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("\nНеправильное значение!\n");
            }
        }
    }

    private void productMenu() {
        while (true) {
            System.out.println("---- Управление товарами ----");
            System.out.println("1. Добавить новый товар");
            System.out.println("2. Изменить данные товара");
            System.out.println("3. Удалить товар");
            System.out.println("4. Получить данные по товару");
            System.out.println("5. Получить данные всех товаров");
            System.out.println("0. Вернутся в главное меню");
            System.out.println("Введите значение:");

            switch (Integer.parseInt(scanner.nextLine())) {
                case 1:
                    productController.add();
                    break;
                case 2:
                    productController.update();
                    break;
                case 3:
                    productController.delete();
                    break;
                case 4:
                    productController.findById();
                    break;
                case 5:
                    productController.findAll();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("\nНеправильное значение!\n");
            }
        }
    }
}
