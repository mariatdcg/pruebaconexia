
package com.conexia.jsf.beans;

import com.conexia.beans.DetalleFactura;
import com.conexia.beans.Factura;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

/**
 *
 * @author Hp
 */
@Stateless
public class FacturaFacade extends AbstractFacade<Factura> {

    @PersistenceContext(unitName = "MejorCocinaPU")
    private EntityManager entity;
    private EntityManagerFactory entityManager ;
  
    public void open(){
        entityManager = Persistence.createEntityManagerFactory("MejorCocinaPU");
        entity = entityManager.createEntityManager();
    }
    
    public void close(){
        entity.close();
        entityManager.close();
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return entity;
    }

    public FacturaFacade() {
        super(Factura.class);
    }
    
   public List<DetalleFactura> getDetallesFactura(Integer idFactura){
         List<DetalleFactura> lista = new ArrayList<DetalleFactura>();
       try{
         //open();
           Query query = entity.createQuery("SELECT d FROM DetalleFactura d WHERE d.idFactura = "+idFactura);
                
           lista = query.getResultList();
        //   close();
       }catch(Exception e){
           System.out.println("Error al buscar la lista de Tareas"+e.getMessage());
       }
       return lista;
    }
}
