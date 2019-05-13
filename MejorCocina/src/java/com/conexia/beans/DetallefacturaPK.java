/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.conexia.beans;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Maria Da Costa
 */
@Embeddable
public class DetallefacturaPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "idDetalleFactura")
    private int idDetalleFactura;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idFactura")
    private int idFactura;

    public DetallefacturaPK() {
    }

    public DetallefacturaPK(int idDetalleFactura, int idFactura) {
        this.idDetalleFactura = idDetalleFactura;
        this.idFactura = idFactura;
    }

    public int getIdDetalleFactura() {
        return idDetalleFactura;
    }

    public void setIdDetalleFactura(int idDetalleFactura) {
        this.idDetalleFactura = idDetalleFactura;
    }

    public int getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(int idFactura) {
        this.idFactura = idFactura;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idDetalleFactura;
        hash += (int) idFactura;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetallefacturaPK)) {
            return false;
        }
        DetallefacturaPK other = (DetallefacturaPK) object;
        if (this.idDetalleFactura != other.idDetalleFactura) {
            return false;
        }
        if (this.idFactura != other.idFactura) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.conexia.beans.DetallefacturaPK[ idDetalleFactura=" + idDetalleFactura + ", idFactura=" + idFactura + " ]";
    }
    
}
