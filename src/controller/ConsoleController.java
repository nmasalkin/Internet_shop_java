package controller;

import java.util.Scanner;

public class ConsoleController {

    private CustomerController customerController = new CustomerController();
    private OrderController orderController = new OrderController();
    private ProductController productController = new ProductController();

    private Scanner scanner = new Scanner(System.in);

    public void menu() {
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
            System.out.println("2. Получить данные заказчика");
            System.out.println("3. Изменить данные заказчика");
            System.out.println("4. Удалить заказчика");
            System.out.println("5. Получить данные всех заказчиков");
            System.out.println("0. Вернутся в главное меню");
            System.out.println("Введите значение:");

            switch (Integer.parseInt(scanner.nextLine())) {
                case 1:
                    customerController.add();
                    break;
                case 2:
                    customerController.get();
                    break;
                case 3:
                    customerController.update();
                    break;
                case 4:
                    customerController.delete();
                    break;
                case 5:
                    customerController.getAll();
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
            System.out.println("2. Получить данные по заказу");
            System.out.println("3. Изменить данные в заказе");
            System.out.println("4. Удалить заказ");
            System.out.println("5. Получить данные по всем заказам");
            System.out.println("0. Вернутся в главное меню");
            System.out.println("Введите значение:");

            switch (Integer.parseInt(scanner.nextLine())) {
                case 1:
                    orderController.add();
                    break;
                case 2:
                    orderController.get();
                    break;
                case 3:
                    orderController.update();
                    break;
                case 4:
                    orderController.delete();
                    break;
                case 5:
                    orderController.getAll();
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
            System.out.println("2. Получить данные по товару");
            System.out.println("3. Изменить данные товара");
            System.out.println("4. Удалить товар");
            System.out.println("5. Получить данные всех товаров");
            System.out.println("0. Вернутся в главное меню");
            System.out.println("Введите значение:");

            switch (Integer.parseInt(scanner.nextLine())) {
                case 1:
                    productController.add();
                    break;
                case 2:
                    productController.get();
                    break;
                case 3:
                    productController.update();
                    break;
                case 4:
                    productController.delete();
                    break;
                case 5:
                    productController.getAll();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("\nНеправильное значение!\n");
            }
        }
    }
}