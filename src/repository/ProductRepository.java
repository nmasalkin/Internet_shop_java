package repository;

import model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductRepository implements RepositoryInterface<Product> {

    private static List<Product> products = new ArrayList<Product>();

    private static int id = 1;

    @Override
    public void add(Product product) {
        product.setId(id++);
        products.add(product);
    }

    @Override
    public Product get(int id) {
        for (Product product : products) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }

    @Override
    public void update(int id, Product newProduct) {
        for (Product product : products) {
            if (product.getId() == id) {
                newProduct.setId(product.getId());
                products.set(products.indexOf(product), newProduct);
                break;
            }
        }
    }

    @Override
    public void delete(int id) {
        products.remove(get(id));
    }

    @Override
    public List<Product> getAll() {
        return products;
    }
}
