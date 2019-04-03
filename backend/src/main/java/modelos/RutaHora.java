package modelos;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "RUTA_HORA")
public class RutaHora implements java.io.Serializable {

    private BigDecimal idRutaHora;
    private Ruta ruta;
    private Horario horario;

    public RutaHora() {
    }

    public RutaHora(BigDecimal idRutaHora, Ruta ruta, Horario horario) {
        this.idRutaHora = idRutaHora;
        this.ruta = ruta;
        this.horario = horario;
    }

    @Id
    @Column(name = "ID_RUTA_HORA", unique = true, nullable = false, precision = 22, scale = 0)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_RUTHOR_ID")
    @SequenceGenerator(name = "SEQ_RUTHOR_ID", sequenceName = "SEQ_RUTHOR_ID", allocationSize = 1)
    public BigDecimal getIdRutaHora() {
        return this.idRutaHora;
    }

    public void setIdRutaHora(BigDecimal idRutaHora) {
        this.idRutaHora = idRutaHora;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_RUTA", nullable = false)
    public Ruta getRuta() {
        return this.ruta;
    }

    public void setRuta(Ruta ruta) {
        this.ruta = ruta;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_HORARIO", nullable = false)
    public Horario getHorario() {
        return this.horario;
    }

    public void setHorario(Horario horario) {
        this.horario = horario;
    }

}