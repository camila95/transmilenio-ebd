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
@Table(name = "TIPO_PARADERO")
public class TipoParadero implements java.io.Serializable {

    private BigDecimal idTipoParadero;
    private String nombre;
    private String figura;
    private Set<Paradero> paraderos = new HashSet<>(0);

    public TipoParadero() {
    }

    public TipoParadero(BigDecimal idTipoParadero, String nombre, String figura) {
        this.idTipoParadero = idTipoParadero;
        this.nombre = nombre;
        this.figura = figura;
    }

    public TipoParadero(BigDecimal idTipoParadero, String nombre, String figura, Set<Paradero> paraderos) {
        this.idTipoParadero = idTipoParadero;
        this.nombre = nombre;
        this.figura = figura;
        this.paraderos = paraderos;
    }

    @Id
    @Column(name = "ID_TIPO_PARADERO", unique = true, nullable = false, precision = 22, scale = 0)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_TIPA_ID")
    @SequenceGenerator(name = "SEQ_TIPA_ID", sequenceName = "SEQ_TIPA_ID", allocationSize = 1)
    public BigDecimal getIdTipoParadero() {
        return this.idTipoParadero;
    }

    public void setIdTipoParadero(BigDecimal idTipoParadero) {
        this.idTipoParadero = idTipoParadero;
    }

    @Column(name = "NOMBRE", nullable = false, length = 30)
    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Column(name = "FIGURA", nullable = false, length = 15)
    public String getFigura() {
        return this.figura;
    }

    public void setFigura(String figura) {
        this.figura = figura;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "tipoParadero")
    public Set<Paradero> getParaderos() {
        return this.paraderos;
    }

    public void setParaderos(Set<Paradero> paraderos) {
        this.paraderos = paraderos;
    }

}