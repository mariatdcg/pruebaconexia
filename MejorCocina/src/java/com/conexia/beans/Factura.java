package com.conexia.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Maria Da Costa
 */
@Entity
@Table(name = "factura")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Factura.findAll", query = "SELECT f FROM Factura f")
    , @NamedQuery(name = "Factura.findByIdFactura", query = "SELECT f FROM Factura f WHERE f.idFactura = :idFactura")
    , @NamedQuery(name = "Factura.findByFechaFactura", query = "SELECT f FROM Factura f WHERE f.fechaFactura = :fechaFactura")})

public class Factura implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer idFactura;
   
    @ManyToOne(optional = false)
    @JoinColumn(name = "idCliente", referencedColumnName = "idCliente")
    private Cliente cliente;
   
    @ManyToOne(optional = false)
     @JoinColumn(name = "idCamarero", referencedColumnName = "idCamarero")
    private Camarero camarero;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "idMesa", referencedColumnName = "idMesa")
    private Mesa mesa;
    
    @Temporal(TemporalType.TIMESTAMP) 
    private Date fechaFactura;  
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "factura")
    private List<DetalleFactura> detalleFactura = new ArrayList();

  public Factura() {
    }

    public Factura(Integer idFactura) {
        this.idFactura = idFactura;
    }

    public Factura(Integer idFactura, Date fechaFactura) {
        this.idFactura = idFactura;
        this.fechaFactura = fechaFactura;
    }

    public Integer getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(Integer idFactura) {
        this.idFactura = idFactura;
    }

    public Date getFechaFactura() {
        return fechaFactura;
    }

    public void setFechaFactura(Date fechaFactura) {
        this.fechaFactura = fechaFactura;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Camarero getCamarero() {
        return camarero;
    }

    public void setCamarero(Camarero camarero) {
        this.camarero = camarero;
    }

    public Mesa getMesa() {
        return mesa;
    }

    public void setMesa(Mesa mesa) {
        this.mesa = mesa;
    }

    @XmlTransient
    public List<DetalleFactura> getDetalleFactura() {
        return detalleFactura;
    }

    public void setDetalleFactura(List<DetalleFactura> detalleFactura) {
        this.detalleFactura = detalleFactura;
    }
  

      @Override
    public String toString() {
        return "id " + idFactura +", fecha: "+ fechaFactura;
    }
    
        @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFactura != null ? idFactura.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Factura)) {
            return false;
        }
        Factura other = (Factura) object;
        if ((this.idFactura == null && other.idFactura != null) || (this.idFactura != null && !this.idFactura.equals(other.idFactura))) {
            return false;
        }
        return true;
    }
    
    public List<DetalleFactura> getDetallesFactura(){
        List<DetalleFactura> detalles = new ArrayList<DetalleFactura>();
        
        return detalles;
    }
    
}
