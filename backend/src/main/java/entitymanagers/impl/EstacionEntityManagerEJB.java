package entitymanagers.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.hibernate.Session;
import org.hibernate.Transaction;
import entitymanagers.intf.IEstacionEntityManagerEJB;
import modelos.Estacion;
import utils.DAOGenerico;
import utils.HibernateUtil;
import utils.TRANSMILENIOException;

@Stateless
public class EstacionEntityManagerEJB extends DAOGenerico<Estacion> implements IEstacionEntityManagerEJB {

    public EstacionEntityManagerEJB() {
        super(Estacion.class);
    }

    @Override
    public void createEstacion(Estacion estacion) throws TRANSMILENIOException {
        try {
            DAOGenerico<Estacion> dao = new DAOGenerico<Estacion>(Estacion.class);
            dao.insertar(estacion);
        } catch (Exception e) {
            throw new TRANSMILENIOException("Error en crear la estación " + e);
        }
    }

    @Override
    public List<Estacion> getEstacionByTipoEstacion(BigDecimal idTipoEstacion) throws TRANSMILENIOException {
        List<Estacion> listaEstacion = new ArrayList<>();
        // String consulta = "SELECT * FROM ESTACION WHETE ID_TIPO_ESTACION =:
        // tipoEstacion";
        // Query query = se.createQuery(consulta);
        // query.setParameter("tipoEstacion", new
        // BigDecimal(isTipoEstacion)).getResultList();
        try {
        } catch (Exception e) {
            throw new TRANSMILENIOException("Error en crear la estación " + e);
        }
        return listaEstacion;
    }

    @Override
    public void updateEstacion(Estacion estacion) throws TRANSMILENIOException {
        try {
            DAOGenerico<Estacion> dao = new DAOGenerico<Estacion>(Estacion.class);
            dao.actualizar(estacion);
        } catch (Exception e) {
            throw new TRANSMILENIOException("Error en crear la estación " + e);
        }
    }

    @Override
    public void deleteEstacion(Estacion estacion) throws TRANSMILENIOException {
        try {
            DAOGenerico<Estacion> dao = new DAOGenerico<Estacion>(Estacion.class);
            dao.eliminar(estacion);
        } catch (Exception e) {
            throw new TRANSMILENIOException("Error en crear la estación " + e);
        }
    }

}