/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ues.occ.edu.sv.ingenieria.prn335.cinewebapp.boundary;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.model.LazyDataModel;
import ues.occ.edu.sv.ingenieria.prn335.cinewebapp.control.AbstractFacade;
import ues.occ.edu.sv.ingenieria.prn335.cinewebapp.control.CaracteristicaAsientoFacade;
import ues.occ.edu.sv.ingenieria.prn335.cinewebapp.entity.CaracteristicaAsiento;
import ues.occ.edu.sv.ingenieria.prn335.cinewebapp.entity.Sucursal;

/**
 *
 * @author melvin
 */

@Named(value = "caracteristicaAsientoBean")
@ViewScoped
public class CaracteristicaAsientoBean extends BackingBean<CaracteristicaAsiento> implements Serializable {
    @Inject
    private CaracteristicaAsientoFacade caracAsientoFacade;
    private CaracteristicaAsiento caracAsiento;
    private List<CaracteristicaAsiento> caracAsientoList;

    public CaracteristicaAsiento getCaracAsiento() {
        return caracAsiento;
    }

    public void setCaracAsiento(CaracteristicaAsiento caracAsiento) {
        this.caracAsiento = caracAsiento;
    }

    public List<CaracteristicaAsiento> getCaracAsientoList() {
        return caracAsientoList;
    }

    public void setCaracAsientoList(List<CaracteristicaAsiento> caracAsientoList) {
        this.caracAsientoList = caracAsientoList;
    }

    

    
    
    @PostConstruct
    @Override
    public void iniciar() {
        super.iniciar(); //To change body of generated methods, choose Tools | Templates.
        iniciarRelaciones();
        
    }
    
    
    public void iniciarRelaciones(){
        if(caracAsientoFacade !=null){
            caracAsientoList=caracAsientoFacade.findAll();
        }
    }

    @Override
    public Object clavePorDatos(CaracteristicaAsiento object) {
        if (object != null) {
            return object.getIdCaracteristica();
        }
        return null;
    }

    @Override
    public CaracteristicaAsiento datosPorClave(String rowKey) {
        if (rowKey != null && !rowKey.isEmpty()) {
            try {
                Integer search = new Integer(rowKey);
                for (CaracteristicaAsiento tu : this.List) {
                    if (tu.getIdCaracteristica().compareTo(search) == 0) {
                        return tu;
                    }
                }
            } catch (NumberFormatException e) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
            }
        }
        return null;
    }

    @Override
    protected AbstractFacade<CaracteristicaAsiento> getFacade() {
        return caracAsientoFacade;
    }
    
    
    @Override
    public LazyDataModel<CaracteristicaAsiento> getModelo() {
        return super.getModelo(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CaracteristicaAsiento getRegistro() {
        if (this.registro == null) {
            this.registro = new CaracteristicaAsiento();
        }
        return super.getRegistro(); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void btnAgregarHandler(ActionEvent ae) {
        if (registro != null) {
            registro.setIdCaracteristica(caracAsientoFacade.getNextId());
            caracAsientoFacade.create(registro);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Registro guardado éxito"));
            iniciar();
        }
    }
    
}


