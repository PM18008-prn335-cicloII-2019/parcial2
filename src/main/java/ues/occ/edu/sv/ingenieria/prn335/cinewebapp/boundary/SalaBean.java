/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ues.occ.edu.sv.ingenieria.prn335.cinewebapp.boundary;

import java.io.Serializable;
import javax.inject.Inject;
import javax.inject.Named;
import ues.occ.edu.sv.ingenieria.prn335.cinewebapp.control.AbstractFacade;
import ues.occ.edu.sv.ingenieria.prn335.cinewebapp.control.SalaFacade;
import ues.occ.edu.sv.ingenieria.prn335.cinewebapp.control.SucursalFacade;
import ues.occ.edu.sv.ingenieria.prn335.cinewebapp.entity.Sala;

/**
 *
 * @author melvin
 */
@Named(value = "salaBean")
public class SalaBean extends BackingBean<Sala> implements Serializable{
    
    @Inject
    SalaFacade salaFacade;
    
    @Inject
    SucursalFacade sucursalFacade;

    @Override
    public Object clavePorDatos(Sala object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Sala datosPorClave(String rowKey) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected AbstractFacade<Sala> getFacade() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
