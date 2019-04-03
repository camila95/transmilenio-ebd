package controladores;

import java.util.Collection;
import org.hibernate.Session;

import javax.persistence.EntityManager;
import modelos.Test;
import utils.HibernateUtil;

/**
 * ControladorTest
 */
import java.util.HashMap;

public class ControladorTest {

  public static HashMap<String, Test> get(int id) {
    EntityManager session = null;
    HashMap<String, Test> test = new HashMap<>();
    try {
      session = HibernateUtil.getSessionFactory().createEntityManager();
      test.put("1", session.getReference(Test.class, id));
    } catch (Exception e) {
      System.out.println(e);
    } finally {
      if (session != null && session.isOpen()) {
          session.close();
      }
    }
    return test;
  }
}
