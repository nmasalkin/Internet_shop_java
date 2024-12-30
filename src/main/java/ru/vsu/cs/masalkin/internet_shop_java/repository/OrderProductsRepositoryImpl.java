package ru.vsu.cs.masalkin.internet_shop_java.repository;

import ru.vsu.cs.masalkin.internet_shop_java.config.DataBaseConnection;
import ru.vsu.cs.masalkin.internet_shop_java.model.Order;
import ru.vsu.cs.masalkin.internet_shop_java.model.OrderProducts;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderProductsRepositoryImpl implements OrderProductsRepository<OrderProducts> {

    @Override
    public OrderProducts add(OrderProducts obj) {
        try (PreparedStatement preparedStatement = DataBaseConnection.getConnection()
                .prepareStatement("INSERT INTO order_products(order_id, product_id, price, quantity) VALUES (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, obj.getOrderId());
            preparedStatement.setInt(2, obj.getProductId());
            preparedStatement.setInt(3, obj.getPrice());
            preparedStatement.setInt(4, obj.getQuantity());
            preparedStatement.executeUpdate();
            try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    return findById(obj.getOrderId(), obj.getProductId()).get();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public OrderProducts update(OrderProducts obj) {
        try (PreparedStatement preparedStatement = DataBaseConnection.getConnection()
                .prepareStatement("UPDATE order_products SET price = ?, quantity = ? WHERE order_id = ? AND product_id = ?")) {
            preparedStatement.setInt(1, obj.getPrice());
            preparedStatement.setInt(2, obj.getQuantity());
            preparedStatement.setInt(3, obj.getOrderId());
            preparedStatement.setInt(4, obj.getProductId());
            preparedStatement.executeUpdate();
            System.out.println(preparedStatement.executeUpdate());
            return findById(obj.getOrderId(), obj.getProductId()).get();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean delete(OrderProducts obj) {
        try (PreparedStatement preparedStatement = DataBaseConnection.getConnection()
                .prepareStatement("DELETE FROM order_products WHERE order_id = ? AND product_id = ?")) {
            preparedStatement.setInt(1, obj.getOrderId());
            preparedStatement.setInt(2, obj.getProductId());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Optional<OrderProducts> findById(int order_id, int product_id) {
        try (PreparedStatement preparedStatement = DataBaseConnection.getConnection()
                .prepareStatement("SELECT * FROM order_products WHERE order_id = ? AND product_id = ?")) {
            preparedStatement.setInt(1, order_id);
            preparedStatement.setInt(2, product_id);
            preparedStatement.executeQuery();
            try (ResultSet resultSet = preparedStatement.getResultSet()) {
                OrderProducts orderProducts;
                if (resultSet.next()) {
                    orderProducts = new OrderProducts(
                            resultSet.getInt("order_id"),
                            resultSet.getInt("product_id"),
                            resultSet.getInt("price"),
                            resultSet.getInt("quantity"));
                    return Optional.of(orderProducts);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<OrderProducts> findAllByOrderId(int order_id) {
        try (PreparedStatement preparedStatement = DataBaseConnection.getConnection()
                .prepareStatement("SELECT * FROM order_products where order_id = ?")) {
            preparedStatement.setInt(1, order_id);
            preparedStatement.executeQuery();
            try (ResultSet resultSet = preparedStatement.getResultSet()) {
                List<OrderProducts> orderProductsList = new ArrayList<>();
                OrderProducts orderProducts;
                while (resultSet.next()) {
                    orderProducts = new OrderProducts(
                            resultSet.getInt("order_id"),
                            resultSet.getInt("product_id"),
                            resultSet.getInt("price"),
                            resultSet.getInt("quantity"));
                    orderProductsList.add(orderProducts);
                }
                return orderProductsList;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return new ArrayList<>();
    }

    @Override
    public List<OrderProducts> findAll() {
        try (PreparedStatement preparedStatement = DataBaseConnection.getConnection()
                .prepareStatement("SELECT * FROM order_products ORDER BY order_id")) {
            preparedStatement.executeQuery();
            try (ResultSet resultSet = preparedStatement.getResultSet()) {
                List<OrderProducts> orderProductsList = new ArrayList<>();
                OrderProducts orderProducts;
                while (resultSet.next()) {
                    orderProducts = new OrderProducts(
                            resultSet.getInt("order_id"),
                            resultSet.getInt("product_id"),
                            resultSet.getInt("price"),
                            resultSet.getInt("quantity"));
                    orderProductsList.add(orderProducts);
                }
                return orderProductsList;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}
