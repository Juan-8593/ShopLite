package com.darwinruiz.shoplite.repositories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    private static Conexion instance;
    private Connection connection;
    private final String URL = "jdbc:postgresql://localhost:5433/postgres";
    private final String USER = "postgres";
    private final String PASSWORD = "admin123";

    private Conexion() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
            this.connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            System.out.println("PostgreSQL JDBC Driver no encontrado");
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public static Conexion getInstance() throws SQLException {
        if (instance == null) {
            instance = new Conexion();
        } else if (instance.getConnection().isClosed()) {
            instance = new Conexion();
        }
        return instance;
    }
}