/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ues.occ.edu.sv.ingenieria.prn335.cinewebapp.control;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import ues.occ.edu.sv.ingenieria.prn335.cinewebapp.entity.AtributoAsiento;

/**
 *
 * @author melvin
 */
@Stateless
public class AtributoAsientoFacade extends AbstractFacade<AtributoAsiento> {

    @PersistenceContext(unitName = "cinePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AtributoAsientoFacade() {
        super(AtributoAsiento.class);
    }
    
    public List<AtributoAsiento> AtributoAsientoIdAs(int entero) {
        Query q = em.createNamedQuery("AtributoAsiento.findByIdAsiento");
        q.setParameter("idAsiento", entero);
        return q.getResultList();
    }
    
}

