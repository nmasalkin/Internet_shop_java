package repository;

import model.Customer;

import java.util.ArrayList;
import java.util.List;

public class CustomerRepository implements RepositoryInterface<Customer> {

    private static List<Customer> customers = new ArrayList<Customer>();

    private static int id = 1;

    @Override
    public void add(Customer customer) {
        customer.setId(id++);
        customers.add(customer);
    }

    @Override
    public Customer get(int id) {
        for (Customer customer : customers) {
            if (customer.getId() == id) {
                return customer;
            }
        }
        return null;
    }

    @Override
    public void update(int id, Customer newCustomer) {
        for (Customer customer : customers) {
            if (customer.getId() == id) {
                newCustomer.setId(customer.getId());
                customers.set(customers.indexOf(customer), newCustomer);
                break;
            }
        }
    }

    @Override
    public void delete(int id) {
        customers.remove(get(id));
    }

    @Override
    public List<Customer> getAll() {
        return customers;
    }
}