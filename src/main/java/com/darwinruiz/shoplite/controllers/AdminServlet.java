package com.darwinruiz.shoplite.controllers;

import com.darwinruiz.shoplite.models.Product;
import com.darwinruiz.shoplite.repositories.ProductRepository;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/admin")
public class AdminServlet extends HttpServlet {
    private final ProductRepository repo = new ProductRepository();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            req.getRequestDispatcher("/admin.jsp").forward(req, resp);
        } catch (Exception e) {
            throw new IOException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String name = req.getParameter("name");
        String priceStr = req.getParameter("price");
        String stockStr = req.getParameter("stock");

        double price = 0;
        int stock = 0;

        try {
            price = Double.parseDouble(priceStr);
        } catch (NumberFormatException ignored) {}

        try {
            stock = Integer.parseInt(stockStr);
        } catch (NumberFormatException ignored) {}

        if (name == null || name.trim().isEmpty() || price <= 0) {
            resp.sendRedirect(req.getContextPath() + "/admin?err=1");
            return;
        }


        Product p = new Product(0L, name.trim(), price, stock);

        repo.add(p);

        resp.sendRedirect(req.getContextPath() + "/home");
    }
}