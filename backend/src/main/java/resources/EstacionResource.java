package resources;

import java.math.BigDecimal;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import business.intf.IEstacionBusinessEJB;
import modelos.Estacion;
import utils.TRANSMILENIOException;

@Stateless
@Path("/estacion")
public class EstacionResource {

    @Inject
    private IEstacionBusinessEJB estacionBusinessEJB;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/buscarEstacion/{idTipoEstacion}")
    public Response getEstacionByTipoEstacion(BigDecimal idTipoEstacion) {
        try {
            return Response.ok(this.estacionBusinessEJB.getEstacionByTipoEstacion(idTipoEstacion)).build();
        } catch (TRANSMILENIOException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getCause()).build();
        }
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/crearEstacion/{estacion}")
    public Response createEstacion(@PathParam("estacion") Estacion estacion) {
        try {
            this.estacionBusinessEJB.createEstacion(estacion);
            return Response.ok().build();
        } catch (TRANSMILENIOException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getCause()).build();
        }
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    @Path("/editarEstacion/{estacion}")
    public Response updateEstacion(@QueryParam("estacion") Estacion estacion) {
        try {
            this.estacionBusinessEJB.updateEstacion(estacion);
            return Response.ok().build();
        } catch (TRANSMILENIOException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getCause()).build();
        }
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    @Path("/borrarEstacion/{estacion}")
    public Response deleteEstacion(@QueryParam("estacion") Estacion estacion) {
        try {
            this.estacionBusinessEJB.deleteEstacion(estacion);
            return Response.status(Response.Status.OK).type(MediaType.APPLICATION_JSON + ";charset=utf-8").build();
        } catch (TRANSMILENIOException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getCause()).build();
        }
    }

}