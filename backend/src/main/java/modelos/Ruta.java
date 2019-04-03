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
@Table(name = "RUTA")
public class Ruta implements java.io.Serializable {

    private BigDecimal idRuta;
    private String nombre;
    private String sentido;
    private Set<RutaEsta> rutaEstas = new HashSet<>(0);
    private Set<RutaHora> rutaHoras = new HashSet<>(0);

    public Ruta() {
    }

    public Ruta(BigDecimal idRuta, String nombre, String sentido) {
        this.idRuta = idRuta;
        this.nombre = nombre;
        this.sentido = sentido;
    }

    public Ruta(BigDecimal idRuta, String nombre, String sentido, Set<RutaEsta> rutaEstas, Set<RutaHora> rutaHoras) {
        this.idRuta = idRuta;
        this.nombre = nombre;
        this.sentido = sentido;
        this.rutaEstas = rutaEstas;
        this.rutaHoras = rutaHoras;
    }

    @Id
    @Column(name = "ID_RUTA", unique = true, nullable = false, precision = 22, scale = 0)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_RUT_ID")
    @SequenceGenerator(name = "SEQ_RUT_ID", sequenceName = "SEQ_RUT_ID", allocationSize = 1)
    public BigDecimal getIdRuta() {
        return this.idRuta;
    }

    public void setIdRuta(BigDecimal idRuta) {
        this.idRuta = idRuta;
    }

    @Column(name = "NOMBRE", nullable = false, length = 30)
    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Column(name = "SENTIDO", nullable = false, length = 2)
    public String getSentido() {
        return this.sentido;
    }

    public void setSentido(String sentido) {
        this.sentido = sentido;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "ruta")
    public Set<RutaEsta> getRutaEstas() {
        return this.rutaEstas;
    }

    public void setRutaEstas(Set<RutaEsta> rutaEstas) {
        this.rutaEstas = rutaEstas;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "ruta")
    public Set<RutaHora> getRutaHoras() {
        return this.rutaHoras;
    }

    public void setRutaHoras(Set<RutaHora> rutaHoras) {
        this.rutaHoras = rutaHoras;
    }

}