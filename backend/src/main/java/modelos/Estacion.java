package modelos;

import java.math.BigDecimal;
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

@Entity
@Table(name = "ESTACION")
public class Estacion implements java.io.Serializable {

    private BigDecimal idEstacion;
    private TipoEstacion tipoEstacion;
    private Troncal troncal;
    private String nombre;
    private String direccion;
    private String localidad;
    private String latitud;
    private String longitud;
    private BigDecimal estaIncial;
    private BigDecimal estaFinal;
    private BigDecimal orden;
    private Set<Vagon> vagons = new HashSet<>(0);
    private Set<RutaAlimen> rutaAlimens = new HashSet<>(0);

    public Estacion() {
    }

    public Estacion(BigDecimal idEstacion, TipoEstacion tipoEstacion, Troncal troncal, String nombre, String direccion,
            String localidad, BigDecimal estaIncial, BigDecimal estaFinal, BigDecimal orden) {
        this.idEstacion = idEstacion;
        this.tipoEstacion = tipoEstacion;
        this.troncal = troncal;
        this.nombre = nombre;
        this.direccion = direccion;
        this.localidad = localidad;
        this.estaIncial = estaIncial;
        this.estaFinal = estaFinal;
        this.orden = orden;
    }

    public Estacion(BigDecimal idEstacion, TipoEstacion tipoEstacion, Troncal troncal, String nombre, String direccion,
            String localidad, String latitud, String longitud, BigDecimal estaIncial, BigDecimal estaFinal,
            BigDecimal orden, Set<Vagon> vagons, Set<RutaAlimen> rutaAlimens) {
        this.idEstacion = idEstacion;
        this.tipoEstacion = tipoEstacion;
        this.troncal = troncal;
        this.nombre = nombre;
        this.direccion = direccion;
        this.localidad = localidad;
        this.latitud = latitud;
        this.longitud = longitud;
        this.estaIncial = estaIncial;
        this.estaFinal = estaFinal;
        this.orden = orden;
        this.vagons = vagons;
        this.rutaAlimens = rutaAlimens;
    }

    @Id
    @Column(name = "ID_ESTACION", unique = true, nullable = false, precision = 22, scale = 0)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_EST_ID")
    @SequenceGenerator(name = "SEQ_EST_ID", sequenceName = "SEQ_EST_ID", allocationSize = 1)
    public BigDecimal getIdEstacion() {
        return this.idEstacion;
    }

    public void setIdEstacion(BigDecimal idEstacion) {
        this.idEstacion = idEstacion;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_TIPO_ESTA", nullable = false)
    public TipoEstacion getTipoEstacion() {
        return this.tipoEstacion;
    }

    public void setTipoEstacion(TipoEstacion tipoEstacion) {
        this.tipoEstacion = tipoEstacion;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_TRONCAL", nullable = false)
    public Troncal getTroncal() {
        return this.troncal;
    }

    public void setTroncal(Troncal troncal) {
        this.troncal = troncal;
    }

    @Column(name = "NOMBRE", nullable = false, length = 30)
    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Column(name = "DIRECCION", nullable = false, length = 45)
    public String getDireccion() {
        return this.direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Column(name = "LOCALIDAD", nullable = false, length = 30)
    public String getLocalidad() {
        return this.localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    @Column(name = "LATITUD", length = 10)
    public String getLatitud() {
        return this.latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    @Column(name = "LONGITUD", length = 10)
    public String getLongitud() {
        return this.longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    @Column(name = "ESTA_INCIAL", nullable = false, precision = 1, scale = 0)
    public BigDecimal getEstaIncial() {
        return this.estaIncial;
    }

    public void setEstaIncial(BigDecimal estaIncial) {
        this.estaIncial = estaIncial;
    }

    @Column(name = "ESTA_FINAL", nullable = false, precision = 1, scale = 0)
    public BigDecimal getEstaFinal() {
        return this.estaFinal;
    }

    public void setEstaFinal(BigDecimal estaFinal) {
        this.estaFinal = estaFinal;
    }

    @Column(name = "ORDEN", nullable = false, precision = 22, scale = 0)
    public BigDecimal getOrden() {
        return this.orden;
    }

    public void setOrden(BigDecimal orden) {
        this.orden = orden;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "estacion")
    public Set<Vagon> getVagons() {
        return this.vagons;
    }

    public void setVagons(Set<Vagon> vagons) {
        this.vagons = vagons;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "estacion")
    public Set<RutaAlimen> getRutaAlimens() {
        return this.rutaAlimens;
    }

    public void setRutaAlimens(Set<RutaAlimen> rutaAlimens) {
        this.rutaAlimens = rutaAlimens;
    }

    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("idEstacion", this.idEstacion);
        map.put("nombre", this.nombre);
        map.put("direccion", this.direccion);
        map.put("localidad", this.localidad);
        map.put("latitud", this.latitud);
        map.put("longitud", this.longitud);
        map.put("estaIncial", this.estaIncial);
        map.put("estaFinal", this.estaFinal);
        map.put("orden", this.orden);
        return map;
    }

    public static Estacion fake() {
        Estacion estacion = new Estacion();
        Faker faker = new Faker();
        estacion.setNombre(faker.name().fullName());
        if (estacion.getNombre().length() > 30) {
            estacion.setNombre(estacion.getNombre().substring(0, 30));
        }
        estacion.setDireccion(faker.address().fullAddress());
        if (estacion.getDireccion().length() > 30) {
            estacion.setDireccion(estacion.getDireccion().substring(0, 30));
        }
        estacion.setLocalidad(faker.name().firstName());
        if (estacion.getLocalidad().length() > 30) {
            estacion.setLocalidad(estacion.getLocalidad().substring(0, 30));
        }
        estacion.setLatitud(faker.name().lastName());
        if (estacion.getLatitud().length() > 30) {
            estacion.setLatitud(estacion.getLatitud().substring(0, 30));
        }
        estacion.setLongitud(faker.name().lastName());
        if (estacion.getLongitud().length() > 30) {
            estacion.setLongitud(estacion.getLongitud().substring(0, 30));
        }

        return estacion;
    }
}