package modelos;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
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

import com.github.javafaker.Faker;

@Entity
@Table(name = "TIPO_ESTACION")
public class TipoEstacion implements java.io.Serializable {

    private BigDecimal idTipoEsta;
    private String nombre;

    public TipoEstacion() {
    }

    public TipoEstacion(BigDecimal idTipoEsta, String nombre) {
        this.idTipoEsta = idTipoEsta;
        this.nombre = nombre;
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

    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("idTipoEsta", this.idTipoEsta);
        map.put("nombre", this.nombre);
        return map;
    }

    public static TipoEstacion fake() {
        TipoEstacion tipoEstacion = new TipoEstacion();
        Faker faker = new Faker();
        tipoEstacion.setNombre(faker.name().fullName());
        if (tipoEstacion.getNombre().length() > 30) {
            tipoEstacion.setNombre(tipoEstacion.getNombre().substring(0, 30));
        }
        return tipoEstacion;
    }

}