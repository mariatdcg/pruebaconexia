/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.conexia.jsf.beans;

import com.conexia.beans.Camarero;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Hp
 */
@Stateless
public class CamareroFacade extends AbstractFacade<Camarero> {

    @PersistenceContext(unitName = "MejorCocinaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CamareroFacade() {
        super(Camarero.class);
    }
    
}
