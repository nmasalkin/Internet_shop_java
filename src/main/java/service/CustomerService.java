package service;

import model.Customer;
import repository.CustomerRepositoryImpl;

import java.util.List;
import java.util.Optional;

public class CustomerService {

    private CustomerRepositoryImpl customerRepositoryImpl;

    public CustomerService(CustomerRepositoryImpl customerRepositoryImpl) {
        this.customerRepositoryImpl = customerRepositoryImpl;
    }

    public Customer add(Customer obj) {
        return customerRepositoryImpl.add(obj);
    }

    public Customer update(Customer obj) {
        return customerRepositoryImpl.update(obj);
    }

    public boolean delete(Customer obj) {
        return customerRepositoryImpl.delete(obj);
    }

    public Optional<Customer> findById(int id) {
        return customerRepositoryImpl.findById(id);
    }

    public List<Customer> findAll() {
        return customerRepositoryImpl.findAll();
    }
}
