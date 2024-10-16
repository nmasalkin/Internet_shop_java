package model;

public class Product {

    private int id;

    private String title;

    private double price;

    private String description;

    public Product(String title, double price, String description) {
        this.title = title;
        this.price = price;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "ID = " + id + ", наименование = " + title + ", цена = " + price + ", описание = " + description;
    }
}