package business.impl;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import business.intf.IEstacionBusinessEJB;
import business.intf.ITroncalBusinessEJB;
import entitymanagers.intf.IEstacionEntityManagerEJB;
import entitymanagers.intf.ITroncalEntityManagerEJB;
import modelos.Estacion;
import modelos.Troncal;
import utils.TRANSMILENIOException;

@Stateless
public class TroncalBusinessEJB implements ITroncalBusinessEJB {

    @Inject
    private ITroncalEntityManagerEJB iTroncalEntityManagerEJB;

    @Override
    public List<Troncal> getAllTroncales() throws TRANSMILENIOException {
        try {
            return this.iTroncalEntityManagerEJB.getAllTroncales();
        } catch (TRANSMILENIOException e) {
            throw new TRANSMILENIOException(e.toString());
        }
    }

}