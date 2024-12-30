package ru.vsu.cs.masalkin.internet_shop_java.servlet;


import ru.vsu.cs.masalkin.internet_shop_java.model.Customer;
import ru.vsu.cs.masalkin.internet_shop_java.repository.CustomerRepositoryImpl;
import ru.vsu.cs.masalkin.internet_shop_java.service.CustomerService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(value = "/AdminCustomers")
public class AdminCustomersServlet extends HttpServlet {

    private CustomerService customerService;

    @Override
    public void init() throws ServletException {
        CustomerRepositoryImpl customerRepository = new CustomerRepositoryImpl();
        customerService = new CustomerService(customerRepository);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<Customer> customers = customerService.findAll();

        request.setAttribute("customers", customers);
        request.getRequestDispatcher("/AdminCustomers.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (request.getParameter("postType").equals("add")) {
            String fullname = request.getParameter("fullname");
            String phone_number = request.getParameter("phone_number");
            String address = request.getParameter("address");
            Customer customer = new Customer(fullname, phone_number, address);
            customerService.add(customer);
        } else if (request.getParameter("postType").equals("edit")) {
            String fullname = request.getParameter("fullname");
            String phone_number = request.getParameter("phone_number");
            String address = request.getParameter("address");
            Customer customer = new Customer(fullname, phone_number, address);
            customer.setId(Integer.parseInt(request.getParameter("id")));
            customerService.update(customer);
        } else if (request.getParameter("postType").equals("delete")) {
            Customer customer = new Customer("", "", "");
            customer.setId(Integer.parseInt(request.getParameter("id")));
            customerService.delete(customer);
        }
        doGet(request, response);
    }
}
