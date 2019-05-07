package utils;

import utils.HibernateUtil;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transaction;

import org.hibernate.Query;
import org.hibernate.Session;

public class DAOGenerico<T> {

    private Class<T> objClass;

    public DAOGenerico(Class<T> objClass) {
        this.objClass = objClass;
    }

    public T consultarPorId(BigDecimal id) {
        EntityManager session = HibernateUtil.getSessionFactory().createEntityManager();
        T obj = null;
        try {
            session = HibernateUtil.getSessionFactory().createEntityManager();
            obj = (T) session.getReference(objClass, id);
        } catch (Exception e) {
            obj = null;
            System.out.println(e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return obj;
    }

    public void insertar(T obj) {
        EntityManager session = HibernateUtil.getSessionFactory().createEntityManager();
        try {
            session.getTransaction().begin();
            session.persist(obj);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public void actualizar(T obj) {
        EntityManager session = (EntityManager) HibernateUtil.getSessionFactory();
        try {
            session.getTransaction().begin();
            session.merge(obj);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public void eliminar(T obj) {
        EntityManager session = HibernateUtil.getSessionFactory().createEntityManager();
        try {
            session.getTransaction().begin();
            session.remove(obj);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

}
