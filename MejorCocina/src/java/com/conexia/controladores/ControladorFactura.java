package com.conexia.controladores;

import com.conexia.beans.Camarero;
import com.conexia.beans.Cliente;
import com.conexia.beans.Cocinero;
import com.conexia.beans.DetalleFactura;
import com.conexia.beans.Factura;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.ManagedBean;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * @author Maria Da Costa
 */
@ManagedBean
@Named(value = "facturador")
@ApplicationScoped
public class ControladorFactura {
     private Factura factura = new Factura();
     private Cliente cliente = new Cliente();
     private Camarero camarero = new Camarero();
     private Cocinero cocinero = new Cocinero();
     private DetalleFactura detalleFactura = new DetalleFactura();
     
     private EntityManagerFactory entityManagerFactory ;
     private EntityManager entityManager;
   
    public void open(){
        entityManagerFactory = Persistence.createEntityManagerFactory("MejorCocinaPU");
        entityManager = entityManagerFactory.createEntityManager();
    }
    
    public void close(){
        entityManager.close();
        entityManagerFactory.close();
    }   
     
  public void registrarFactura() {
    open();
    System.out.println("Se va a registrar al cliente " + cliente.getNombre());
    entityManager.getTransaction().begin();
    entityManager.persist(cliente);
    entityManager.persist(camarero);
    entityManager.persist(cocinero);
    
    detalleFactura.setCocinero(cocinero);
    detalleFactura.setFactura(factura);
    factura.setCamarero(camarero);
    factura.setCliente(cliente);
    entityManager.persist(factura);
    
    entityManager.persist(detalleFactura);
    List<DetalleFactura> detalle = new ArrayList();
    detalle.add(detalleFactura);
    factura.setDetalleFactura(detalle);   
    
    entityManager.getTransaction().commit();
    close();
  }
  
  public Factura getFactura() {
    return factura;
  }

  public void setFactura(Factura genero) {
    this.factura = genero;
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

    public Cocinero getCocinero() {
        return cocinero;
    }

    public void setCocinero(Cocinero cocinero) {
        this.cocinero = cocinero;
    }

    public DetalleFactura getDetalleFactura() {
        return detalleFactura;
    }

    public void setDetalleFactura(DetalleFactura detalleFactura) {
        this.detalleFactura = detalleFactura;
    }
  
  
}
