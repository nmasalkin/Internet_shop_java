package ru.vsu.cs.masalkin.internet_shop_java.servlet;

import ru.vsu.cs.masalkin.internet_shop_java.model.Product;
import ru.vsu.cs.masalkin.internet_shop_java.repository.ProductRepositoryImpl;
import ru.vsu.cs.masalkin.internet_shop_java.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(value = "/AdminProducts")
public class AdminProductsServlet extends HttpServlet {

    private ProductService productService;

    @Override
    public void init() throws ServletException {
        ProductRepositoryImpl productRepository = new ProductRepositoryImpl();
        productService = new ProductService(productRepository);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<Product> products = productService.findAll();
        request.setAttribute("products", products);
        request.getRequestDispatcher("/AdminProducts.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (request.getParameter("postType").equals("add")) {
            String title = request.getParameter("title");
            int price = Integer.parseInt(request.getParameter("price"));
            String description = request.getParameter("description");
            Product product = new Product(title, price, description);
            productService.add(product);
        } else if (request.getParameter("postType").equals("edit")) {
            String title = request.getParameter("title");
            int price = Integer.parseInt(request.getParameter("price"));
            String description = request.getParameter("description");
            Product product = new Product(title, price, description);
            product.setId(Integer.parseInt(request.getParameter("id")));
            productService.update(product);
        } else if (request.getParameter("postType").equals("delete")) {
            Product product = new Product("", 0, "");
            product.setId(Integer.parseInt(request.getParameter("id")));
            productService.delete(product);
        }
        doGet(request, response);
    }
}
