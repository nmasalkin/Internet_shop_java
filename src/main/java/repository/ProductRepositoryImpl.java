package repository;

import config.DataBaseConnection;
import model.Product;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductRepositoryImpl implements Repository<Product> {

    @Override
    public Product add(Product obj) {
        try (PreparedStatement preparedStatement = DataBaseConnection.getConnection()
                .prepareStatement("INSERT INTO products(title, price, description) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, obj.getTitle());
            preparedStatement.setInt(2, obj.getPrice());
            preparedStatement.setString(3, obj.getDescription());
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
    public Product update(Product obj) {
        try (PreparedStatement preparedStatement = DataBaseConnection.getConnection()
                .prepareStatement("UPDATE products SET title = ?, price = ?, description = ? WHERE id = ?")) {
            preparedStatement.setString(1, obj.getTitle());
            preparedStatement.setInt(2, obj.getPrice());
            preparedStatement.setString(3, obj.getDescription());
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
    public boolean delete(Product obj) {
        try (PreparedStatement preparedStatement = DataBaseConnection.getConnection()
                .prepareStatement("DELETE FROM products WHERE id = ?")) {
            preparedStatement.setInt(1, obj.getId());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Optional<Product> findById(int id) {
        try (PreparedStatement preparedStatement = DataBaseConnection.getConnection()
                .prepareStatement("SELECT * FROM products WHERE id = ?")) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeQuery();
            try (ResultSet resultSet = preparedStatement.getResultSet()) {
                Product product;
                if (resultSet.next()) {
                    product = new Product(
                            resultSet.getString("title"),
                            resultSet.getInt("price"),
                            resultSet.getString("description"));
                    product.setId(resultSet.getInt("id"));
                    return Optional.of(product);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<Product> findAll() {
        try (PreparedStatement preparedStatement = DataBaseConnection.getConnection()
                .prepareStatement("SELECT * FROM products ORDER BY id")) {
            preparedStatement.executeQuery();
            try (ResultSet resultSet = preparedStatement.getResultSet()) {
                List<Product> products = new ArrayList<>();
                Product product;
                while (resultSet.next()) {
                    product = new Product(
                            resultSet.getString("title"),
                            resultSet.getInt("price"),
                            resultSet.getString("description"));
                    product.setId(resultSet.getInt("id"));
                    products.add(product);
                }
                return products;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}
