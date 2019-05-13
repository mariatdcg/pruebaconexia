package com.conexia.beans;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Maria Da Costa
 */
@Entity
public class Camarero implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCamarero;
     @Basic (optional = false)
    private String nombre;
    private String apellido1;
    private String apellido2;

    public Camarero() {
    }

    public Camarero(Integer idCamarero) {
        this.idCamarero = idCamarero;
    }

    public Camarero(Integer idCamarero, String nombre, String apellido1) {
        this.idCamarero = idCamarero;
        this.nombre = nombre;
        this.apellido1 = apellido1;
    }

    public Integer getIdCamarero() {
        return idCamarero;
    }

    public void setIdCamarero(Integer idCamarero) {
        this.idCamarero = idCamarero;
    }
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String Nombre) {
        this.nombre = Nombre;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String Apellido1) {
        this.apellido1 = Apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String Apellido2) {
        this.apellido2 = Apellido2;
    }   
    
     @Override
  public String toString() {
      return nombre + " " + apellido1;
  }
  
   @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCamarero != null ? idCamarero.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Camarero)) {
            return false;
        }
        Camarero other = (Camarero) object;
        if ((this.idCamarero == null && other.idCamarero != null) || (this.idCamarero != null && !this.idCamarero.equals(other.idCamarero))) {
            return false;
        }
        return true;
    }
}
