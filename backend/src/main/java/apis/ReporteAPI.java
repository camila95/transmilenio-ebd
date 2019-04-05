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

import java.util.HashMap;
import java.util.List;

public class ReporteAPI {

    public static void routes() {

            get("/:idEstacion/:idRutaAlimen", (req, res) -> {
                BigDecimal idEstacion = new BigDecimal(req.params(":idEstacion"));
                BigDecimal idRutaAlimen = new BigDecimal(req.params(":idRutaAlimen"));
                Session se = HibernateUtil.getSessionFactory().openSession();
                Query query = se.createQuery(
                    "select e.nombre, r.codigo, r.nombre "
                    +" from Estacion as e "
                    +" join e.rutaAlimens as r" 
                    +" join r.parRutAlims as pra");
                List<ConsultaDTO> lista = query.list();
                 obtenerDatos(lista);
                return lista;
            });
    }

    public static void obtenerDatos(List<ConsultaDTO> lista){
        if(lista.isEmpty()){
            for (int i = 0; i < lista.size(); i++) {
                System.out.println(lista.get(i).getNombre()+" "+lista.get(i).getCodigo()+" "+lista.get(i).getNombreRuta()); 
            }
        }


    }

}
