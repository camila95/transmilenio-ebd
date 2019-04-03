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
@Table(name = "VAGON")
public class Vagon implements java.io.Serializable {

    private BigDecimal idVagon;
    private Estacion estacion;
    private String nombre;
    private Set<RutaEsta> rutaEstas = new HashSet<>(0);

    public Vagon() {
    }

    public Vagon(BigDecimal idVagon, Estacion estacion, String nombre) {
        this.idVagon = idVagon;
        this.estacion = estacion;
        this.nombre = nombre;
    }

    public Vagon(BigDecimal idVagon, Estacion estacion, String nombre, Set<RutaEsta> rutaEstas) {
        this.idVagon = idVagon;
        this.estacion = estacion;
        this.nombre = nombre;
        this.rutaEstas = rutaEstas;
    }

    @Id
    @Column(name = "ID_VAGON", unique = true, nullable = false, precision = 22, scale = 0)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_VAG_ID")
    @SequenceGenerator(name = "SEQ_VAG_ID", sequenceName = "SEQ_VAG_ID", allocationSize = 1)
    public BigDecimal getIdVagon() {
        return this.idVagon;
    }

    public void setIdVagon(BigDecimal idVagon) {
        this.idVagon = idVagon;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_ESTACION", referencedColumnName = "ID_ESTACION", nullable = false)
    public Estacion getEstacion() {
        return this.estacion;
    }

    public void setEstacion(Estacion estacion) {
        this.estacion = estacion;
    }

    @Column(name = "NOMBRE", nullable = false, length = 30)
    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "vagon")
    public Set<RutaEsta> getRutaEstas() {
        return this.rutaEstas;
    }

    public void setRutaEstas(Set<RutaEsta> rutaEstas) {
        this.rutaEstas = rutaEstas;
    }

}