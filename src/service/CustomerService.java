package service;

import model.Customer;
import repository.CustomerRepository;

import java.util.List;
import java.util.Optional;

public class CustomerService {

    private CustomerRepository customerRepository = new CustomerRepository();

    public Customer add(Customer customer) {
        return customerRepository.add(customer);
    }

    public Optional<Customer> getById(int id) {
        return customerRepository.getById(id);
    }

    public Customer update(Customer newCustomer) {
        return customerRepository.update(newCustomer);
    }

    public Optional<Customer> delete(Customer customer) {
        return customerRepository.delete(customer);
    }

    public List<Customer> getAll() {
        return customerRepository.getAll();
    }
}