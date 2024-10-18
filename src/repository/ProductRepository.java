package repository;

import model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductRepository implements RepositoryInterface<Product> {

    private static List<Product> products = new ArrayList<Product>();

    private static int id = 1;

    @Override
    public Product add(Product product) {
        product.setId(id++);
        products.add(product);
        return product;
    }

    @Override
    public Optional<Product> getById(int id) {
        for (Product product : products) {
            if (product.getId() == id) {
                return Optional.of(product);
            }
        }
        return Optional.empty();
    }

    @Override
    public Product update(Product newProduct) {
        if (newProduct.getId() == 0) {
            return null;
        } else {
            for (Product product : products) {
                if (product.getId() == newProduct.getId()) {
                    products.set(products.indexOf(product), newProduct);
                    return newProduct;
                }
            }
        }
        return null;
    }


    @Override
    public Optional<Product> delete(Product product) {
        if (products.contains(product)) {
            products.remove(product);
            return Optional.of(product);
        }
        return Optional.empty();
    }

    @Override
    public List<Product> getAll() {
        return products;
    }
}