package ru.vsu.cs.masalkin.internet_shop_java.repository;

import ru.vsu.cs.masalkin.internet_shop_java.config.DataBaseConnection;
import ru.vsu.cs.masalkin.internet_shop_java.model.Order;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderRepositoryImpl implements Repository<Order> {

    @Override
    public Order add(Order obj) {
        try (PreparedStatement preparedStatement = DataBaseConnection.getConnection()
                .prepareStatement("INSERT INTO orders(customer_id) VALUES (?)", Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, obj.getCustomerId());
            preparedStatement.executeUpdate();
            try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    int generatedId = resultSet.getInt(1);
                    obj.setId(generatedId);
                    return findById(generatedId).get();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Order update(Order obj) {
        try (PreparedStatement preparedStatement = DataBaseConnection.getConnection()
                .prepareStatement("UPDATE orders SET customer_id = ? WHERE id = ?")) {
            preparedStatement.setInt(1, obj.getCustomerId());
            preparedStatement.setInt(2, obj.getId());
            preparedStatement.executeUpdate();
            System.out.println(preparedStatement.executeUpdate());
            return findById(obj.getId()).get();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean delete(Order obj) {
        try (PreparedStatement preparedStatement = DataBaseConnection.getConnection()
                .prepareStatement("DELETE FROM orders WHERE id = ?")) {
            preparedStatement.setInt(1, obj.getId());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Optional<Order> findById(int id) {
        try (PreparedStatement preparedStatement = DataBaseConnection.getConnection()
                .prepareStatement("SELECT * FROM orders WHERE id = ?")) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeQuery();
            try (ResultSet resultSet = preparedStatement.getResultSet()) {
                Order order;
                if (resultSet.next()) {
                    order = new Order(resultSet.getInt("customer_id"));
                    order.setId(resultSet.getInt("id"));
                    return Optional.of(order);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<Order> findAll() {
        try (PreparedStatement preparedStatement = DataBaseConnection.getConnection()
                .prepareStatement("SELECT * FROM orders ORDER BY id")) {
            preparedStatement.executeQuery();
            try (ResultSet resultSet = preparedStatement.getResultSet()) {
                List<Order> orders = new ArrayList<>();
                Order order;
                while (resultSet.next()) {
                    order = new Order(resultSet.getInt("customer_id"));
                    order.setId(resultSet.getInt("id"));
                    orders.add(order);
                }
                return orders;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}
