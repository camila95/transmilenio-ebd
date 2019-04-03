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
@Table(name = "PARADERO")
public class Paradero implements java.io.Serializable {

    private BigDecimal idParadero;
    private TipoParadero tipoParadero;
    private String nombre;
    private String direccion;
    private String latitud;
    private String longitud;
    private Set<ParRutAlim> parRutAlims = new HashSet<>(0);
    private Set<ParaHora> paraHoras = new HashSet<>(0);

    public Paradero() {
    }

    public Paradero(BigDecimal idParadero, TipoParadero tipoParadero, String nombre, String direccion) {
        this.idParadero = idParadero;
        this.tipoParadero = tipoParadero;
        this.nombre = nombre;
        this.direccion = direccion;
    }

    public Paradero(BigDecimal idParadero, TipoParadero tipoParadero, String nombre, String direccion, String latitud,
            String longitud, Set<ParRutAlim> parRutAlims, Set<ParaHora> paraHoras) {
        this.idParadero = idParadero;
        this.tipoParadero = tipoParadero;
        this.nombre = nombre;
        this.direccion = direccion;
        this.latitud = latitud;
        this.longitud = longitud;
        this.parRutAlims = parRutAlims;
        this.paraHoras = paraHoras;
    }

    @Id
    @Column(name = "ID_PARADERO", unique = true, nullable = false, precision = 22, scale = 0)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PAR_ID")
    @SequenceGenerator(name = "SEQ_PAR_ID", sequenceName = "SEQ_PAR_ID", allocationSize = 1)
    public BigDecimal getIdParadero() {
        return this.idParadero;
    }

    public void setIdParadero(BigDecimal idParadero) {
        this.idParadero = idParadero;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_TIPO_PARADERO", nullable = false)
    public TipoParadero getTipoParadero() {
        return this.tipoParadero;
    }

    public void setTipoParadero(TipoParadero tipoParadero) {
        this.tipoParadero = tipoParadero;
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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "paradero")
    public Set<ParRutAlim> getParRutAlims() {
        return this.parRutAlims;
    }

    public void setParRutAlims(Set<ParRutAlim> parRutAlims) {
        this.parRutAlims = parRutAlims;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "paradero")
    public Set<ParaHora> getParaHoras() {
        return this.paraHoras;
    }

    public void setParaHoras(Set<ParaHora> paraHoras) {
        this.paraHoras = paraHoras;
    }
}