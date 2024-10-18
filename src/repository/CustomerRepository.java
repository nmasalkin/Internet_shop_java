package repository;

import model.Customer;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CustomerRepository implements RepositoryInterface<Customer> {

    private static List<Customer> customers = new ArrayList<Customer>();

    private static int id = 1;

    @Override
    public Customer add(Customer customer) {
        customer.setId(id++);
        customers.add(customer);
        return customer;
    }

    @Override
    public Optional<Customer> getById(int id) {
        for (Customer customer : customers) {
            if (customer.getId() == id) {
                return Optional.of(customer);
            }
        }
        return Optional.empty();
    }

    @Override
    public Customer update(Customer newCustomer) {
        if (newCustomer.getId() == 0) {
            return null;
        } else {
            for (Customer customer : customers) {
                if (customer.getId() == newCustomer.getId()) {
                    customers.set(customers.indexOf(customer), newCustomer);
                    return newCustomer;
                }
            }
        }
        return null;
    }

    @Override
    public Optional<Customer> delete(Customer customer) {
        if (customers.contains(customer)) {
            customers.remove(customer);
            return Optional.of(customer);
        }
        return Optional.empty();
    }

    @Override
    public List<Customer> getAll() {
        return customers;
    }
}