package apis;

import static spark.Spark.*;

import java.math.BigDecimal;
import modelos.Estacion;
import com.google.gson.Gson;

import org.hibernate.Query;
import org.hibernate.Session;

import utils.DAOGenerico;
import utils.HibernateUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class EstacionAPI {

    public static void routes() {

        get("/", (req, res) -> {
            String response = "";
            List<Map<String, Object>> aux = new ArrayList<>();
            try {
                Session se = HibernateUtil.getSessionFactory().openSession();
                List<Estacion> estacion = (List<Estacion>) se.createQuery("from Estacion").list();
                res.status(!estacion.isEmpty() ? 200 : 400);
                se.close();
                for (int i = 0; i < estacion.size(); i++) {
                    aux.add(estacion.get(i).toMap());
                }
                response = new Gson().toJson(aux);
            } catch (Exception e) {
                res.status(404);
            }
            return response;
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
