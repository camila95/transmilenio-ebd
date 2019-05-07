package apis;

import static spark.Spark.*;

import java.math.BigDecimal;
import modelos.Estacion;
import com.google.gson.Gson;

import org.hibernate.Query;
import org.hibernate.Session;

import utils.DAOGenerico;
import utils.HibernateUtil;
import modelos.RutaAlimen;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class RutaAlimenAPI {

    public static void routes() {

        get("/:idEstacion", (req, res) -> {
            String response = "";
            List<Map<String, Object>> aux = new ArrayList<>();
            BigDecimal idEstacion = new BigDecimal(req.params(":idEstacion"));
            Map<String, List<Map<String, Object>>> result = new HashMap<>();
            try {
                Session se = HibernateUtil.getSessionFactory().openSession();
                Query q = se.createQuery(
                        "select ra from RutaAlimen ra join fetch ra.operador join fetch ra.estacion where ra.estacion.idEstacion =: idEstacion");
                List<RutaAlimen> rutaAlimen = (List<RutaAlimen>) q.setParameter("idEstacion", idEstacion).list();

                res.status(!rutaAlimen.isEmpty() ? 200 : 400);
                se.close();
                for (int i = 0; i < rutaAlimen.size(); i++) {
                    aux.add(rutaAlimen.get(i).toMap());
                }
                result.put("listasRutaAlimen", aux);
                response = new Gson().toJson(result);
            } catch (Exception e) {
                res.status(404);
            }
            return response;
        });
    }

}
