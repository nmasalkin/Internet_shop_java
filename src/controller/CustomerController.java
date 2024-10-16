package controller;

import model.Customer;
import service.CustomerService;

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
        customerService.add(new Customer(fullName, address, phone));
    }

    public void get() {
        System.out.println("Введите ID заказчика:");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.println(customerService.get(id));
    }

    public void update() {
        System.out.println("Введите ID заказчика данные которого хотите изменить:");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.println("Введите ФИО:");
        String fullName = scanner.nextLine();
        System.out.println("Введите адрес:");
        String address = scanner.nextLine();
        System.out.println("Введите телефон:");
        String phone = scanner.nextLine();
        customerService.update(id, new Customer(fullName, address, phone));
    }

    public void delete() {
        System.out.println("Введите ID заказчика которого хотите удалить:");
        customerService.delete(Integer.parseInt(scanner.nextLine()));
    }

    public void getAll() {
        System.out.println("Данные всех заказчиков:");
        for (Customer customer : customerService.getAll()) {
            System.out.println(customer);
        }
    }
}