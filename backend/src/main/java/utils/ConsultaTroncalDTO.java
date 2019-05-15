package utils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ConsultaTroncalDTO implements java.io.Serializable {

    private String nombreOperador;
    private String nombreEstacion;
    private String nombreVagon;
    private String nombreRuta;
    private String sentido;
    private String rango;
    private String horaComienzo;
    private String horaFin;

    public ConsultaTroncalDTO() {
    }

    public String getNombreOperador() {
        return this.nombreOperador;
    }

    public void setNombreOperador(String nombre) {
        this.nombreOperador = nombre;
    }

    public String getNombreEstacion() {
        return this.nombreEstacion;
    }

    public void setNombreEstacion(String nombre) {
        this.nombreEstacion = nombre;
    }

    public String getNombreVagon() {
        return this.nombreVagon;
    }

    public void setNombreVagon(String codigo) {
        this.nombreVagon = codigo;
    }

    public String getNombreRuta() {
        return this.nombreRuta;
    }

    public void setNombreRuta(String nombre) {
        this.nombreRuta = nombre;
    }

    public String getSentido() {
        return this.sentido;
    }

    public void setSentido(String nombre) {
        this.sentido = nombre;
    }

    public String getRango() {
        return this.rango;
    }

    public void setRango(String rango) {
        this.rango = rango;
    }

    public String getHoraComienzo() {
        return this.horaComienzo;
    }

    public void setHoraComienzo(String horaComienzo) {
        this.horaComienzo = horaComienzo;
    }

    public String getHoraFin() {
        return this.horaFin;
    }

    public void setHoraFin(String horaFin) {
        this.horaFin = horaFin;
    }

}