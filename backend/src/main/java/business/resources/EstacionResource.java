import modelos.Estacion;

@Stateless
@Path("/estacion")
public class EstacionResource {

    private static final Logger LOGGER = Logger.getLogger(EstacionResource.class.getName());

    @Inject
    private IEstacionBusinessEJB estacionBusinessEJB;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/crearEstacion/{estacion}")
    public Response createEstacion(@PathParam("estacion") Estacion entidad) {
        try {
            return Response.ok(estacionBusinessEJB.createEstacion(entidad)).build();
        } catch (SIUCEException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getCause()).build();
        }
    }

}