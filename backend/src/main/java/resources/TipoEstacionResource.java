package resources;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import business.intf.ITipoEstacionBusinessEJB;
import utils.TRANSMILENIOException;

@Stateless
@Path("/tipoEstacion")
public class TipoEstacionResource {

    @Inject
    private ITipoEstacionBusinessEJB tipoEstacionBusinessEJB;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getAll")
    public Response getAllTipoEstacion() {
        try {
            return Response.ok(this.tipoEstacionBusinessEJB.getAllTipoEstacion()).build();
        } catch (TRANSMILENIOException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getCause()).build();
        }
    }
}