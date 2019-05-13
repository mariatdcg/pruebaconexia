package com.conexia.test;

import com.conexia.beans.DetalleFactura;
import com.conexia.beans.Factura;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 * @author Maria Da Costa
 */
public class PruebaLectura {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    //Obtener una instancia de EntityManagerFactory
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("MejorCocinaPU");
    //Obtener una instancia de EntityManager
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    
    //Obtener una instancia de Query
    Query query = entityManager.createQuery("SELECT f FROM Factura f where f.idFactura = 1");
    //Instruir a la instancia de Query que ejecute la consulta
    Factura factura = (Factura) query.getSingleResult();
    //Procesar los datos devueltos
    System.out.println("Información de Factura: ");
    System.out.println("ID: " + factura.getIdFactura());
    System.out.println("Camarero: " + factura.getCamarero().getNombre());
    System.out.println("Cliente: " + factura.getCliente().getNombre());
    System.out.println("Mesa: " + factura.getMesa().getUbicacion());

    System.out.println("Detalle de factura");
    for(DetalleFactura detalle : factura.getDetalleFactura()){
      System.out.println("- Cocinero: " + detalle.getCocinero().getNombre() +
        " Plato: " + detalle.getPlato()+
        " Precio Unitario: " + detalle.getImporte());
    }
    
   
    Query query1 = entityManager.createQuery("SELECT f FROM Factura f");
    //Instruir a la instancia de Query que ejecute la consulta
    List<Factura> listaFacturas = (List) query1.getResultList();
    //Procesar los datos devueltos
    System.out.println();
    System.out.println("Lista de factura del cliente");
    for(Factura f : listaFacturas) {
      System.out.println("ID: " + f.getIdFactura() + " fecha: " + f.getFechaFactura());
    }
    
    //Cerrar la instancia de EntityManager
    entityManager.clear();
    //Cerrar la instancia de EntityManagerFactory
    entityManagerFactory.close();
  }
    
    
}
