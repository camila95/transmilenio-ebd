
package entitymanagers.intf;

import java.math.BigDecimal;
import java.util.List;
import javax.ejb.Local;
import modelos.Estacion;
import modelos.TipoEstacion;
import utils.TRANSMILENIOException;

@Local
public interface ITipoEstacionEntityManagerEJB {

    public List<TipoEstacion> getAllTipoEstacion() throws TRANSMILENIOException;
}
