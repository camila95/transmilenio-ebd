package apis;

import static spark.Spark.*;

import java.math.BigDecimal;
import modelos.Estacion;
import com.google.gson.Gson;

import org.hibernate.Query;
import org.hibernate.Session;

import utils.DAOGenerico;
import utils.HibernateUtil;

import java.util.HashMap;
import java.util.List;

public class EstacionAPI {

    public static void routes() {

        get("/", (req, res) -> {
            Session se = HibernateUtil.getSessionFactory().openSession();
            Query query = se.createQuery("from Estacion");
            List<Estacion> tp = query.getResultList();
            res.status(!tp.isEmpty() ? 200 : 400);
            return new Gson().toJson(tp);
        });

        get("/:id/", (req, res) -> {
            BigDecimal id = new BigDecimal(req.params(":id"));
            DAOGenerico<Estacion> dao = new DAOGenerico<Estacion>(Estacion.class);
            Estacion estacion = dao.consultarPorId(id);
            String response = "{}";
            try {
                response = new Gson().toJson(estacion.toMap());
            } catch (Exception e) {
                res.status(404);
            }
            return response;
        });
        post("/", (req, res) -> {
            Estacion estacion = new Gson().fromJson(req.body(), Estacion.class);
            DAOGenerico<Estacion> dao = new DAOGenerico<Estacion>(Estacion.class);
            dao.insertar(estacion);
            return new Gson().toJson(estacion.toMap());
        });
        put("/:id/", (req, res) -> {
            BigDecimal id = new BigDecimal(req.params(":id"));
            DAOGenerico<Estacion> dao = new DAOGenerico<Estacion>(Estacion.class);
            Estacion estacion = dao.consultarPorId(id);
            Estacion estacionReq = new Gson().fromJson(req.body(), Estacion.class);
            String response = "{}";
            try {
                estacion.setNombre(estacionReq.getNombre());
                estacion.setDireccion(estacionReq.getDireccion());
                estacion.setEstaFinal(estacionReq.getEstaFinal());
                estacion.setEstaIncial(estacionReq.getEstaIncial());
                estacion.setLatitud(estacionReq.getLatitud());
                estacion.setLocalidad(estacionReq.getLocalidad());
                estacion.setLongitud(estacionReq.getLongitud());
                estacion.setOrden(estacionReq.getOrden());
                estacion.setRutaAlimens(estacionReq.getRutaAlimens());
                estacion.setTipoEstacion(estacionReq.getTipoEstacion());
                estacion.setTroncal(estacionReq.getTroncal());
                estacion.setVagons(estacionReq.getVagons());
                dao.actualizar(estacion);
                response = new Gson().toJson(estacion.toMap());
            } catch (Exception e) {
                res.status(404);
            }
            dao.insertar(estacion);
            return response;
        });
        delete("/:id/", (req, res) -> {
            BigDecimal id = new BigDecimal(req.params(":id"));
            DAOGenerico<Estacion> dao = new DAOGenerico<Estacion>(Estacion.class);
            Estacion estacion = dao.consultarPorId(id);
            String response = "{}";
            try {
                dao.eliminar(estacion);
            } catch (Exception e) {
                res.status(404);
            }
            return response;
        });
    }

}
