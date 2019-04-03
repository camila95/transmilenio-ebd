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
@Table(name = "OPERADOR")
public class Operador implements java.io.Serializable {

    private BigDecimal idOperador;
    private String nombre;
    private String telefono;
    private String direccion;
    private String representante;
    private String paginaWeb;
    private Set<Troncal> troncals = new HashSet<>(0);
    private Set<RutaAlimen> rutaAlimens = new HashSet<>(0);

    public Operador() {
    }

    public Operador(BigDecimal idOperador, String nombre, String telefono, String direccion, String representante) {
        this.idOperador = idOperador;
        this.nombre = nombre;
        this.telefono = telefono;
        this.direccion = direccion;
        this.representante = representante;
    }

    public Operador(BigDecimal idOperador, String nombre, String telefono, String direccion, String representante,
            String paginaWeb, Set<Troncal> troncals, Set<RutaAlimen> rutaAlimens) {
        this.idOperador = idOperador;
        this.nombre = nombre;
        this.telefono = telefono;
        this.direccion = direccion;
        this.representante = representante;
        this.paginaWeb = paginaWeb;
        this.troncals = troncals;
        this.rutaAlimens = rutaAlimens;
    }

    @Id
    @Column(name = "ID_OPERADOR", unique = true, nullable = false, precision = 22, scale = 0)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_OPE_ID")
    @SequenceGenerator(name = "SEQ_OPE_ID", sequenceName = "SEQ_OPE_ID", allocationSize = 1)
    public BigDecimal getIdOperador() {
        return this.idOperador;
    }

    public void setIdOperador(BigDecimal idOperador) {
        this.idOperador = idOperador;
    }

    @Column(name = "NOMBRE", nullable = false, length = 30)
    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Column(name = "TELEFONO", nullable = false, length = 15)
    public String getTelefono() {
        return this.telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Column(name = "DIRECCION", nullable = false, length = 45)
    public String getDireccion() {
        return this.direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Column(name = "REPRESENTANTE", nullable = false, length = 30)
    public String getRepresentante() {
        return this.representante;
    }

    public void setRepresentante(String representante) {
        this.representante = representante;
    }

    @Column(name = "PAGINA_WEB", length = 100)
    public String getPaginaWeb() {
        return this.paginaWeb;
    }

    public void setPaginaWeb(String paginaWeb) {
        this.paginaWeb = paginaWeb;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "operador")
    public Set<Troncal> getTroncals() {
        return this.troncals;
    }

    public void setTroncals(Set<Troncal> troncals) {
        this.troncals = troncals;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "operador")
    public Set<RutaAlimen> getRutaAlimens() {
        return this.rutaAlimens;
    }

    public void setRutaAlimens(Set<RutaAlimen> rutaAlimens) {
        this.rutaAlimens = rutaAlimens;
    }
}
