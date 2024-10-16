package service;

import model.Product;
import repository.ProductRepository;

import java.util.List;

public class ProductService {

    private ProductRepository productRepository = new ProductRepository();

    public void add(Product product) {
        productRepository.add(product);
    }

    public Product get(int id) {
        return productRepository.get(id);
    }

    public void update(int id, Product newProduct) {
        productRepository.update(id, newProduct);
    }

    public void delete(int id) {
        productRepository.delete(id);
    }

    public List<Product> getAll() {
        return productRepository.getAll();
    }
}