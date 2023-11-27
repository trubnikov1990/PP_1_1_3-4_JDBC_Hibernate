package jm.task.core.jdbc.util;


import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

public class Util {

  private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
  private static final String DB_URL = "jdbc:mysql://localhost:3306/mybase";
  private static final String DB_USERNAME = "root";
  private static final String DB_PASSWORD = "root";
  private static final String DB_DIALECT = "org.hibernate.dialect.MySQL5Dialect";


  private static Connection connection = null;

  public static Connection getConnection() {

    try {
      if (connection == null) {
        Class.forName(DB_DRIVER);
        System.out.println("OK");
        connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
      }
    } catch (ClassNotFoundException | SQLException e) {
      System.out.println("Error in class Util");
      e.printStackTrace();
    }
    return connection;
  }
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
      if (sessionFactory == null) {
        try {
          Configuration configuration = new Configuration();

          Properties setting = new Properties();
          setting.put(Environment.DRIVER, DB_DRIVER);
          setting.put(Environment.URL, DB_URL);
          setting.put(Environment.USER, DB_USERNAME);
          setting.put(Environment.PASS, DB_PASSWORD);
          setting.put(Environment.DIALECT, DB_DIALECT);
          setting.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
          setting.put(Environment.HBM2DDL_AUTO, "");
          setting.put(Environment.SHOW_SQL, "true");

          configuration.setProperties(setting);
          configuration.addAnnotatedClass(User.class);

          ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
              .applySettings(configuration.getProperties()).build();
          sessionFactory = configuration.buildSessionFactory(serviceRegistry);
          System.out.println("OK");
        } catch (Exception e) {
          System.out.println("Error in class Util");
          e.printStackTrace();
        }
      }
      return sessionFactory;
    }
  }



