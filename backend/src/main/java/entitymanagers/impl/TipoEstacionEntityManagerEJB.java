package entitymanagers.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;
import entitymanagers.intf.IEstacionEntityManagerEJB;
import entitymanagers.intf.ITipoEstacionEntityManagerEJB;
import modelos.Estacion;
import modelos.TipoEstacion;
import utils.DAOGenerico;
import utils.HibernateUtil;
import utils.TRANSMILENIOException;

@Stateless
public class TipoEstacionEntityManagerEJB extends DAOGenerico<TipoEstacion> implements ITipoEstacionEntityManagerEJB {

    public TipoEstacionEntityManagerEJB() {
        super(TipoEstacion.class);
    }

    @Override
    public List<TipoEstacion> getAllTipoEstacion() throws TRANSMILENIOException {
        try {
            Session se = HibernateUtil.getSessionFactory().openSession();
            Transaction tx = se.beginTransaction();
            Query query = se.createQuery("from TipoEstacion");
            List<TipoEstacion> tp = query.getResultList();
            return tp;
        } catch (Exception e) {
            throw new TRANSMILENIOException("Error en crear la estación " + e);
        }
    }

}