package com.darwinruiz.shoplite.repositories;

import java.sql.Connection;
import java.sql.SQLException;

public class PrebaConexion {
    public static void main(String[] args) {
        try {
            Connection conn = Conexion.getInstance().getConnection();
            if (conn != null && !conn.isClosed()) {
                System.out.println("✅ FURULANDING ");
            } else {
                System.out.println("❌ NO FURULANDING");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}