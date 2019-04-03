import static spark.Spark.*;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.EntityManager;
import javax.servlet.MultipartConfigElement;

import modelos.Test;
import controladores.ControladorTest;

import com.google.gson.Gson;
import utils.StandardResponse;
import utils.StatusResponse;

public class TestAPI {

    public static void main(String[] args) {
      path("/api/", () -> {
        get("/test", (req, res) -> {
          res.type("application/json");
          return new Gson().toJson(
            new StandardResponse(StatusResponse.SUCCESS, new Gson().toJsonTree(ControladorTest.get(1))));
          });
      });
        /* SessionFactory sf = new Configuration().configure().buildSessionFactory();

        get("/", (req, res) -> {
            EntityManager session = sf.createEntityManager();
            try {
                req.attribute("org.eclipse.jetty.multipartConfig", new MultipartConfigElement(""));
                //Integer id = Integer.parseInt(req.queryParams("id"));
                //String name = req.queryParams("name");

                Test task = new Test();
                task.setId(1);
                task.setDescripcion("Test");

                session.getTransaction().begin();
                session.persist(task);
                session.getTransaction().commit();
                return "Ok";
            } catch (Exception e) {
                return "Error: " + e.getMessage();
            } finally {
                if (session.isOpen()) {
                    session.close();
                }
            }
        }); */
    }
}
