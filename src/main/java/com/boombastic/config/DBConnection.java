package com.boombastic.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * The `DBConnection` class provides a static method to establish a connection to a MySQL database.
 */
public class DBConnection {
    
 /**
  * The function `getConnection` establishes a connection to a MySQL database using JDBC in a Java
  * application.
  * 
  * @return The `getConnection()` method returns a `Connection` object, which represents a connection
  * to a database.
  */
    
    public static Connection getConnection() {
        Connection con;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hr_system?useSSL=false", "root", "");
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        return con;
    }
}
