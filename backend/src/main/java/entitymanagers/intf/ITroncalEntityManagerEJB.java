package entitymanagers.intf;

import java.math.BigDecimal;
import java.util.List;
import javax.ejb.Local;
import modelos.Estacion;
import modelos.Troncal;
import utils.TRANSMILENIOException;

@Local
public interface ITroncalEntityManagerEJB {

    public List<Troncal> getAllTroncales() throws TRANSMILENIOException;

}