package entitymanagers.impl;

import java.math.BigDecimal;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.hibernate.Session;
import org.hibernate.Transaction;
import entitymanagers.intf.IEstacionEntityManagerEJB;
import modelos.Estacion;
import utils.DAOGenerico;
import utils.HibernateUtil;

@Stateless
public class EstacionEntityManagerEJB extends DAOGenerico<Estacion> implements IEstacionEntityManagerEJB {

    @PersistenceContext(unitName = PERSISTENCE_UNIT)
    private EntityManager em;

    @Override
    public void createEstacion(String idTipoEstacion) {
        // String consulta = "SELECT * FROM ESTACION WHETE ID_TIPO_ESTACION =:
        // tipoEstacion";
        // Query query = se.createQuery(consulta);
        // query.setParameter("tipoEstacion", new
        // BigDecimal(isTipoEstacion)).getResultList();
    }

}