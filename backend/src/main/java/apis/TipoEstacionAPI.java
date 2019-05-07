package apis;

import static spark.Spark.*;

import java.math.BigDecimal;
import modelos.TipoEstacion;
import oracle.net.aso.i;
import modelos.Estacion;
import com.google.gson.Gson;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ArrayNode;
import org.codehaus.jackson.node.ObjectNode;
import org.hibernate.Query;
import org.hibernate.Session;

import utils.DAOGenerico;
import utils.HibernateUtil;
import utils.JsonTransformer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TipoEstacionAPI {

    public static void routes() {

        get("/", (req, res) -> {
            String response = "";
            List<Map<String, Object>> aux = new ArrayList<>();
            Map<String, List<Map<String, Object>>> result = new HashMap<>();
            try {
                Session se = HibernateUtil.getSessionFactory().openSession();
                List<TipoEstacion> tp = (List<TipoEstacion>) se
                        .createQuery("SELECT ti FROM TipoEstacion ti ORDER BY ti.nombre").list();
                res.status(!tp.isEmpty() ? 200 : 400);
                se.close();
                for (int i = 0; i < tp.size(); i++) {
                    aux.add(tp.get(i).toMap());
                }
                result.put("listasTroncales", aux);
                response = new Gson().toJson(result);
            } catch (Exception e) {
                res.status(404);
            }
            return response;
        });

        get("/:id/", (req, res) -> {
            BigDecimal id = new BigDecimal(req.params(":id"));
            DAOGenerico<TipoEstacion> dao = new DAOGenerico<TipoEstacion>(TipoEstacion.class);
            TipoEstacion operador = dao.consultarPorId(id);
            String response = "{}";
            try {
                response = new Gson().toJson(operador.toMap());
            } catch (Exception e) {
                res.status(404);
            }
            return response;
        });
        post("/", (req, res) -> {
            TipoEstacion operador = new Gson().fromJson(req.body(), TipoEstacion.class);
            DAOGenerico<TipoEstacion> dao = new DAOGenerico<TipoEstacion>(TipoEstacion.class);
            dao.insertar(operador);
            // return new Gson().toJson(operador.toMap());
            return operador;
        });
        put("/:id/", (req, res) -> {
            BigDecimal id = new BigDecimal(req.params(":id"));
            DAOGenerico<TipoEstacion> dao = new DAOGenerico<TipoEstacion>(TipoEstacion.class);
            TipoEstacion operador = dao.consultarPorId(id);
            TipoEstacion operadorReq = new Gson().fromJson(req.body(), TipoEstacion.class);
            String response = "{}";
            try {
                operador.setNombre(operadorReq.getNombre());
                // operador.setEstacions(operadorReq.getEstacions());
                dao.actualizar(operador);
                // response = new Gson().toJson(operador.toMap());
            } catch (Exception e) {
                res.status(404);
            }
            dao.insertar(operador);
            return response;
        });
        delete("/:id/", (req, res) -> {
            BigDecimal id = new BigDecimal(req.params(":id"));
            DAOGenerico<TipoEstacion> dao = new DAOGenerico<TipoEstacion>(TipoEstacion.class);
            TipoEstacion operador = dao.consultarPorId(id);
            String response = "{}";
            try {
                dao.eliminar(operador);
            } catch (Exception e) {
                res.status(404);
            }
            return response;
        });

    }

}
