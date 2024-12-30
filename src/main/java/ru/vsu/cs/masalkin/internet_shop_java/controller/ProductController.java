package ru.vsu.cs.masalkin.internet_shop_java.controller;

import ru.vsu.cs.masalkin.internet_shop_java.model.Product;
import ru.vsu.cs.masalkin.internet_shop_java.service.ProductService;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class ProductController {

    private ProductService productService;
    private Scanner scanner;

    public ProductController(ProductService productService, Scanner scanner) {
        this.productService = productService;
        this.scanner = scanner;
    }

    protected void add() {
        System.out.println("Введите название товара:");
        String title = scanner.nextLine();
        System.out.println("Введите цену:");
        int price = Integer.parseInt(scanner.nextLine());
        System.out.println("Введите описание:");
        String description = scanner.nextLine();
        System.out.println("Товар: " + productService.add(new Product(title, price, description)) + " добавлен.");
    }

    protected void update() {
        System.out.println("Введите ID товара который хотите изменить:");
        int id = Integer.parseInt(scanner.nextLine());
        Optional<Product> productOptional = productService.findById(id);
        if (productOptional.isPresent()) {
            System.out.println("Введите название товара:");
            String name = scanner.nextLine();
            System.out.println("Введите цену:");
            int price = Integer.parseInt(scanner.nextLine());
            System.out.println("Введите описание:");
            String description = scanner.nextLine();
            Product product = new Product(name, price, description);
            product.setId(id);
            System.out.println("Обновленный товар: " + productService.update(product));
        } else {
            System.out.println("Товар с ID " + id + " не найден.");
        }
    }

    protected void delete() {
        System.out.println("Введите ID товара который хотите удалить:");
        int id = Integer.parseInt(scanner.nextLine());
        Optional<Product> productOptional = productService.findById(id);
        if (productOptional.isPresent()) {
            if (productService.delete(productOptional.get())){
                System.out.println("Товар: " + productOptional.get() + " удален.");
            } else {
                System.out.println("Товар: " + productOptional.get() + " не удален.");
            }
        } else {
            System.out.println("Товар с ID " + id + " не найден.");
        }
    }

    protected void findById() {
        System.out.println("Введите ID товара:");
        int id = Integer.parseInt(scanner.nextLine());
        Optional<Product> productOptional = productService.findById(id);
        if (productOptional.isPresent()) {
            System.out.println(productOptional.get());
        } else {
            System.out.println("Товар с ID " + id + " не найден.");
        }
    }

    protected void findAll() {
        List<Product> productList = productService.findAll();
        if (productList.isEmpty()) {
            System.out.println("Товаров нет.");
        } else {
            System.out.println("Данные всех товаров:");
            for (Product product : productService.findAll()) {
                System.out.println(product);
            }
        }
    }
}
