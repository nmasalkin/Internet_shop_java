package ru.vsu.cs.masalkin.internet_shop_java.model;

import ru.vsu.cs.masalkin.internet_shop_java.config.DataBaseConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Класс представляет собой модель заказа-товара, содержащую информацию
 * о ID заказа, ID товара, цене товара и его количестве.
 */
public class OrderProducts {

    /**
     * ID заказа
     */
    private int orderId;

    /**
     * ID товара
     */
    private int productId;

    /**
     * Цена товара
     */
    private int price;

    /**
     * Количество товара
     */
    private int quantity;

    /**
     * Создает заказ-товар
     *
     * @param orderId   ID заказа
     * @param productId ID товара
     * @param price     цена
     * @param quantity  количество
     */
    public OrderProducts(int orderId, int productId, int price, int quantity) {
        this.orderId = orderId;
        this.productId = productId;
        this.price = price;
        this.quantity = quantity;
    }

    /**
     * Получить ID заказа
     *
     * @return ID заказа
     */
    public int getOrderId() {
        return orderId;
    }

    /**
     * Установить ID заказа
     *
     * @param orderId ID заказа
     */
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    /**
     * Получить ID товара
     *
     * @return ID товара
     */
    public int getProductId() {
        return productId;
    }

    /**
     * Установить ID товара
     *
     * @param productId ID товара
     */
    public void setProductId(int productId) {
        this.productId = productId;
    }

    /**
     * Получить цену
     *
     * @return цена
     */
    public int getPrice() {
        return price;
    }

    /**
     * Установить цену
     *
     * @param price цена
     */
    public void setPrice(int price) {
        this.price = price;
    }

    /**
     * Получить количество
     *
     * @return количество
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Установить количество
     *
     * @param quantity количество
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Представление объекта заказа в виде строки.
     *
     * @return строковое представление заказ-товара.
     */
    @Override
    public String toString() {
        try (PreparedStatement preparedStatement = DataBaseConnection.getConnection()
                .prepareStatement("SELECT * FROM products WHERE id = ?")) {
            preparedStatement.setInt(1, productId);
            preparedStatement.executeQuery();
            try (ResultSet resultSet = preparedStatement.getResultSet()) {
                if (resultSet.next()) {
                    return "ID товара = " + productId + ", название = " + resultSet.getString("title") +
                           ", цена = " + price + ", кол-во = " + quantity +
                           ", описание = " + resultSet.getString("description");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}