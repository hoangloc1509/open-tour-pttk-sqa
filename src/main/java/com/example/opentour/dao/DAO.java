package com.example.opentour.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class DAO {
    public static Connection con = null;

    public DAO() {
        if (con == null) {
            String dbUrl = "jdbc:mysql://localhost:3306/open_tour";
            String user = "root";
            String password = "";
            String dbClass = "com.mysql.cj.jdbc.Driver";
            try {
                Class.forName(dbClass);
                con = DriverManager.getConnection(dbUrl, user, password);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
