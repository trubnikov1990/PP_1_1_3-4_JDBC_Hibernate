package jm.task.core.jdbc.util;


import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {

  private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
  private static final String DB_URL = "jdbc:mysql://localhost:3306/mybase";
  private static final String DB_USERNAME = "root";
  private static final String DB_PASSWORD = "root";
  private static Connection connection = null;

  public static Connection getConnection() {

    try {
      if (connection == null) {
        Class.forName(DB_DRIVER);
        System.out.println("OK");
        connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
      }
    } catch ( ClassNotFoundException | SQLException e) {
      System.out.println("Error in class Util");
      e.printStackTrace();
    }
    return connection;
  }
}

