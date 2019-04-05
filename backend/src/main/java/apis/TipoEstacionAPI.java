package apis;

import static spark.Spark.*;

import java.math.BigDecimal;
import modelos.TipoEstacion;
import com.google.gson.Gson;

import org.hibernate.Query;
import org.hibernate.Session;

import utils.DAOGenerico;
import utils.HibernateUtil;
import utils.JsonTransformer;

import java.util.HashMap;
import java.util.List;

public class TipoEstacionAPI {

    public static void routes() {

        get("/", (req, res) -> {
            Session se = HibernateUtil.getSessionFactory().openSession();
            Query query = se.createQuery("from TipoEstacion");
            List<TipoEstacion> tp = query.getResultList();
            res.status(!tp.isEmpty() ? 200 : 400);
            return new Gson().toJson(tp);
        }, new JsonTransformer());

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
            return new Gson().toJson(operador.toMap());
        });
        put("/:id/", (req, res) -> {
            BigDecimal id = new BigDecimal(req.params(":id"));
            DAOGenerico<TipoEstacion> dao = new DAOGenerico<TipoEstacion>(TipoEstacion.class);
            TipoEstacion operador = dao.consultarPorId(id);
            TipoEstacion operadorReq = new Gson().fromJson(req.body(), TipoEstacion.class);
            String response = "{}";
            try {
                operador.setNombre(operadorReq.getNombre());
                operador.setEstacions(operadorReq.getEstacions());
                dao.actualizar(operador);
                response = new Gson().toJson(operador.toMap());
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
