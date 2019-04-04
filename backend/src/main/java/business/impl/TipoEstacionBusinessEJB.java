package business.impl;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import business.intf.IEstacionBusinessEJB;
import business.intf.ITipoEstacionBusinessEJB;
import entitymanagers.intf.IEstacionEntityManagerEJB;
import entitymanagers.intf.ITipoEstacionEntityManagerEJB;
import modelos.Estacion;
import modelos.TipoEstacion;
import utils.TRANSMILENIOException;

@Stateless
public class TipoEstacionBusinessEJB implements ITipoEstacionBusinessEJB {

    @Inject
    private ITipoEstacionEntityManagerEJB iTipoEstacionEntityManagerEJB;

    @Override
    public List<TipoEstacion> getAllTipoEstacion() throws TRANSMILENIOException {
        try {
            return this.iTipoEstacionEntityManagerEJB.getAllTipoEstacion();
        } catch (TRANSMILENIOException e) {
            throw new TRANSMILENIOException(e.toString());
        }
    }

}