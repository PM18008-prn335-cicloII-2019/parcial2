/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ues.occ.edu.sv.ingenieria.prn335.cinewebapp.control;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import ues.occ.edu.sv.ingenieria.prn335.cinewebapp.entity.Sucursal;

/**
 *
 * @author melvin
 */
@Stateless
public class SucursalFacade extends AbstractFacade<Sucursal> {

    @PersistenceContext(unitName = "cinePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SucursalFacade() {
        super(Sucursal.class);
    }
    
    public int getNextId(){
        int nextId = 0;
        Query query = em.createQuery("SELECT MAX (t.idSucursal) FROM Sucursal t");
        if(query.getSingleResult() != null){
            nextId = (Integer) query.getSingleResult() + 1;
        }else{
            nextId = 1;
        }          
        return nextId;
    }
    
}
