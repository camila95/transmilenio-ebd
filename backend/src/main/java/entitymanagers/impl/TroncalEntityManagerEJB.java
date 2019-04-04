package entitymanagers.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import entitymanagers.intf.IEstacionEntityManagerEJB;
import entitymanagers.intf.ITroncalEntityManagerEJB;
import modelos.Estacion;
import modelos.Troncal;
import utils.DAOGenerico;
import utils.HibernateUtil;
import utils.TRANSMILENIOException;

@Stateless
public class TroncalEntityManagerEJB extends DAOGenerico<Troncal> implements ITroncalEntityManagerEJB {

    public TroncalEntityManagerEJB() {
        super(Troncal.class);
    }

    @Override
    public List<Troncal> getAllTroncales() throws TRANSMILENIOException {
        try {
            Session se = HibernateUtil.getSessionFactory().openSession();
            Transaction tx = se.beginTransaction();
            Query query = se.createQuery("from Troncal ");
            List<Troncal> t = query.getResultList();
            return t;
        } catch (Exception e) {
            throw new TRANSMILENIOException("Error en crear la estación " + e);
        }
    }
}