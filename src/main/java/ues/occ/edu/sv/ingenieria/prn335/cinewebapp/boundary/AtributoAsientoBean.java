/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ues.occ.edu.sv.ingenieria.prn335.cinewebapp.boundary;

import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;
import ues.occ.edu.sv.ingenieria.prn335.cinewebapp.control.AbstractFacade;
import ues.occ.edu.sv.ingenieria.prn335.cinewebapp.control.AsientoFacade;
import ues.occ.edu.sv.ingenieria.prn335.cinewebapp.control.AtributoAsientoFacade;
import ues.occ.edu.sv.ingenieria.prn335.cinewebapp.control.CaracteristicaAsientoFacade;
import ues.occ.edu.sv.ingenieria.prn335.cinewebapp.entity.AtributoAsiento;
import ues.occ.edu.sv.ingenieria.prn335.cinewebapp.entity.CaracteristicaAsiento;

/**
 *
 * @author melvin
 */
public class AtributoAsientoBean extends BackingBean<AtributoAsiento> implements Serializable{
    
    @Inject
    private AtributoAsientoFacade atrAsientoFacade;
    @Inject
    private AsientoFacade asientoFacade;
    
    @Inject
    private CaracteristicaAsientoFacade caracAsientoFacade;
    private List<CaracteristicaAsiento> allcaracAsientos;

    public List<CaracteristicaAsiento> getAllcaracAsientos() {
        return allcaracAsientos;
    }

    public void setAllcaracAsientos(List<CaracteristicaAsiento> allcaracAsientos) {
        this.allcaracAsientos = allcaracAsientos;
    }

    
    
    @Override
    public Object clavePorDatos(AtributoAsiento object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public AtributoAsiento datosPorClave(String rowKey) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected AbstractFacade<AtributoAsiento> getFacade() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
