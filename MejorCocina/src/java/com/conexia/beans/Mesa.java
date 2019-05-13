
package com.conexia.beans;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Maria Da Costa
 */
@Entity
@Table(name = "mesa")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Mesa.findAll", query = "SELECT m FROM Mesa m")
    , @NamedQuery(name = "Mesa.findByIdMesa", query = "SELECT m FROM Mesa m WHERE m.idMesa = :idMesa")
    , @NamedQuery(name = "Mesa.findByNumMaxComensales", query = "SELECT m FROM Mesa m WHERE m.numMaxComensales = :numMaxComensales")
    , @NamedQuery(name = "Mesa.findByUbicacion", query = "SELECT m FROM Mesa m WHERE m.ubicacion = :ubicacion")})

public class Mesa implements Serializable{
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idMesa;
    private int numMaxComensales;
    private String ubicacion;

     @OneToMany(cascade = CascadeType.ALL, mappedBy = "mesa")
     private List<Factura> facturaList;
   
     public Mesa() {
    }

    public Mesa(Integer idMesa) {
        this.idMesa = idMesa;
    }

    public Mesa(Integer idMesa, int numMaxComensales) {
        this.idMesa = idMesa;
        this.numMaxComensales = numMaxComensales;
    }

    public int getIdMesa() {
        return idMesa;
    }

    public void setIdMesa(int idMesa) {
        this.idMesa = idMesa;
    }

    public int getNumMaxComensales() {
        return numMaxComensales;
    }

    public void setNumMaxComensales(int numMaxComensales) {
        this.numMaxComensales = numMaxComensales;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }
    
    public List<Factura> getFacturaList() {
        return facturaList;
    }

    public void setFacturaList(List<Factura> facturaList) {
        this.facturaList = facturaList;
    }
    
     @Override
  public String toString() {
      return "Mesa: " + idMesa + " - " + ubicacion;
  }
  
  
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMesa != null ? idMesa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Mesa)) {
            return false;
        }
        Mesa other = (Mesa) object;
        if ((this.idMesa == null && other.idMesa != null) || (this.idMesa != null && !this.idMesa.equals(other.idMesa))) {
            return false;
        }
        return true;
    }
}
