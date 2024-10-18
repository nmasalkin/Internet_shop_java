package controller;

import model.Customer;
import service.CustomerService;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class CustomerController {

    private static CustomerService customerService = new CustomerService();

    private Scanner scanner = new Scanner(System.in);

    protected static CustomerService getCustomerService() {
        return customerService;
    }

    public void add() {
        System.out.println("Введите ФИО:");
        String fullName = scanner.nextLine();
        System.out.println("Введите адрес:");
        String address = scanner.nextLine();
        System.out.println("Введите телефон:");
        String phone = scanner.nextLine();
        System.out.println("Заказчик: " + customerService.add(new Customer(fullName, address, phone)) + " добавлен.");
    }

    public void getById() {
        System.out.println("Введите ID заказчика:");
        int id = Integer.parseInt(scanner.nextLine());
        Optional<Customer> customerOptional = customerService.getById(id);
        if (customerOptional.isPresent()) {
            System.out.println(customerOptional.get());
        } else {
            System.out.println("Заказчик с ID " + id + " не найден.");
        }
    }

    public void update() {
        System.out.println("Введите ID заказчика данные которого хотите изменить:");
        int id = Integer.parseInt(scanner.nextLine());
        Optional<Customer> customerOptional = customerService.getById(id);
        if (customerOptional.isPresent()) {
            System.out.println("Введите ФИО:");
            String fullName = scanner.nextLine();
            System.out.println("Введите адрес:");
            String address = scanner.nextLine();
            System.out.println("Введите телефон:");
            String phone = scanner.nextLine();
            Customer customer = new Customer(fullName, address, phone);
            customer.setId(id);
            customerService.update(customer);
            System.out.println("Обновленные данные заказчика: " + customerService.getById(id).get());
        } else {
            System.out.println("Заказчик с ID " + id + " не найден.");
        }
    }

    public void delete() {
        System.out.println("Введите ID заказчика которого хотите удалить:");
        int id = Integer.parseInt(scanner.nextLine());
        Optional<Customer> customerOptional = customerService.getById(id);
        if (customerOptional.isPresent()) {
            customerService.delete(customerOptional.get());
            System.out.println("Заказчик: " + customerOptional.get() + " удален.");
        } else {
            System.out.println("Заказчик с ID " + id + " не найден.");
        }
    }

    public void getAll() {
        List<Customer> customers = customerService.getAll();
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