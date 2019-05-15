package modelos.dto;

import java.math.BigDecimal;

public class EstacionDTO {

    private BigDecimal idEstacion;
    private BigDecimal idTipoEstacion;
    private BigDecimal idTroncal;
    private String nombre;
    private String direccion;
    private String localidad;
    private String latitud;
    private String longitud;
    private BigDecimal estaIncial;
    private BigDecimal estaFinal;
    private BigDecimal orden;

    public EstacionDTO() {

    }

    public EstacionDTO(BigDecimal idEstacion, BigDecimal tipoEstacion, BigDecimal troncal, String nombre,
            String direccion, String localidad, BigDecimal estaIncial, BigDecimal estaFinal, BigDecimal orden) {
        this.idEstacion = idEstacion;
        this.idTipoEstacion = tipoEstacion;
        this.idTroncal = troncal;
        this.nombre = nombre;
        this.direccion = direccion;
        this.localidad = localidad;
        this.estaIncial = estaIncial;
        this.estaFinal = estaFinal;
        this.orden = orden;
    }

    public BigDecimal getIdEstacion() {
        return this.idEstacion;
    }

    public void setIdEstacion(BigDecimal idEstacion) {
        this.idEstacion = idEstacion;
    }

    public BigDecimal getIdTipoEstacion() {
        return this.idTipoEstacion;
    }

    public void setIdTipoEstacion(BigDecimal tipoEstacion) {
        this.idTipoEstacion = tipoEstacion;
    }

    public BigDecimal getIdTroncal() {
        return this.idTroncal;
    }

    public void setIdTroncal(BigDecimal troncal) {
        this.idTroncal = troncal;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return this.direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getLocalidad() {
        return this.localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getLatitud() {
        return this.latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return this.longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public BigDecimal getEstaIncial() {
        return this.estaIncial;
    }

    public void setEstaIncial(BigDecimal estaIncial) {
        this.estaIncial = estaIncial;
    }

    public BigDecimal getEstaFinal() {
        return this.estaFinal;
    }

    public void setEstaFinal(BigDecimal estaFinal) {
        this.estaFinal = estaFinal;
    }

    public BigDecimal getOrden() {
        return this.orden;
    }

    public void setOrden(BigDecimal orden) {
        this.orden = orden;
    }

}