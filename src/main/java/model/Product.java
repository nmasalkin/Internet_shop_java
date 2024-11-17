package model;

/**
 * Класс представляет собой модель товара, содержащую информацию о его
 * идентификаторе, названии, цене и описании.
 */
public class Product {

    /**
     * ID товара
     */
    private int id;

    /**
     * Название товара
     */
    private String title;

    /**
     * Цена товара
     */
    private int price;

    /**
     * Описание товара
     */
    private String description;

    /**
     * Создает товар
     *
     * @param title   название
     * @param price   цена
     * @param description описание
     */
    public Product(String title, int price, String description) {
        this.title = title;
        this.price = price;
        this.description = description;
    }

    /**
     * Получить ID товара
     *
     * @return ID
     */
    public int getId() {
        return id;
    }

    /**
     * Установить ID товара
     *
     * @param id ID
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Получить название товара
     *
     * @return название
     */
    public String getTitle() {
        return title;
    }

    /**
     * Установить название товара
     *
     * @param title название
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Получить цену товара
     *
     * @return цена
     */
    public int getPrice() {
        return price;
    }

    /**
     * Установить цену товара
     *
     * @param price цена
     */
    public void setPrice(int price) {
        this.price = price;
    }

    /**
     * Получить описание товара
     *
     * @return описание
     */
    public String getDescription() {
        return description;
    }

    /**
     * Установить описание товара
     *
     * @param description описание
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Представление объекта заказа в виде строки.
     *
     * @return строковое представление товара
     */
    @Override
    public String toString() {
        return "ID товара = " + id + ", название = " + title + ", цена = " + price + ", описание = " + description;
    }
}
