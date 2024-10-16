package controller;

import model.Product;
import service.ProductService;

import java.util.Scanner;

public class ProductController {

    private static ProductService productService = new ProductService();

    private Scanner scanner = new Scanner(System.in);

    protected static ProductService getProductService() {
        return productService;
    }

    public void add() {
        System.out.println("Введите название товара:");
        String name = scanner.nextLine();
        System.out.println("Введите цену:");
        double price = Double.parseDouble(scanner.nextLine());
        System.out.println("Введите описание:");
        String description = scanner.nextLine();
        productService.add(new Product(name, price, description));
    }

    public void get() {
        System.out.println("Введите ID товара:");
        int id = Integer.parseInt(scanner.nextLine());
        productService.get(id);
    }

    public void update() {
        System.out.println("Введите ID товара который хотите изменить:");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.println("Введите название товара:");
        String name = scanner.nextLine();
        System.out.println("Введите цену:");
        double price = Double.parseDouble(scanner.nextLine());
        System.out.println("Введите описание:");
        String description = scanner.nextLine();
        productService.update(id, new Product(name, price, description));
    }

    public void delete() {
        System.out.println("Введите ID товара который хотите удалить:");
        productService.delete(Integer.parseInt(scanner.nextLine()));
    }

    public void getAll() {
        System.out.println("Данные всех товаров:");
        for (Product product : productService.getAll()) {
            System.out.println(product);
        }
    }
}