import static spark.Spark.*;

import java.util.List;
import utils.*;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import javax.persistence.EntityManager;
import javax.servlet.MultipartConfigElement;

import modelos.RutaAlimen;
import modelos.Test;
import utils.HibernateUtil;
import apis.*;

import com.google.gson.Gson;

public class TestAPI {

    public static <TipoEstacion> void main(String[] args) {

        // after((req, res) -> { res.type("application/json"); });

        Session se = HibernateUtil.getSessionFactory().openSession();
        se.close();

        port(8081);
        TestAPI testAPI = new TestAPI();
        testAPI.corsFilter();

        path("/operador", () -> {
            OperadorAPI.routes();
        });

        path("/estaciones", () -> {
            EstacionAPI.routes();
        });

        path("/tipo-estaciones", () -> {
            TipoEstacionAPI.routes();
        });

        path("/troncales", () -> {
            TroncalAPI.routes();
        });

        path("/reporte", () -> {
            ReporteAPI.routes();
        });

        path("/ruta-alimen", () -> {
            RutaAlimenAPI.routes();
        });

        path("/generacion-masiva", () -> {
            GeneracionMasivaAPI.routes();
        });

        /*
         * get("/test", (req, res) -> { res.type("application/json"); return new
         * Gson().toJson( new StandardResponse(StatusResponse.SUCCESS, new
         * Gson().toJsonTree(ControladorTest.get(1)))); });
         */
        /*
         * SessionFactory sf = new Configuration().configure().buildSessionFactory();
         * 
         * get("/", (req, res) -> { EntityManager session = sf.createEntityManager();
         * try { req.attribute("org.eclipse.jetty.multipartConfig", new
         * MultipartConfigElement("")); //Integer id =
         * Integer.parseInt(req.queryParams("id")); //String name =
         * req.queryParams("name");
         * 
         * Test task = new Test(); task.setId(1); task.setDescripcion("Test");
         * 
         * session.getTransaction().begin(); session.persist(task);
         * session.getTransaction().commit(); return "Ok"; } catch (Exception e) {
         * return "Error: " + e.getMessage(); } finally { if (session.isOpen()) {
         * session.close(); } } });
         */
    }

    public static void corsFilter() {
        // Filtro para convertir la salida a formato JSON
        options("/*", (request, response) -> {
            String accessControlRequestHeaders = request.headers("Access-Control-Request-Headers");
            if (accessControlRequestHeaders != null) {
                response.header("Access-Control-Allow-Headers", accessControlRequestHeaders);
            }
            String accessControlRequestMethod = request.headers("Access-Control-Request-Method");
            if (accessControlRequestMethod != null) {
                response.header("Access-Control-Allow-Methods", accessControlRequestMethod);
            }
            String mensajeOk = "{'id':10,'cantidad':0}";
            return new Gson().toJson(mensajeOk).toString();

        });
        before((request, response) -> response.header("Access-Control-Allow-Origin", "*"));
        after((request, response) -> response.type("application/json"));
    }
}
