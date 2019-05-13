package com.conexia.test;


import com.conexia.beans.Camarero;
import com.conexia.beans.Cliente;
import com.conexia.beans.Cocinero;
import com.conexia.beans.Factura;
import com.conexia.beans.Mesa;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Hp
 */
public class PruebaApp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("MejorCocinaPU");
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    entityManager.getTransaction().begin();

    //Registrar una mesa
    Mesa mesa  = new Mesa();
    mesa.setNumMaxComensales(4);
    mesa.setUbicacion("Fila 1, Columna 1");
    
    Mesa mesa1  = new Mesa();
    mesa1.setNumMaxComensales(4);
    mesa1.setUbicacion("Fila 1, Columna 2");
    entityManager.persist(mesa);
    entityManager.persist(mesa1);
    
      Mesa mesa3  = new Mesa();
    mesa3.setNumMaxComensales(4);
    mesa3.setUbicacion("Fila 2, Columna 1");
        entityManager.persist(mesa3);

    Mesa mesa4  = new Mesa();
    mesa4.setNumMaxComensales(4);
    mesa4.setUbicacion("Fila 2, Columna 2");
 entityManager.persist(mesa4);
      
    
      entityManager.getTransaction().commit();
    entityManager.close();
    entityManagerFactory.close();
    }
    
}
