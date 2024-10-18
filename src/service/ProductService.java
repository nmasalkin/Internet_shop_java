package service;

import model.Product;
import repository.ProductRepository;

import java.util.List;
import java.util.Optional;

public class ProductService {

    private ProductRepository productRepository = new ProductRepository();

    public Product add(Product product) {
        return productRepository.add(product);
    }

    public Optional<Product> getById(int id) {
        return productRepository.getById(id);
    }

    public Product update(Product newProduct) {
        return productRepository.update(newProduct);
    }

    public Optional<Product> delete(Product product) {
        return productRepository.delete(product);
    }

    public List<Product> getAll() {
        return productRepository.getAll();
    }
}