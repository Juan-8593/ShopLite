package com.darwinruiz.shoplite.controllers;

import com.darwinruiz.shoplite.models.Product;
import com.darwinruiz.shoplite.repositories.ProductRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/admin")
public class AdminServlet extends HttpServlet {

    private ProductRepository repo;

    @Override
    public void init() {
        repo = new ProductRepository();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String action = req.getParameter("action");
            String idStr = req.getParameter("id");

            if (action != null && idStr != null) {
                int id = Integer.parseInt(idStr);
                switch (action) {
                    case "delete":
                        repo.delete(id);
                        resp.sendRedirect(req.getContextPath() + "/admin");
                        return;
                    case "edit":
                        Product p = repo.findById(id);
                        req.setAttribute("product", p);
                        req.getRequestDispatcher("/EditarProducto.jsp").forward(req, resp);
                        return;
                }
            }

            List<Product> products = repo.findAll();
            req.setAttribute("products", products);
            req.getRequestDispatcher("/admin.jsp").forward(req, resp);

        } catch (Exception e) {
            throw new ServletException("Error al procesar la solicitud", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String idStr = req.getParameter("id"); // viene al editar
        String name = req.getParameter("name");
        String priceStr = req.getParameter("price");
        String stockStr = req.getParameter("stock");

        double price = 0;
        int stock = 0;

        try { price = Double.parseDouble(priceStr); } catch (NumberFormatException ignored) {}
        try { stock = Integer.parseInt(stockStr); } catch (NumberFormatException ignored) {}

        if (name == null || name.trim().isEmpty() || price <= 0) {
            resp.sendRedirect(req.getContextPath() + "/admin?err=1");
            return;
        }

        Product p = new Product(0, name.trim(), price, stock, null);

        if (idStr != null && !idStr.isEmpty()) {
            p.setId(Integer.parseInt(idStr));
            repo.update(p);
        } else {
            repo.create(p);
        }

        resp.sendRedirect(req.getContextPath() + "/admin");
    }
}
