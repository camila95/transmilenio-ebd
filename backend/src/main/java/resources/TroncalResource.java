package resources;

import java.math.BigDecimal;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import business.intf.ITroncalBusinessEJB;
import utils.TRANSMILENIOException;

@Stateless
@Path("/troncal")
public class TroncalResource {

    @Inject
    private ITroncalBusinessEJB troncalBusinessEJB;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getAll")
    public Response getAllTroncales() {
        try {
            return Response.ok(this.troncalBusinessEJB.getAllTroncales()).build();
        } catch (TRANSMILENIOException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getCause()).build();
        }
    }

}