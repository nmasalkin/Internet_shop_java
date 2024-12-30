package ru.vsu.cs.masalkin.internet_shop_java.repository;

import ru.vsu.cs.masalkin.internet_shop_java.config.DataBaseConnection;
import ru.vsu.cs.masalkin.internet_shop_java.model.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CustomerRepositoryImpl implements Repository<Customer> {

    @Override
    public Customer add(Customer obj) {
        try (PreparedStatement preparedStatement = DataBaseConnection.getConnection()
                .prepareStatement("INSERT INTO customers(fullname, phone_number, address) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, obj.getFullname());
            preparedStatement.setString(2, obj.getPhone_number());
            preparedStatement.setString(3, obj.getAddress());
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
    public Customer update(Customer obj) {
        try (PreparedStatement preparedStatement = DataBaseConnection.getConnection()
                .prepareStatement("UPDATE customers SET fullname = ?, phone_number = ?, address = ? WHERE id = ?")) {
            preparedStatement.setString(1, obj.getFullname());
            preparedStatement.setString(2, obj.getPhone_number());
            preparedStatement.setString(3, obj.getAddress());
            preparedStatement.setInt(4, obj.getId());
            preparedStatement.executeUpdate();
            System.out.println(preparedStatement.executeUpdate());
            return findById(obj.getId()).get();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean delete(Customer obj) {
        try (PreparedStatement preparedStatement = DataBaseConnection.getConnection()
                .prepareStatement("DELETE FROM customers WHERE id = ?")) {
            preparedStatement.setInt(1, obj.getId());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Optional<Customer> findById(int id) {
        try (PreparedStatement preparedStatement = DataBaseConnection.getConnection()
                .prepareStatement("SELECT * FROM customers WHERE id = ?")) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeQuery();
            try (ResultSet resultSet = preparedStatement.getResultSet()) {
                Customer customer;
                if (resultSet.next()) {
                    customer = new Customer(
                            resultSet.getString("fullname"),
                            resultSet.getString("phone_number"),
                            resultSet.getString("address"));
                    customer.setId(resultSet.getInt("id"));
                    return Optional.of(customer);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<Customer> findAll() {
        try (PreparedStatement preparedStatement = DataBaseConnection.getConnection()
                .prepareStatement("SELECT * FROM customers ORDER BY id")) {
            preparedStatement.executeQuery();
            try (ResultSet resultSet = preparedStatement.getResultSet()) {
                List<Customer> customers = new ArrayList<>();
                Customer customer;
                while (resultSet.next()) {
                    customer = new Customer(
                            resultSet.getString("fullname"),
                            resultSet.getString("phone_number"),
                            resultSet.getString("address"));
                    customer.setId(resultSet.getInt("id"));
                    customers.add(customer);
                }
                return customers;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}
