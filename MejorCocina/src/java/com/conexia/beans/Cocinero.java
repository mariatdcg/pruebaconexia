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
public class Cocinero implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCocinero;
     @Basic (optional = false)
    private String nombre;
    private String apellido1;
    private String apellido2;

      public Cocinero(Integer idCocinero) {
        this.idCocinero = idCocinero;
    }

    public Cocinero(Integer idCocinero, String nombre, String apellido1) {
        this.idCocinero = idCocinero;
        this.nombre = nombre;
        this.apellido1 = apellido1;
    }

    public Cocinero() {
       
    }
    
    public Integer getIdCocinero() {
        return idCocinero;
    }

    public void setIdCocinero(Integer idCocinero) {
        this.idCocinero = idCocinero;
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

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }   
    
     @Override
  public String toString() {
      return nombre + " " + apellido1;
  }
  
      @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCocinero != null ? idCocinero.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cocinero)) {
            return false;
        }
        Cocinero other = (Cocinero) object;
        if ((this.idCocinero == null && other.idCocinero != null) || (this.idCocinero != null && !this.idCocinero.equals(other.idCocinero))) {
            return false;
        }
        return true;
    }
}
