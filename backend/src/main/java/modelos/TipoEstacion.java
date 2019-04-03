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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "TIPO_ESTACION")
public class TipoEstacion implements java.io.Serializable {

    private BigDecimal idTipoEsta;
    private String nombre;
    private Set<Estacion> estacions = new HashSet<>(0);

    public TipoEstacion() {
    }

    public TipoEstacion(BigDecimal idTipoEsta, String nombre) {
        this.idTipoEsta = idTipoEsta;
        this.nombre = nombre;
    }

    public TipoEstacion(BigDecimal idTipoEsta, String nombre, Set<Estacion> estacions) {
        this.idTipoEsta = idTipoEsta;
        this.nombre = nombre;
        this.estacions = estacions;
    }

    @Id
    @Column(name = "ID_TIPO_ESTA", unique = true, nullable = false, precision = 22, scale = 0)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_TIES_ID")
    @SequenceGenerator(name = "SEQ_TIES_ID", sequenceName = "SEQ_TIES_ID", allocationSize = 1)
    public BigDecimal getIdTipoEsta() {
        return this.idTipoEsta;
    }

    public void setIdTipoEsta(BigDecimal idTipoEsta) {
        this.idTipoEsta = idTipoEsta;
    }

    @Column(name = "NOMBRE", nullable = false, length = 30)
    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "tipoEstacion")
    public Set<Estacion> getEstacions() {
        return this.estacions;
    }

    public void setEstacions(Set<Estacion> estacions) {
        this.estacions = estacions;
    }

}