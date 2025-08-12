package com.darwinruiz.shoplite.controllers;

import com.darwinruiz.shoplite.models.User;
import com.darwinruiz.shoplite.repositories.UserRepository;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Optional;

@WebServlet("/auth/login")
public class LoginServlet extends HttpServlet {
    private final UserRepository users = new UserRepository();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String email = req.getParameter("email");
        String pass = req.getParameter("password");

        Optional<User> u = users.findByEmail(email);

        if (u.isEmpty() || !u.get().getPassword().equals(pass)) {
            resp.sendRedirect(req.getContextPath() + "/login.jsp?err=1");
            return;
        }

        // Cerrar sesión
        HttpSession oldSession = req.getSession(false);
        if (oldSession != null) {
            oldSession.invalidate();
        }

        // Crea una nueva sesion.
        HttpSession session = req.getSession(true);
        session.setAttribute("auth", true);
        session.setAttribute("userEmail", u.get().getEmail());
        session.setAttribute("role", u.get().getRole());
        session.setMaxInactiveInterval(1800);

        resp.sendRedirect(req.getContextPath() + "/home");
    }
}
