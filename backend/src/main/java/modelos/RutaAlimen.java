package modelos;

import java.math.BigDecimal;
import java.util.HashSet;
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

@Entity
@Table(name = "RUTA_ALIMEN")
public class RutaAlimen implements java.io.Serializable {

    private BigDecimal idRutaAlimen;
    private Operador operador;
    private Estacion estacion;
    private String codigo;
    private String nombre;
    private Set<ParRutAlim> parRutAlims = new HashSet<>(0);

    public RutaAlimen() {
    }

    public RutaAlimen(BigDecimal idRutaAlimen, Operador operador, Estacion estacion, String codigo, String nombre) {
        this.idRutaAlimen = idRutaAlimen;
        this.operador = operador;
        this.estacion = estacion;
        this.codigo = codigo;
        this.nombre = nombre;
    }

    public RutaAlimen(BigDecimal idRutaAlimen, Operador operador, Estacion estacion, String codigo, String nombre,
            Set<ParRutAlim> parRutAlims) {
        this.idRutaAlimen = idRutaAlimen;
        this.operador = operador;
        this.estacion = estacion;
        this.codigo = codigo;
        this.nombre = nombre;
        this.parRutAlims = parRutAlims;
    }

    @Id
    @Column(name = "ID_RUTA_ALIMEN", unique = true, nullable = false, precision = 22, scale = 0)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_RUTALIM_ID")
    @SequenceGenerator(name = "SEQ_RUTALIM_ID", sequenceName = "SEQ_RUTALIM_ID", allocationSize = 1)
    public BigDecimal getIdRutaAlimen() {
        return this.idRutaAlimen;
    }

    public void setIdRutaAlimen(BigDecimal idRutaAlimen) {
        this.idRutaAlimen = idRutaAlimen;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_OPERADOR", nullable = false)
    public Operador getOperador() {
        return this.operador;
    }

    public void setOperador(Operador operador) {
        this.operador = operador;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_ESTACION", nullable = false)
    public Estacion getEstacion() {
        return this.estacion;
    }

    public void setEstacion(Estacion estacion) {
        this.estacion = estacion;
    }

    @Column(name = "CODIGO", nullable = false, length = 5)
    public String getCodigo() {
        return this.codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    @Column(name = "NOMBRE", nullable = false, length = 30)
    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "rutaAlimen")
    public Set<ParRutAlim> getParRutAlims() {
        return this.parRutAlims;
    }

    public void setParRutAlims(Set<ParRutAlim> parRutAlims) {
        this.parRutAlims = parRutAlims;
    }

}