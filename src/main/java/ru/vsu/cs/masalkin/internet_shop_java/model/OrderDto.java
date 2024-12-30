package ru.vsu.cs.masalkin.internet_shop_java.model;

import java.util.HashMap;
import java.util.List;

public class OrderDto {

    private int id;

    private Customer customer;

    private int order_cost;

    private HashMap<Product, Integer> products;

    public OrderDto(int id, Customer customer, HashMap<Product, Integer> products) {
        this.id = id;
        this.customer = customer;
        this.products = products;

        this.order_cost = products.entrySet().stream()
                .mapToInt(entry -> entry.getKey().getPrice() * entry.getValue())
                .sum();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public int getOrder_cost() {
        return order_cost;
    }

    public void setOrder_cost(int order_cost) {
        this.order_cost = order_cost;
    }

    public HashMap<Product, Integer> getProducts() {
        return products;
    }

    public void setProducts(HashMap<Product, Integer> products) {
        this.products = products;
    }
}
