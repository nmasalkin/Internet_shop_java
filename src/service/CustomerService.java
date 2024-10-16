package service;

import model.Customer;
import repository.CustomerRepository;

import java.util.List;

public class CustomerService {

    private CustomerRepository customerRepository = new CustomerRepository();

    public void add(Customer customer) {
        customerRepository.add(customer);
    }

    public Customer get(int id) {
        return customerRepository.get(id);
    }

    public void update(int id, Customer newCustomer) {
        customerRepository.update(id, newCustomer);
    }

    public void delete(int id) {
        customerRepository.delete(id);
    }

    public List<Customer> getAll() {
        return customerRepository.getAll();
    }
}