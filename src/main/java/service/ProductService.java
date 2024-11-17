package service;

import model.Product;
import repository.ProductRepositoryImpl;

import java.util.List;
import java.util.Optional;

public class ProductService {

    private ProductRepositoryImpl productRepositoryImpl;

    public ProductService(ProductRepositoryImpl productRepositoryImpl) {
        this.productRepositoryImpl = productRepositoryImpl;
    }

    public Product add(Product obj) {
        return productRepositoryImpl.add(obj);
    }

    public Product update(Product obj) {
        return productRepositoryImpl.update(obj);
    }

    public boolean delete(Product obj) {
        return productRepositoryImpl.delete(obj);
    }

    public Optional<Product> findById(int id) {
        return productRepositoryImpl.findById(id);
    }

    public List<Product> findAll() {
        return productRepositoryImpl.findAll();
    }
}
