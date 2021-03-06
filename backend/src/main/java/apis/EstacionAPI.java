package apis;

import static spark.Spark.*;

import java.math.BigDecimal;
import modelos.Estacion;
import modelos.TipoEstacion;
import modelos.Troncal;
import modelos.dto.EstacionDTO;

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
            Map<String, List<Map<String, Object>>> result = new HashMap<>();
            try {
                Session se = HibernateUtil.getSessionFactory().openSession();
                List<Estacion> estacion = (List<Estacion>) se.createQuery(
                        "select e from Estacion e join fetch e.tipoEstacion join fetch e.troncal order by e.nombre")
                        .list();
                res.status(!estacion.isEmpty() ? 200 : 400);
                se.close();

                for (int i = 0; i < estacion.size(); i++) {
                    aux.add(estacion.get(i).toMap());
                }
                result.put("listasEstacion", aux);
                response = new Gson().toJson(result);
            } catch (Exception e) {
                res.status(404);
            }
            return response;
        });

        get("/:id", (req, res) -> {
            BigDecimal id = new BigDecimal(req.params(":id"));
            DAOGenerico<Estacion> dao = new DAOGenerico<Estacion>(Estacion.class);
            Map<String, List<Map<String, Object>>> result = new HashMap<>();
            List<Map<String, Object>> aux = new ArrayList<>();
            Estacion estacion = dao.consultarPorId(id);
            String response = "";
            try {
                for (int i = 0; i < 1; i++) {
                    aux.add(estacion.toMap());
                }
                result.put("listasEstacion", aux);
                response = new Gson().toJson(result);
            } catch (Exception e) {
                res.status(404);
            }
            return response;
        });

        get("/idTipoEstacion/:id", (req, res) -> {
            BigDecimal id = new BigDecimal(req.params(":id"));
            String response = "";
            List<Map<String, Object>> aux = new ArrayList<>();
            Map<String, List<Map<String, Object>>> result = new HashMap<>();
            try {
                Session se = HibernateUtil.getSessionFactory().openSession();
                Query q = se.createQuery(
                        "select e from Estacion e join fetch e.tipoEstacion join fetch e.troncal where e.tipoEstacion.idTipoEsta =: idTipoEsta order by e.nombre ");
                List<Estacion> tp = (List<Estacion>) q.setParameter("idTipoEsta", id).list();
                res.status(!tp.isEmpty() ? 200 : 400);
                se.close();
                for (int i = 0; i < tp.size(); i++) {
                    aux.add(tp.get(i).toMap());
                }
                result.put("listasEstaciones", aux);
                response = new Gson().toJson(result);
            } catch (Exception e) {
                res.status(404);
            }
            return response;
        });

        post("/", (req, res) -> {
            EstacionDTO estacionDTO = new Gson().fromJson(req.body(), EstacionDTO.class);
            Estacion estacion = converseDtoTOEntity(estacionDTO, false);
            DAOGenerico<Estacion> dao = new DAOGenerico<Estacion>(Estacion.class);
            dao.insertar(estacion);
            return new Gson().toJson(estacion.toMap());
        });
        put("/", (req, res) -> {
            EstacionDTO estacionReq = new Gson().fromJson(req.body(), EstacionDTO.class);
            Estacion estacion = converseDtoTOEntity(estacionReq, true);
            DAOGenerico<Estacion> dao = new DAOGenerico<Estacion>(Estacion.class);
            String response = "{}";
            try {
                dao.actualizar(estacion);
                response = new Gson().toJson(estacion.toMap());
            } catch (Exception e) {
                res.status(404);
            }
            // dao.insertar(estacion);
            return response;
        });
        delete("/:id", "application/json", (req, res) -> {
            BigDecimal id = new BigDecimal(req.params(":id"));
            DAOGenerico<Estacion> dao = new DAOGenerico<Estacion>(Estacion.class);
            Estacion estacion = dao.consultarPorId(id);
            String response = "{}";
            try {
                dao.eliminar(estacion);
                response = new Gson().toJson(estacion.toMap());
            } catch (Exception e) {
                res.status(404);
            }
            return response;
        });
    }

    public static Estacion converseDtoTOEntity(EstacionDTO estacionDTO, Boolean isEditar) {
        Estacion estacion = new Estacion();
        if (isEditar) {
            estacion.setIdEstacion(estacionDTO.getIdEstacion());
        }
        estacion.setNombre(estacionDTO.getNombre());
        estacion.setDireccion(estacionDTO.getDireccion());
        estacion.setLocalidad(estacionDTO.getLocalidad());
        estacion.setLatitud(estacionDTO.getLatitud());
        estacion.setLongitud(estacionDTO.getLongitud());

        TipoEstacion tipoEstacion = new TipoEstacion();
        tipoEstacion.setIdTipoEsta(estacionDTO.getIdTipoEstacion());
        estacion.setTipoEstacion(tipoEstacion);

        Troncal troncal = new Troncal();
        troncal.setIdTroncal(estacionDTO.getIdTroncal());
        estacion.setTroncal(troncal);

        estacion.setOrden(estacionDTO.getOrden());
        estacion.setEstaIncial(estacionDTO.getEstaIncial());
        estacion.setEstaFinal(estacionDTO.getEstaFinal());
        return estacion;
    }

}
