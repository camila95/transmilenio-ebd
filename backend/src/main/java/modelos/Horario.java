package modelos;

import java.math.BigDecimal;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "HORARIO")
public class Horario implements java.io.Serializable {

    private BigDecimal idHorario;
    private String rango;
    private Date horaComienzo;
    private Date horaFin;
    private Set<ParaHora> paraHoras = new HashSet<>(0);
    private Set<RutaHora> rutaHoras = new HashSet<>(0);

    public Horario() {
    }

    public Horario(BigDecimal idHorario, String rango, Date horaComienzo, Date horaFin) {
        this.idHorario = idHorario;
        this.rango = rango;
        this.horaComienzo = horaComienzo;
        this.horaFin = horaFin;
    }

    public Horario(BigDecimal idHorario, String rango, Date horaComienzo, Date horaFin, Set<ParaHora> paraHoras,
            Set<RutaHora> rutaHoras) {
        this.idHorario = idHorario;
        this.rango = rango;
        this.horaComienzo = horaComienzo;
        this.horaFin = horaFin;
        this.paraHoras = paraHoras;
        this.rutaHoras = rutaHoras;
    }

    @Id
    @Column(name = "ID_HORARIO", unique = true, nullable = false, precision = 22, scale = 0)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_HOR_ID")
    @SequenceGenerator(name = "SEQ_HOR_ID", sequenceName = "SEQ_HOR_ID", allocationSize = 1)
    public BigDecimal getIdHorario() {
        return this.idHorario;
    }

    public void setIdHorario(BigDecimal idHorario) {
        this.idHorario = idHorario;
    }

    @Column(name = "RANGO", nullable = false, length = 1)
    public String getRango() {
        return this.rango;
    }

    public void setRango(String rango) {
        this.rango = rango;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "HORA_COMIENZO", nullable = false)
    public Date getHoraComienzo() {
        return this.horaComienzo;
    }

    public void setHoraComienzo(Date horaComienzo) {
        this.horaComienzo = horaComienzo;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "HORA_FIN", nullable = false)
    public Date getHoraFin() {
        return this.horaFin;
    }

    public void setHoraFin(Date horaFin) {
        this.horaFin = horaFin;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "horario")
    public Set<ParaHora> getParaHoras() {
        return this.paraHoras;
    }

    public void setParaHoras(Set<ParaHora> paraHoras) {
        this.paraHoras = paraHoras;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "horario")
    public Set<RutaHora> getRutaHoras() {
        return this.rutaHoras;
    }

    public void setRutaHoras(Set<RutaHora> rutaHoras) {
        this.rutaHoras = rutaHoras;
    }

}