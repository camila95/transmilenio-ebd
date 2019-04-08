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

        get("/:idEstacion/:idRutaAlimen", (req, res) -> {
            String response = "";
            List<Map<String, Object>> aux = new ArrayList<>();
            BigDecimal idEstacion = new BigDecimal(req.params(":idEstacion"));
            BigDecimal idRutaAlimen = new BigDecimal(req.params(":idRutaAlimen"));
            Session se = HibernateUtil.getSessionFactory().openSession();
            Query query = se.createQuery("select e.nombre, r.codigo, r.nombre, p.nombre, h.rango, h.horaComienzo, h.horaFin  "
                    + " from Estacion as e "
                    + " join e.rutaAlimens as r join r.parRutAlims as pra  "
                    + " join pra.paradero as p "
                    + " join p.paraHoras ad ph"
                    + " join ph.horario as h "
                    + " where e.idEstacion =: idEstacion "
                    + " and r.idRutaAlimen =: idRuta");
                query.setParameter("idEstacion", idEstacion);
                query.setParameter("idRuta", idRutaAlimen);
            List<ConsultaDTO> lista = (List<ConsultaDTO>) query.list();
            return lista;
        }, new JsonTransformer());
    }

    public static void obtenerDatos(List<ConsultaDTO> lista) {
        if (lista.isEmpty()) {
            for (int i = 0; i < lista.size(); i++) {
                System.out.println(lista.get(i).getNombre() + " " + lista.get(i).getCodigo());
            }
        }

    }

}
