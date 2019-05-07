package apis;

import static spark.Spark.*;

import java.io.Console;
import java.math.BigDecimal;

import modelos.Estacion;
import com.google.gson.Gson;

import org.hibernate.Query;
import org.hibernate.Session;
import utils.ConsultaDTO;
import utils.DAOGenerico;
import utils.HibernateUtil;
import utils.JsonTransformer;

import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ReporteAPI {

    public static void routes() {

        get("/ruta-alimentadora/:idEstacion/:idRutaAlimen", (req, res) -> {
            String response = "";
            Map<String, List<ConsultaDTO>> aux = new HashMap<>();
            BigDecimal idEstacion = new BigDecimal(req.params(":idEstacion"));
            BigDecimal idRutaAlimen = new BigDecimal(req.params(":idRutaAlimen"));
            try {
                Session se = HibernateUtil.getSessionFactory().openSession();
                Query query = se.createQuery("SELECT e.nombre, ra.codigo, ra.nombre, tp.nombre, p.nombre, "
                        + " (CASE h.rango WHEN 'E' THEN 'Lunes-Viernes' WHEN 'S' THEN 'Sábado' ELSE 'Domingo' END), "
                        + " TO_CHAR(h.horaComienzo, 'HH24:MI'), TO_CHAR(h.horaFin, 'HH24:MI') "
                        + " FROM Estacion as e JOIN e.rutaAlimens as ra JOIN ra.parRutAlims as pra "
                        + " JOIN pra.paradero as p JOIN p.tipoParadero as tp JOIN p.paraHoras as ph"
                        + " JOIN ph.horario as h WHERE e.idEstacion =: idEstacion "
                        + " AND ra.idRutaAlimen =: idRuta ORDER BY pra.orden");
                query.setParameter("idEstacion", idEstacion);
                List<ConsultaDTO> lista = (List<ConsultaDTO>) query.setParameter("idRuta", idRutaAlimen).list();
                res.status(!lista.isEmpty() ? 200 : 400);
                se.close();

                aux.put("lista", lista);
                response = new Gson().toJson(aux);
            } catch (Exception e) {
                res.status(404);
            }
            return response;
        });

        get("/ruta-troncal/:idEstacion/:sentido", (req, res) -> {
            String response = "";
            List<Map<String, Object>> aux = new ArrayList<>();
            BigDecimal idEstacion = new BigDecimal(req.params(":idEstacion"));
            BigDecimal idRutaAlimen = new BigDecimal(req.params(":idRutaAlimen"));
            Session se = HibernateUtil.getSessionFactory().openSession();
            Query query = se
                    .createQuery("select e.nombre, r.codigo, r.nombre, p.nombre, h.rango, h.horaComienzo, h.horaFin  "
                            + " from Estacion as e " + " join e.rutaAlimens as r join r.parRutAlims as pra  "
                            + " join pra.paradero as p " + " join p.paraHoras ad ph" + " join ph.horario as h "
                            + " where e.idEstacion =: idEstacion " + " and r.idRutaAlimen =: idRuta");
            query.setParameter("idEstacion", idEstacion);
            query.setParameter("idRuta", idRutaAlimen);
            List<ConsultaDTO> lista = (List<ConsultaDTO>) query.list();
            return lista;
        }, new JsonTransformer());
    }

}
