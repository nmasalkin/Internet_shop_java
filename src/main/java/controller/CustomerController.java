package controller;

import model.Customer;
import service.CustomerService;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class CustomerController {

    private CustomerService customerService;
    private Scanner scanner;

    public CustomerController(CustomerService customerService, Scanner scanner) {
        this.customerService = customerService;
        this.scanner = scanner;
    }

    protected void add() {
        System.out.println("Введите ФИО:");
        String fullName = scanner.nextLine();
        System.out.println("Введите адрес:");
        String address = scanner.nextLine();
        System.out.println("Введите номер телефона:");
        String phone = scanner.nextLine();
        System.out.println("Заказчик: " + customerService.add(new Customer(fullName, address, phone)) + " добавлен.");
    }

    protected void update() {
        System.out.println("Введите ID заказчика данные которого хотите изменить:");
        int id = Integer.parseInt(scanner.nextLine());
        Optional<Customer> customerOptional = customerService.findById(id);
        if (customerOptional.isPresent()) {
            System.out.println("Введите ФИО:");
            String fullName = scanner.nextLine();
            System.out.println("Введите адрес:");
            String address = scanner.nextLine();
            System.out.println("Введите номер телефона:");
            String phone = scanner.nextLine();
            Customer customer = new Customer(fullName, address, phone);
            customer.setId(id);
            customerService.update(customer);
            System.out.println("Обновленные данные заказчика: " + customerService.findById(id).get());
        } else {
            System.out.println("Заказчик с ID " + id + " не найден.");
        }
    }

    protected void delete() {
        System.out.println("Введите ID заказчика которого хотите удалить:");
        int id = Integer.parseInt(scanner.nextLine());
        Optional<Customer> customerOptional = customerService.findById(id);
        if (customerOptional.isPresent()) {
            if (customerService.delete(customerOptional.get())){
                System.out.println("Заказчик: " + customerOptional.get() + " удален.");
            } else {
                System.out.println("Заказчик: " + customerOptional.get() + " не удален.");
            }
        } else {
            System.out.println("Заказчик с ID " + id + " не найден.");
        }
    }

    protected void findById() {
        System.out.println("Введите ID заказчика:");
        int id = Integer.parseInt(scanner.nextLine());
        Optional<Customer> customerOptional = customerService.findById(id);
        if (customerOptional.isPresent()) {
            System.out.println(customerOptional.get());
        } else {
            System.out.println("Заказчик с ID " + id + " не найден.");
        }
    }

    protected void findAll() {
        List<Customer> customers = customerService.findAll();
        if (customers.isEmpty()) {
            System.out.println("Заказчиков нет.");
        } else {
            System.out.println("Данные всех заказчиков:");
            for (Customer customer : customers) {
                System.out.println(customer);
            }
        }
    }
}
