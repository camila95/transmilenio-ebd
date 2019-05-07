package utils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.github.javafaker.Faker;

public class ConsultaDTO implements java.io.Serializable {

    private String nombreEstacion;
    private String codigoRutaAlimen;
    /*
     * private String nombreRutaAlimen; private String nombreTipoPara; private
     * String nombrePara; private String rango; private String horaComienzo; private
     * String horaFin;
     * 
     * public ConsultaDTO() { }
     */

    public String getNombreEstacion() {
        return this.nombreEstacion;
    }

    public void setNombreEstacion(String nombre) {
        this.nombreEstacion = nombre;
    }

    public String getCodigoRutaAlimen() {
        return this.codigoRutaAlimen;
    }

    public void setCodigoRutaAlimen(String codigo) {
        this.codigoRutaAlimen = codigo;
    }

    /*
     * public String getNombreRutaAlimen() { return this.nombreRutaAlimen; }
     * 
     * public void setNombreRutaAlimen(String nombre) { this.nombreRutaAlimen =
     * nombre; }
     * 
     * public String getNombreTipoPara() { return this.nombreTipoPara; }
     * 
     * public void setNombreTipoPara(String nombre) { this.nombreTipoPara = nombre;
     * }
     * 
     * public String getNombrePara() { return this.nombrePara; }
     * 
     * public void setNombrePara(String nombre) { this.nombrePara = nombre; }
     * 
     * public String getRango() { return this.rango; }
     * 
     * public void setRango(String rango) { this.rango = rango; }
     * 
     * public String getHoraComienzo() { return this.horaComienzo; }
     * 
     * public void setHoraComienzo(String horaComienzo) { this.horaComienzo =
     * horaComienzo; }
     * 
     * public String getHoraFin() { return this.horaFin; }
     * 
     * public void setHoraFin(String horaFin) { this.horaFin = horaFin; }
     */

    public Map<String, String> toMap() {
        Map<String, String> map = new HashMap<>();
        map.put("nombreEstacion", this.nombreEstacion);
        map.put("codigoRutaAlimen", this.codigoRutaAlimen);
        /*
         * map.put("nombreRutaAlimen", this.nombreRutaAlimen); map.put("nombreTipoPara",
         * this.nombreTipoPara); map.put("nombrePara", this.nombrePara);
         * map.put("rango", this.rango); map.put("horaComienzo", this.horaComienzo);
         * map.put("horaFin", this.horaFin);
         */
        return map;
    }

}