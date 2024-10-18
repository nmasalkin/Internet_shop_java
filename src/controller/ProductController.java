package controller;

import model.Customer;
import model.Product;
import service.ProductService;

import java.util.List;
import java.util.Optional;
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
        System.out.println("Товар: " + productService.add(new Product(name, price, description)) + " добавлен.");
    }

    public void getById() {
        System.out.println("Введите ID товара:");
        int id = Integer.parseInt(scanner.nextLine());
        Optional<Product> productOptional = productService.getById(id);
        if (productOptional.isPresent()) {
            System.out.println(productOptional.get());
        } else {
            System.out.println("Товар с ID " + id + " не найден.");
        }
    }

    public void update() {
        System.out.println("Введите ID товара который хотите изменить:");
        int id = Integer.parseInt(scanner.nextLine());
        Optional<Product> productOptional = productService.getById(id);
        if (productOptional.isPresent()) {
            System.out.println("Введите название товара:");
            String name = scanner.nextLine();
            System.out.println("Введите цену:");
            double price = Double.parseDouble(scanner.nextLine());
            System.out.println("Введите описание:");
            String description = scanner.nextLine();
            Product product = new Product(name, price, description);
            product.setId(id);
            System.out.println("Обновленный товар: " + productService.update(product));
        } else {
            System.out.println("Товар с ID " + id + " не найден.");
        }
    }

    public void delete() {
        System.out.println("Введите ID товара который хотите удалить:");
        int id = Integer.parseInt(scanner.nextLine());
        Optional<Product> productOptional = productService.getById(id);
        if (productOptional.isPresent()) {
            productService.delete(productOptional.get());
            System.out.println("Товар: " + productOptional.get() + " удален.");
        } else {
            System.out.println("Товар с ID " + id + " не найден.");
        }
    }

    public void getAll() {
        List<Product> productList = productService.getAll();
        if (productList.isEmpty()) {
            System.out.println("Товаров нет.");
        } else {
            System.out.println("Данные всех товаров:");
            for (Product product : productService.getAll()) {
                System.out.println(product);
            }
        }
    }
}