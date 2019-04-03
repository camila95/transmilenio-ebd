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
@Table(name = "PARA_HORA")
public class ParaHora implements java.io.Serializable {

    private BigDecimal idParaHora;
    private Horario horario;
    private Paradero paradero;

    public ParaHora() {
    }

    public ParaHora(BigDecimal idParaHora, Horario horario, Paradero paradero) {
        this.idParaHora = idParaHora;
        this.horario = horario;
        this.paradero = paradero;
    }

    @Id
    @Column(name = "ID_PARA_HORA", unique = true, nullable = false, precision = 22, scale = 0)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PARHOR_ID")
    @SequenceGenerator(name = "SEQ_PARHOR_ID", sequenceName = "SEQ_PARHOR_ID", allocationSize = 1)
    public BigDecimal getIdParaHora() {
        return this.idParaHora;
    }

    public void setIdParaHora(BigDecimal idParaHora) {
        this.idParaHora = idParaHora;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_HORARIO", nullable = false)
    public Horario getHorario() {
        return this.horario;
    }

    public void setHorario(Horario horario) {
        this.horario = horario;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_PARADERO", nullable = false)
    public Paradero getParadero() {
        return this.paradero;
    }

    public void setParadero(Paradero paradero) {
        this.paradero = paradero;
    }

}