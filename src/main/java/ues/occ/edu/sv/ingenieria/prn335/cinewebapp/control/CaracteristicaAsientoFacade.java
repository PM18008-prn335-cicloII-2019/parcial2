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
import ues.occ.edu.sv.ingenieria.prn335.cinewebapp.entity.CaracteristicaAsiento;

/**
 *
 * @author melvin
 */
@Stateless
public class CaracteristicaAsientoFacade extends AbstractFacade<CaracteristicaAsiento> {

    @PersistenceContext(unitName = "cinePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CaracteristicaAsientoFacade() {
        super(CaracteristicaAsiento.class);
    }
    
    public Integer caracteristicaAsientoNombre(String nome){
        Query query = em.createNamedQuery("CaracteristicaAsiento.findIdByNombre");
        query.setParameter("nombre", nome);
        return Integer.valueOf(query.getSingleResult().toString());
    }
    
}
