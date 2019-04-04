package business.intf;

import java.util.List;

import javax.ejb.Local;

import modelos.Troncal;
import utils.TRANSMILENIOException;

@Local
public interface ITroncalBusinessEJB {

    public List<Troncal> getAllTroncales() throws TRANSMILENIOException;

}