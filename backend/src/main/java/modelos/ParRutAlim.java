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
@Table(name = "PAR_RUT_ALIM")
public class ParRutAlim implements java.io.Serializable {

    private BigDecimal idParRutAlim;
    private RutaAlimen rutaAlimen;
    private Paradero paradero;
    private BigDecimal orden;

    public ParRutAlim() {
    }

    public ParRutAlim(BigDecimal idParRutAlim, RutaAlimen rutaAlimen, Paradero paradero, BigDecimal orden) {
        this.idParRutAlim = idParRutAlim;
        this.rutaAlimen = rutaAlimen;
        this.paradero = paradero;
        this.orden = orden;
    }

    @Id
    @Column(name = "ID_PAR_RUT_ALIM", unique = true, nullable = false, precision = 22, scale = 0)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PARRUTALIM_ID")
    @SequenceGenerator(name = "SEQ_PARRUTALIM_ID", sequenceName = "SEQ_PARRUTALIM_ID", allocationSize = 1)
    public BigDecimal getIdParRutAlim() {
        return this.idParRutAlim;
    }

    public void setIdParRutAlim(BigDecimal idParRutAlim) {
        this.idParRutAlim = idParRutAlim;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_RUT_ALIM", nullable = false)
    public RutaAlimen getRutaAlimen() {
        return this.rutaAlimen;
    }

    public void setRutaAlimen(RutaAlimen rutaAlimen) {
        this.rutaAlimen = rutaAlimen;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_PARADERO", nullable = false)
    public Paradero getParadero() {
        return this.paradero;
    }

    public void setParadero(Paradero paradero) {
        this.paradero = paradero;
    }

    @Column(name = "ORDEN", nullable = false, precision = 22, scale = 0)
    public BigDecimal getOrden() {
        return this.orden;
    }

    public void setOrden(BigDecimal orden) {
        this.orden = orden;
    }

}