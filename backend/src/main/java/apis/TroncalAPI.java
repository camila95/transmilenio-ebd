package apis;

import static spark.Spark.*;

import java.math.BigDecimal;

import modelos.Troncal;
import com.google.gson.Gson;

import org.hibernate.Query;
import org.hibernate.Session;

import utils.DAOGenerico;
import utils.HibernateUtil;
import utils.JsonTransformer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class TroncalAPI {

    public static void routes() {

        get("/", (req, res) -> {
            String response = "";
            List<Map<String, Object>> aux = new ArrayList<>();
            try {
                Session se = HibernateUtil.getSessionFactory().openSession();
                List<Troncal> troncal = (List<Troncal>) se.createQuery("from Troncal").list();
                res.status(!troncal.isEmpty() ? 200 : 400);
                se.close();
                for (int i = 0; i < troncal.size(); i++) {
                    aux.add(troncal.get(i).toMap());
                }
                response = new Gson().toJson(aux);
            } catch (Exception e) {
                res.status(404);
            }
            return response;
        });

        get("/:id/", (req, res) -> {
            BigDecimal id = new BigDecimal(req.params(":id"));
            DAOGenerico<Troncal> dao = new DAOGenerico<Troncal>(Troncal.class);
            Troncal troncal = dao.consultarPorId(id);
            String response = "{}";
            try {
                response = new Gson().toJson(troncal.toMap());
            } catch (Exception e) {
                res.status(404);
            }
            return response;
        });
        post("/", (req, res) -> {
            Troncal troncal = new Gson().fromJson(req.body(), Troncal.class);
            DAOGenerico<Troncal> dao = new DAOGenerico<Troncal>(Troncal.class);
            dao.insertar(troncal);
            return new Gson().toJson(troncal.toMap());
        });
        put("/:id/", (req, res) -> {
            BigDecimal id = new BigDecimal(req.params(":id"));
            DAOGenerico<Troncal> dao = new DAOGenerico<Troncal>(Troncal.class);
            Troncal troncal = dao.consultarPorId(id);
            Troncal operadorReq = new Gson().fromJson(req.body(), Troncal.class);
            String response = "{}";
            try {
                troncal.setOperador(operadorReq.getOperador());
                troncal.setNombreVia(operadorReq.getNombreVia());
                troncal.setZona(operadorReq.getZona());
                troncal.setColorZona(operadorReq.getColorZona());
                troncal.setNombreZona(operadorReq.getNombreZona());
                troncal.setEstacions(operadorReq.getEstacions());
                dao.actualizar(troncal);
                response = new Gson().toJson(troncal.toMap());
            } catch (Exception e) {
                res.status(404);
            }
            dao.insertar(troncal);
            return response;
        });
        delete("/:id/", (req, res) -> {
            BigDecimal id = new BigDecimal(req.params(":id"));
            DAOGenerico<Troncal> dao = new DAOGenerico<Troncal>(Troncal.class);
            Troncal operador = dao.consultarPorId(id);
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
