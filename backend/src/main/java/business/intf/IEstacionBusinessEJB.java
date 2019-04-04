package business.intf;

import java.math.BigDecimal;
import java.util.List;
import javax.ejb.Local;
import modelos.Estacion;
import utils.TRANSMILENIOException;

@Local
public interface IEstacionBusinessEJB {

    public void createEstacion(Estacion estacion) throws TRANSMILENIOException;

    public List<Estacion> getEstacionByTipoEstacion(BigDecimal idTipoEstacion) throws TRANSMILENIOException;

    public void updateEstacion(Estacion estacion) throws TRANSMILENIOException;

    public void deleteEstacion(Estacion estacion) throws TRANSMILENIOException;

}