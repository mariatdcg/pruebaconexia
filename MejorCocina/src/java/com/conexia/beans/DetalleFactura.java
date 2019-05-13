
package com.conexia.beans;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Maria Da Costa
 */
@Entity
@Table(name = "detalleFactura")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DetalleFactura.findAll", query = "SELECT d FROM DetalleFactura d")
    , @NamedQuery(name = "DetalleFactura.findByIdDetalleFactura", query = "SELECT d FROM DetalleFactura d WHERE d.detallefacturaPK.idDetalleFactura = :idDetalleFactura")
    , @NamedQuery(name = "DetalleFactura.findByIdFactura", query = "SELECT d FROM DetalleFactura d WHERE d.detallefacturaPK.idFactura = :idFactura")
    , @NamedQuery(name = "DetalleFactura.findByPlato", query = "SELECT d FROM DetalleFactura d WHERE d.plato = :plato")
    , @NamedQuery(name = "DetalleFactura.findByImporte", query = "SELECT d FROM DetalleFactura d WHERE d.importe = :importe")})
   public class DetalleFactura implements Serializable {
   
    @EmbeddedId
    protected DetallefacturaPK detallefacturaPK; 
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "plato")
    private String plato;
    @Basic(optional = false)
    @NotNull
    @Column(name = "importe")
    private float importe;
    @JoinColumn(name = "idCocinero", referencedColumnName = "idCocinero")
    @ManyToOne
    private Cocinero cocinero;
    
    @JoinColumn(name = "idFactura", referencedColumnName = "idFactura", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Factura factura;
    

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

   public String getPlato() {
        return plato;
    }

    public void setPlato(String plato) {
        this.plato = plato;
    }

    public float getImporte() {
        return importe;
    }

    public void setImporte(float importe) {
        this.importe = importe;
    }

    public Cocinero getCocinero() {
        return cocinero;
    }

    public void setCocinero(Cocinero cocinero) {
        this.cocinero = cocinero;
    }
    
    public DetalleFactura() {
    }

    public DetalleFactura(DetallefacturaPK detallefacturaPK) {
        this.detallefacturaPK = detallefacturaPK;
    }

    public DetalleFactura(DetallefacturaPK detallefacturaPK, String plato, float importe) {
        this.detallefacturaPK = detallefacturaPK;
        this.plato = plato;
        this.importe = importe;
    }

    public DetalleFactura(int idDetalleFactura, int idFactura) {
        this.detallefacturaPK = new DetallefacturaPK(idDetalleFactura, idFactura);
    }

    public DetallefacturaPK getDetallefacturaPK() {
        return detallefacturaPK;
    }

    public void setDetallefacturaPK(DetallefacturaPK detallefacturaPK) {
        this.detallefacturaPK = detallefacturaPK;
    }

     @Override
    public String toString() {
        return "Plato: "+ plato+", Importe: " + importe ;
    }
    
        @Override
    public int hashCode() {
        int hash = 0;
        hash += (detallefacturaPK != null ? detallefacturaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetalleFactura)) {
            return false;
        }
        DetalleFactura other = (DetalleFactura) object;
        if ((this.detallefacturaPK == null && other.detallefacturaPK != null) || (this.detallefacturaPK != null && !this.detallefacturaPK.equals(other.detallefacturaPK))) {
            return false;
        }
        return true;
    }
    
}
