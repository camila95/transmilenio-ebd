package modelos;

import java.math.BigDecimal;
import java.util.HashMap;
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
@Table(name = "TRONCAL")
public class Troncal implements java.io.Serializable {

    private BigDecimal idTroncal;
    private Operador operador;
    private String nombreVia;
    private String zona;
    private String colorZona;
    private String nombreZona;
    private Set<Estacion> estacions = new HashSet<>(0);

    public Troncal() {
    }

    public Troncal(BigDecimal idTroncal, Operador operador, String nombreVia, String zona, String colorZona,
            String nombreZona) {
        this.idTroncal = idTroncal;
        this.operador = operador;
        this.nombreVia = nombreVia;
        this.zona = zona;
        this.colorZona = colorZona;
        this.nombreZona = nombreZona;
    }

    public Troncal(BigDecimal idTroncal, Operador operador, String nombreVia, String zona, String colorZona,
            String nombreZona, Set<Estacion> estacions) {
        this.idTroncal = idTroncal;
        this.operador = operador;
        this.nombreVia = nombreVia;
        this.zona = zona;
        this.colorZona = colorZona;
        this.nombreZona = nombreZona;
        this.estacions = estacions;
    }

    @Id
    @Column(name = "ID_TRONCAL", unique = true, nullable = false, precision = 22, scale = 0)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_TRON_ID")
    @SequenceGenerator(name = "SEQ_TRON_ID", sequenceName = "SEQ_TRON_ID", allocationSize = 1)
    public BigDecimal getIdTroncal() {
        return this.idTroncal;
    }

    public void setIdTroncal(BigDecimal idTroncal) {
        this.idTroncal = idTroncal;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_OPERADOR", nullable = false)
    public Operador getOperador() {
        return this.operador;
    }

    public void setOperador(Operador operador) {
        this.operador = operador;
    }

    @Column(name = "NOMBRE_VIA", nullable = false, length = 30)
    public String getNombreVia() {
        return this.nombreVia;
    }

    public void setNombreVia(String nombreVia) {
        this.nombreVia = nombreVia;
    }

    @Column(name = "ZONA", nullable = false, length = 2)
    public String getZona() {
        return this.zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }

    @Column(name = "COLOR_ZONA", nullable = false, length = 15)
    public String getColorZona() {
        return this.colorZona;
    }

    public void setColorZona(String colorZona) {
        this.colorZona = colorZona;
    }

    @Column(name = "NOMBRE_ZONA", nullable = false, length = 30)
    public String getNombreZona() {
        return this.nombreZona;
    }

    public void setNombreZona(String nombreZona) {
        this.nombreZona = nombreZona;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "troncal")
    public Set<Estacion> getEstacions() {
        return this.estacions;
    }

    public void setEstacions(Set<Estacion> estacions) {
        this.estacions = estacions;
    }

    public HashMap<String, Object> toMap() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("idTroncal", this.idTroncal);
        map.put("operador", this.operador);
        map.put("nombreVia", this.nombreVia);
        map.put("zona", this.zona);
        map.put("colorZona", this.colorZona);
        map.put("nombreZona", this.nombreZona);
        map.put("estacions", this.estacions);
        return map;
    }

}