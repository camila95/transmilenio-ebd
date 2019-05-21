package apis;

import static spark.Spark.*;

import java.math.BigDecimal;

import modelos.Operador;
import modelos.dto.OperadorDTO;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.internal.LinkedTreeMap;

import org.codehaus.jackson.map.ObjectMapper;

import utils.DAOGenerico;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        post("/insertar-lista/", (req, res) -> {
            String response = "{}";
            Map<String, Object> mapOperaciones = (Map<String, Object>) new Gson().fromJson(req.body(), HashMap.class);
            ObjectMapper mapper = new ObjectMapper();
            try {
                List<Operador> lista = (List<Operador>) mapOperaciones.get("listaOperadores");

                DAOGenerico<Operador> dao = new DAOGenerico<Operador>(Operador.class);
                if (!lista.isEmpty()) {
                    for (int i = 0; i < lista.size(); i++) {

                        Object objetoOperador = lista.get(i);
                        LinkedTreeMap<Object, Object> t = (LinkedTreeMap) objetoOperador;

                        Operador operador = new Operador();
                        if (t.get("nombre").toString().length() > 30) {
                            operador.setNombre(t.get("nombre").toString().substring(0, 30));
                        } else {
                            operador.setNombre(t.get("nombre").toString());
                        }

                        if (t.get("telefono").toString().length() > 15) {
                            operador.setTelefono(t.get("telefono").toString().substring(0, 15));
                        } else {
                            operador.setTelefono(t.get("telefono").toString());
                        }

                        if (t.get("representante").toString().length() > 30) {
                            operador.setRepresentante(t.get("representante").toString().substring(0, 30));
                        } else {
                            operador.setRepresentante(t.get("representante").toString());
                        }

                        if (t.get("direccion").toString().length() > 45) {
                            operador.setDireccion(t.get("direccion").toString().substring(0, 45));
                        } else {
                            operador.setDireccion(t.get("direccion").toString());
                        }
                        operador.setPaginaWeb(t.get("paginaWeb").toString());
                        operador.setIdOperador(null);
                        dao.insertar(operador);
                    }
                }
            } catch (Exception e) {
                res.status(404);
            }
            return response;
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
