package business.intf;

import java.util.List;

import javax.ejb.Local;

import modelos.TipoEstacion;
import utils.TRANSMILENIOException;

@Local
public interface ITipoEstacionBusinessEJB {

    public List<TipoEstacion> getAllTipoEstacion() throws TRANSMILENIOException;

}