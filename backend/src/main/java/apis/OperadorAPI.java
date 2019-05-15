package apis;

import static spark.Spark.*;

import java.math.BigDecimal;

import modelos.Operador;
import com.google.gson.Gson;

import utils.DAOGenerico;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OperadorAPI {

    public static void routes() {

        get("/:id/", (req, res) -> {
            BigDecimal id = new BigDecimal(req.params(":id"));
            DAOGenerico<Operador> dao = new DAOGenerico<Operador>(Operador.class);
            Operador operador = dao.consultarPorId(id);
            String response = "{}";
            try {
                response = new Gson().toJson(operador.toMap());
            } catch (Exception e) {
                res.status(404);
            }
            return response;
        });
        post("/", (req, res) -> {
            Operador operador = new Gson().fromJson(req.body(), Operador.class);
            DAOGenerico<Operador> dao = new DAOGenerico<Operador>(Operador.class);
            dao.insertar(operador);
            return new Gson().toJson(operador.toMap());
        });
        put("/:id/", (req, res) -> {
            BigDecimal id = new BigDecimal(req.params(":id"));
            DAOGenerico<Operador> dao = new DAOGenerico<Operador>(Operador.class);
            Operador operador = dao.consultarPorId(id);
            Operador operadorReq = new Gson().fromJson(req.body(), Operador.class);
            String response = "{}";
            try {
                operador.setNombre(operadorReq.getNombre());
                operador.setDireccion(operadorReq.getDireccion());
                operador.setPaginaWeb(operadorReq.getPaginaWeb());
                operador.setRepresentante(operadorReq.getRepresentante());
                operador.setTelefono(operadorReq.getTelefono());
                operador.setTroncals(operadorReq.getTroncals());
                operador.setRutaAlimens(operadorReq.getRutaAlimens());
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
            DAOGenerico<Operador> dao = new DAOGenerico<Operador>(Operador.class);
            Operador operador = dao.consultarPorId(id);
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
