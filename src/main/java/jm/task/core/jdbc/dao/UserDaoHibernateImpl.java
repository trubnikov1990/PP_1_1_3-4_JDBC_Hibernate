package jm.task.core.jdbc.dao;

import com.mysql.cj.Query;
import java.util.ArrayList;
import jm.task.core.jdbc.model.User;

import java.util.List;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class UserDaoHibernateImpl implements UserDao {

  Transaction transaction = null;

  public UserDaoHibernateImpl() { }

  @Override
  public void createUsersTable() {
    try (Session session = Util.getSessionFactory().openSession()) {
      transaction = session.beginTransaction();
      session.createSQLQuery("CREATE TABLE IF NOT EXISTS user "
              + "(id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,"
              + " name VARCHAR(255) NOT NULL, lastName VARCHAR(255) NOT NULL, age TINYINT NOT NULL )")
          .executeUpdate();
      transaction.commit();
    } catch (Exception e) {
      if (transaction != null) {
        transaction.rollback();
      }
      e.printStackTrace();
    }
  }

  @Override
  public void dropUsersTable() {
    try (Session session = Util.getSessionFactory().openSession()) {
      transaction = session.beginTransaction();
      session.createSQLQuery("DROP TABLE IF EXISTS user").executeUpdate();
      transaction.commit();
    } catch (Exception e) {
      if (transaction != null) {
        transaction.rollback();
      }
      e.printStackTrace();
    }
  }

  @Override
  public void saveUser(String name, String lastName, byte age) {
    try (Session session = Util.getSessionFactory().openSession()) {
      transaction = session.beginTransaction();
      session.save(new User(name, lastName, age));
      transaction.commit();
    } catch (Exception e) {
      if (transaction != null) {
        transaction.rollback();
      }
      e.printStackTrace();
    }
  }

  @Override
  public void removeUserById(long id) {
    try (Session session = Util.getSessionFactory().openSession()) {
      transaction = session.beginTransaction();
      User user = session.get(User.class , id);
      session.delete(user);
      transaction.commit();
    } catch (Exception e) {
      if (transaction != null) {
        transaction.rollback();
      }
      e.printStackTrace();
    }

  }

  @Override
  public List<User> getAllUsers() {
    try (Session session = Util.getSessionFactory().openSession()) {
      return session.createSQLQuery("SELECT * FROM user").addEntity(User.class).list();
    }
  }

  @Override
  public void cleanUsersTable() {
    try (Session session = Util.getSessionFactory().openSession()) {
      transaction = session.beginTransaction();
      session.createQuery("delete from User ").executeUpdate();
      transaction.commit();
    } catch (Exception e) {
      if (transaction != null) {
        transaction.rollback();
      }
      e.printStackTrace();
    }
  }
}
