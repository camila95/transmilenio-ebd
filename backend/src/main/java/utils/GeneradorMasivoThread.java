package utils;

import java.lang.reflect.Method;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class GeneradorMasivoThread<T> extends Thread implements Runnable {

    private int numeroRegistros;
    private Class<T> tabla;

    public GeneradorMasivoThread(int numeroRegistros, Class<T> tabla) {
        this.numeroRegistros = numeroRegistros;
        this.tabla = tabla;
    }

    @Override
    public void run() {
        EntityManager session = HibernateUtil.getSessionFactory().createEntityManager();
        EntityTransaction tx = session.getTransaction();
        try {
            tx.begin();
            Method m = this.tabla.getMethod("fake");
            for (int i=0; i<this.numeroRegistros; i++) {
                T obj = (T) m.invoke(null);
                session.persist(obj);
            }
            tx.commit();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

}
