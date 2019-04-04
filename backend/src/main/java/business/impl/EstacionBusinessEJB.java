package business.impl;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import business.intf.IEstacionBusinessEJB;
import entitymanagers.intf.IEstacionEntityManagerEJB;
import modelos.Estacion;
import utils.TRANSMILENIOException;

@Stateless
public class EstacionBusinessEJB implements IEstacionBusinessEJB {

    @Inject
    private IEstacionEntityManagerEJB iEstacionEntityManagerEJB;

    @Override
    public void createEstacion(Estacion estacion) throws TRANSMILENIOException {
        try {
            this.iEstacionEntityManagerEJB.createEstacion(estacion);
        } catch (TRANSMILENIOException e) {
            throw new TRANSMILENIOException(e.toString());
        }
    }

    @Override
    public List<Estacion> getEstacionByTipoEstacion(BigDecimal idTipoEstacion) throws TRANSMILENIOException {
        try {
            return this.iEstacionEntityManagerEJB.getEstacionByTipoEstacion(idTipoEstacion);
        } catch (TRANSMILENIOException e) {
            throw new TRANSMILENIOException(e.toString());
        }
    }

    @Override
    public void updateEstacion(Estacion estacion) throws TRANSMILENIOException {
        try {
            this.iEstacionEntityManagerEJB.updateEstacion(estacion);
        } catch (TRANSMILENIOException e) {
            throw new TRANSMILENIOException(e.toString());
        }
    }

    @Override
    public void deleteEstacion(Estacion estacion) throws TRANSMILENIOException {
        try {
            this.iEstacionEntityManagerEJB.deleteEstacion(estacion);
        } catch (TRANSMILENIOException e) {
            throw new TRANSMILENIOException(e.toString());
        }
    }

}