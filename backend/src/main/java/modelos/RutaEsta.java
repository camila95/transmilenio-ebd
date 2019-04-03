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
@Table(name = "RUTA_ESTA")
public class RutaEsta implements java.io.Serializable {

    private BigDecimal idRutaEsta;
    private Vagon vagon;
    private Ruta ruta;
    private BigDecimal orden;

    public RutaEsta() {
    }

    public RutaEsta(BigDecimal idRutaEsta, Vagon vagon, Ruta ruta, BigDecimal orden) {
        this.idRutaEsta = idRutaEsta;
        this.vagon = vagon;
        this.ruta = ruta;
        this.orden = orden;
    }

    @Id
    @Column(name = "ID_RUTA_ESTA", unique = true, nullable = false, precision = 22, scale = 0)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_RUTEST_ID")
    @SequenceGenerator(name = "SEQ_RUTEST_ID", sequenceName = "SEQ_RUTEST_ID", allocationSize = 1)
    public BigDecimal getIdRutaEsta() {
        return this.idRutaEsta;
    }

    public void setIdRutaEsta(BigDecimal idRutaEsta) {
        this.idRutaEsta = idRutaEsta;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_VAGON", nullable = false)
    public Vagon getVagon() {
        return this.vagon;
    }

    public void setVagon(Vagon vagon) {
        this.vagon = vagon;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_RUTA", nullable = false)
    public Ruta getRuta() {
        return this.ruta;
    }

    public void setRuta(Ruta ruta) {
        this.ruta = ruta;
    }

    @Column(name = "ORDEN", nullable = false, precision = 22, scale = 0)
    public BigDecimal getOrden() {
        return this.orden;
    }

    public void setOrden(BigDecimal orden) {
        this.orden = orden;
    }

}