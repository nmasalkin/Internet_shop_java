package model;

import config.DataBaseConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

/**
 * Класс представляет собой модель заказа, содержащую информацию о его
 * идентификаторе, ID заказчика и описании.
 */
public class Order {

    /**
     * ID заказа.
     */
    private int id;

    /**
     * ID заказчика.
     */
    private int customerId;

    /**
     * Создает заказ
     *
     * @param customerId ID заказчика
     */
    public Order(int customerId) {
        this.customerId = customerId;
    }

    /**
     * Получить ID заказа.
     *
     * @return ID заказа
     */
    public int getId() {
        return id;
    }

    /**
     * Установить ID заказа.
     *
     * @param id ID заказа
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Получить ID заказчика.
     *
     * @return ID заказчика
     */
    public int getCustomerId() {
        return customerId;
    }

    /**
     * Установить ID заказчика.
     *
     * @param customerId ID заказчика
     */
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    /**
     * Представление объекта заказа в виде строки.
     *
     * @return строковое представление заказа
     */
    @Override
    public String toString() {
        try (PreparedStatement preparedStatement = DataBaseConnection.getConnection()
                .prepareStatement("SELECT * FROM customers WHERE id = ?")) {
            preparedStatement.setInt(1, customerId);
            preparedStatement.executeQuery();
            try (ResultSet resultSet = preparedStatement.getResultSet()) {
                if (resultSet.next()) {
                    return "ID заказа = " + id + ", ID заказчика = " + customerId +
                           ", ФИО = " + resultSet.getString("fullname") +
                           ", адрес = " + resultSet.getString("address") +
                           ", Номер телефона = " + resultSet.getString("phone_number");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
