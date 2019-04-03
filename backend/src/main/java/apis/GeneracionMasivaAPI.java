package apis;

import static spark.Spark.*;

import java.math.BigDecimal;

import modelos.Operador;
import com.google.gson.Gson;

import utils.DAOGenerico;
import utils.GeneradorMasivoThread;
import java.util.HashMap;

public class GeneracionMasivaAPI {

    public static int MAX_REGISTROS_POR_HILO = 100;

    public static void routes() {
        path("/generacion-masiva", () -> {
            post("/", (req, res) -> {
                Operador operador = Operador.fake();
                // 10000000
                HashMap<String, Double> data = new Gson().fromJson(req.body(), HashMap.class);
                for (String key : data.keySet()) {
                    System.out.println(data.get(key));
                }
                int numeroRegistros = data.get("tabla").intValue();
                int maxHilos = (int) Math.ceil(numeroRegistros / MAX_REGISTROS_POR_HILO);
                int registrosPorHilo = (int) (numeroRegistros / maxHilos);
                int registrosRestantes = (int) (numeroRegistros % maxHilos);
                int numeroHilos = (numeroRegistros < maxHilos) ? numeroRegistros : maxHilos;
                System.out.println(numeroHilos + " " + registrosPorHilo + " " + registrosRestantes);
                for (int i=0; i<numeroHilos; i++) {
                    new GeneradorMasivoThread<Operador>(registrosPorHilo, Operador.class).start();
                }
                new GeneradorMasivoThread<Operador>(registrosRestantes, Operador.class).start();
                return new Gson().toJson(operador.toMap());
            });
        });
    }

}
