package business.impl;

import business.intf.IEstacionBusinessEJB;
import entitymanagers.intf.IEstacionEntityManagerEJB;

@Stateless
public class EstacionBusinessEJB implements IEstacionBusinessEJB {

    @Inject
    private IEstacionEntityManagerEJB iEstacionEntityManagerEJB;

    public void createEstacion(String idTipoEstacion) {
        return iEstacionEntityManagerEJB.createEstacion(idTipoEstacion);
    }

}