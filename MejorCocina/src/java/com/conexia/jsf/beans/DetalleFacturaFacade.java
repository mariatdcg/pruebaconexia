/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.conexia.jsf.beans;

import com.conexia.beans.DetalleFactura;
import com.conexia.beans.Factura;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Hp
 */
@Stateless
public class DetalleFacturaFacade extends AbstractFacade<DetalleFactura> {

    @PersistenceContext(unitName = "MejorCocinaPU")
    private EntityManager entity;
    private EntityManagerFactory entityManager ;

    @Override
    protected EntityManager getEntityManager() {
        return entity;
    }

    public DetalleFacturaFacade() {
        super(DetalleFactura.class);
    }
 
  
    public void open(){
        entityManager = Persistence.createEntityManagerFactory("MejorCocinaPU");
        entity = entityManager.createEntityManager();
    }
    
    public void close(){
        entity.close();
        entityManager.close();
    }
    
    public List getDetallesDeFactura(Factura factura){
        if(factura!= null){
      Query lineas = entity.createNamedQuery("DetalleFactura.findByIdFactura");
      lineas = lineas.setParameter("idFactura", factura.getIdFactura());
      return lineas.getResultList();
        }
      return null;
    }
        
}
