package ru.vsu.cs.masalkin.internet_shop_java.servlet;


import ru.vsu.cs.masalkin.internet_shop_java.model.*;
import ru.vsu.cs.masalkin.internet_shop_java.repository.CustomerRepositoryImpl;
import ru.vsu.cs.masalkin.internet_shop_java.repository.OrderProductsRepositoryImpl;
import ru.vsu.cs.masalkin.internet_shop_java.repository.OrderRepositoryImpl;
import ru.vsu.cs.masalkin.internet_shop_java.repository.ProductRepositoryImpl;
import ru.vsu.cs.masalkin.internet_shop_java.service.CustomerService;
import ru.vsu.cs.masalkin.internet_shop_java.service.OrderProductsService;
import ru.vsu.cs.masalkin.internet_shop_java.service.OrderService;
import ru.vsu.cs.masalkin.internet_shop_java.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@WebServlet(value = "/AdminOrders")
public class AdminOrdersServlet extends HttpServlet {

    private OrderService orderService;

    private OrderProductsService orderProductsService;

    private ProductService productService;

    private CustomerService customerService;

    @Override
    public void init() throws ServletException {
        OrderRepositoryImpl orderRepository = new OrderRepositoryImpl();
        orderService = new OrderService(orderRepository);
        OrderProductsRepositoryImpl orderProductsRepository = new OrderProductsRepositoryImpl();
        orderProductsService = new OrderProductsService(orderProductsRepository);
        ProductRepositoryImpl productRepository = new ProductRepositoryImpl();
        productService = new ProductService(productRepository);
        CustomerRepositoryImpl customerRepository = new CustomerRepositoryImpl();
        customerService = new CustomerService(customerRepository);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<Order> orders = orderService.findAll();
        List<OrderDto> orderDtos = new ArrayList<>();
        List<OrderProducts> orderProducts;
        for (Order order : orders) {
            HashMap<Product, Integer> products = new HashMap<>();
            orderProducts = orderProductsService.findAllByOrderId(order.getId());
            for (OrderProducts orderProduct : orderProducts) {
                Product product = productService.findById(orderProduct.getProductId()).get();
                product.setPrice(orderProduct.getPrice());
                products.put(product, orderProduct.getQuantity());
            }
            orderDtos.add(new OrderDto(order.getId(), customerService.findById(order.getCustomerId()).get(), products));
        }

        request.setAttribute("orders", orderDtos);

        List<Product> products = productService.findAll();
        request.setAttribute("products", products);

        List<Customer> customers = customerService.findAll();
        request.setAttribute("customers", customers);

        request.getRequestDispatcher("/AdminOrders.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("delete") != null) {
            int id = Integer.parseInt(request.getParameter("orderId"));
            Optional<Order> orderOptional = orderService.findById(id);
            if (orderOptional.isPresent()) {
                for (OrderProducts orderProducts : orderProductsService.findAllByOrderId(id)) {
                    orderProductsService.delete(orderProducts);
                }
                orderService.delete(orderOptional.get());
            }
            response.sendRedirect(request.getContextPath() + "/AdminOrders");
        } else {
            int customerId = Integer.parseInt(request.getParameter("customerId"));

            List<OrderProducts> orderProductsList = new ArrayList<>();
            int productId;
            int quantity;
            try {
                for (int i = 0; i <= Integer.parseInt(request.getParameter("productIndex")); i++) {
                    if (request.getParameter("product[" + i + "].id") != null) {
                        productId = Integer.parseInt(request.getParameter("product[" + i + "].id"));
                        quantity = Integer.parseInt(request.getParameter("product[" + i + "].quantity"));
                        orderProductsList.add(new OrderProducts(0, productId, productService.findById(productId).get().getPrice(), quantity));
                    }
                }
            } catch (Exception e) {
                response.sendRedirect(request.getContextPath() + "/AdminOrders");
                return;
            }

            Order order = orderService.add(new Order(customerId));

            for (OrderProducts orderProducts : orderProductsList) {
                orderProducts.setOrderId(order.getId());
                orderProductsService.add(orderProducts);
            }

            response.sendRedirect(request.getContextPath() + "/AdminOrders");
        }
    }

}
