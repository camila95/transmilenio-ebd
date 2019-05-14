package apis;

import static spark.Spark.*;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.Console;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import modelos.Estacion;
import com.google.gson.Gson;

import org.dom4j.Branch;
import org.hibernate.Session;
import utils.ConsultaAlimenDTO;
import utils.ConsultaTroncalDTO;
import utils.DAOGenerico;
import utils.HibernateUtil;
import utils.JsonTransformer;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReporteAPI {

    private static Connection conn;
    private static PreparedStatement ps;

    public static void routes() {

        get("/ruta-alimentadora/:idEstacion/:idRutaAlimen", (req, res) -> {
            String response = "";
            BigDecimal idEstacion = new BigDecimal(req.params(":idEstacion"));
            BigDecimal idRutaAlimen = new BigDecimal(req.params(":idRutaAlimen"));
            Map<String, List<ConsultaAlimenDTO>> result = new HashMap<>();

            Connection c = conexion();
            String consulta = "SELECT e.nombre, ra.codigo, ra.nombre, tp.nombre, p.nombre, "
                    + "DECODE(h.rango, 'E', 'Lunes-Viernes', 'S', 'Sábado', 'Domingo') DIAS, "
                    + "TO_CHAR(h.HORA_COMIENZO, 'HH24:MI:SS') HORA_INICIO, TO_CHAR(h.HORA_FIN, 'HH24:MI:SS')HORA_FIN "
                    + "FROM ESTACION e " + "INNER JOIN RUTA_ALIMEN ra ON ra.ID_ESTACION = e.ID_ESTACION "
                    + "INNER JOIN PAR_RUT_ALIM pra ON pra.ID_RUT_ALIM = ra.ID_RUTA_ALIMEN "
                    + "INNER JOIN PARADERO p ON p.ID_PARADERO = pra.ID_PARADERO "
                    + "INNER JOIN TIPO_PARADERO tp ON tp.ID_TIPO_PARADERO = p.ID_TIPO_PARADERO "
                    + "INNER JOIN PARA_HORA ph ON ph.ID_PARADERO = p.ID_PARADERO "
                    + "INNER JOIN HORARIO h ON h.ID_HORARIO = ph.ID_HORARIO " + "WHERE e.ID_ESTACION = ? "
                    + "AND ra.ID_RUTA_ALIMEN = ? " + "ORDER BY pra.ORDEN ";
            try {
                ps = conn.prepareStatement(consulta);
                ps.setBigDecimal(1, idEstacion);
                ps.setBigDecimal(2, idRutaAlimen);

                ResultSet rs = ps.executeQuery();
                List<ConsultaAlimenDTO> lista = new ArrayList<>();

                while (rs.next()) {
                    ConsultaAlimenDTO dto = new ConsultaAlimenDTO();
                    dto.setNombreEstacion(rs.getString(1));
                    dto.setCodigoRutaAlimen(rs.getString(2));
                    dto.setNombreRutaAlimen(rs.getString(3));
                    dto.setNombreTipoPara(rs.getString(4));
                    dto.setNombrePara(rs.getString(5));
                    dto.setRango(rs.getString(6));
                    dto.setHoraComienzo(rs.getString(7));
                    dto.setHoraFin(rs.getString(8));
                    lista.add(dto);
                }
                result.put("lista", lista);
                response = new Gson().toJson(result);
                ps.close();
                c.close();
            } catch (Exception e) {
                res.status(404);
            }
            return response;
        });

        get("/ruta-troncal/:idEstacion/:idSentido", (req, res) -> {
            String response = "";
            BigDecimal idEstacion = new BigDecimal(req.params(":idEstacion"));
            BigDecimal idSentido = new BigDecimal(req.params(":idSentido"));
            List<ConsultaTroncalDTO> lista = new ArrayList<>();

            String sentido = obtenerSentido(idSentido);
            Connection c = conexion();
            String consulta = "SELECT o.NOMBRE, e.NOMBRE, v.NOMBRE, r.NOMBRE, "
                    + "DECODE(r.SENTIDO, 'N', 'Norte', 'S','Sur','W','Oeste','E','Este','NW','Noroeste','NE','Noreste','SW','Suroeste','Sureste'), "
                    + "DECODE(h.rango, 'E', 'Lunes-Viernes', 'S', 'Sábado', 'Domingo'), "
                    + "TO_CHAR(h.HORA_COMIENZO, 'HH24:MI:SS'), TO_CHAR(h.HORA_FIN, 'HH24:MI:SS') " + "FROM ESTACION e "
                    + "INNER JOIN TIPO_ESTACION te ON te.ID_TIPO_ESTA = e.ID_TIPO_ESTA "
                    + "INNER JOIN TRONCAL t ON t.ID_TRONCAL = e.ID_TRONCAL "
                    + "INNER JOIN OPERADOR o ON o.ID_OPERADOR = t.ID_OPERADOR "
                    + "INNER JOIN VAGON v ON v.ID_ESTACION = e.ID_ESTACION "
                    + "INNER JOIN RUTA_ESTA re ON re.ID_VAGON = v.ID_VAGON "
                    + "INNER JOIN RUTA r ON r.ID_RUTA = re.ID_RUTA "
                    + "INNER JOIN RUTA_HORA rh ON rh.ID_RUTA = r.ID_RUTA "
                    + "INNER JOIN HORARIO h ON h.ID_HORARIO = rh.ID_HORARIO " + "WHERE e.ID_ESTACION = ? "
                    + "AND r.SENTIDO = ? " + "ORDER BY v.ID_VAGON ";

            try {
                ps = conn.prepareStatement(consulta);
                ps.setBigDecimal(1, idEstacion);
                ps.setString(2, sentido);

                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    ConsultaTroncalDTO dto = new ConsultaTroncalDTO();
                    dto.setNombreOperador(rs.getString(1));
                    dto.setNombreEstacion(rs.getString(2));
                    dto.setNombreVagon(rs.getString(3));
                    dto.setNombreRuta(rs.getString(4));
                    dto.setSentido(rs.getString(5));
                    dto.setRango(rs.getString(6));
                    dto.setHoraComienzo(rs.getString(7));
                    dto.setHoraFin(rs.getString(8));
                    lista.add(dto);
                }
                response = new Gson().toJson(lista);
                ps.close();
                c.close();
            } catch (Exception e) {
                res.status(404);
            }

            return obtenerArchivo(lista);
        });
    }

    public static Connection conexion() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
            conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "TRANSMI1", "Asdf1234$");
        } catch (Exception ex) {
            Logger.getLogger(ReporteAPI.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conn;
    }

    public static String obtenerSentido(BigDecimal idSentido) {
        String sentido = "";
        switch (idSentido.toString()) {
        case "1":
            sentido = "N";
            break;
        case "2":
            sentido = "S";
            break;
        case "3":
            sentido = "E";
            break;
        case "4":
            sentido = "O";
            break;
        case "5":
            sentido = "SE";
            break;
        case "6":
            sentido = "SO";
            break;
        case "7":
            sentido = "NE";
            break;
        case "8":
            sentido = "NO";
            break;
        default:
            break;
        }
        return sentido;
    }

    public static byte[] obtenerArchivo(List<ConsultaTroncalDTO> lista) {
        /*
         * FileWriter fw = new FileWriter("./datos.txt"); BufferedWriter bw = new
         * BufferedWriter(fw); for (int i = 0; i < lista.size(); i++) {
         * bw.write(lista.get(i).getNombreEstacion()); bw.newLine(); } bw.close();
         */
        byte[] bytes = {};

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Employee Data");

        Map<String, Object[]> data = new TreeMap<String, Object[]>();
        data.put("1", new Object[] { "ID", "NAME", "LASTNAME" });
        data.put("2", new Object[] { 1, "Amit", "Shukla" });
        data.put("3", new Object[] { 2, "Lokesh", "Gupta" });
        data.put("4", new Object[] { 3, "John", "Adwards" });
        data.put("5", new Object[] { 4, "Brian", "Schultz" });

        Set<String> keyset = data.keySet();
        int rownum = 0;
        for (String key : keyset) {
            Row row = sheet.createRow(rownum++);
            Object[] objArr = data.get(key);
            int cellnum = 0;
            for (Object obj : objArr) {
                Cell cell = row.createCell(cellnum++);
                if (obj instanceof String)
                    cell.setCellValue((String) obj);
                else if (obj instanceof Integer)
                    cell.setCellValue((Integer) obj);
            }
        }
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            workbook.write(bos);
            bos.close();
            System.out.println("howtodoinjava_demo.xlsx written successfully on disk.");
        } catch (Exception e) {
            e.printStackTrace();
        }

        bytes = bos.toByteArray();
        return bytes;
    }

}
