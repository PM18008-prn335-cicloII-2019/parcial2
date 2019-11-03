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
import ues.occ.edu.sv.ingenieria.prn335.cinewebapp.control.SucursalFacade;
import ues.occ.edu.sv.ingenieria.prn335.cinewebapp.entity.Sucursal;

/**
 *
 * @author melvin
 */

@Named(value = "sucursalBean")
@ViewScoped
public class SucursalBean extends BackingBean<Sucursal> implements Serializable {
    @Inject
    private SucursalFacade sucursalFacade;
    private Sucursal sucursal;
    private List<Sucursal> sucursalList;

    public Sucursal getSucursal() {
        return sucursal;
    }

    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
    }

    
    public List<Sucursal> getSucursalList() {
        return sucursalList;
    }

    public void setSucursalList(List<Sucursal> sucursalList) {
        this.sucursalList = sucursalList;
    }

    
    
    @PostConstruct
    @Override
    public void iniciar() {
        super.iniciar(); //To change body of generated methods, choose Tools | Templates.
        iniciarRelaciones();
        
    }
    
    
    public void iniciarRelaciones(){
        if(sucursalFacade !=null){
            sucursalList=sucursalFacade.findAll();
        }
    }

    @Override
    public Object clavePorDatos(Sucursal object) {
        if (object != null) {
            return object.getIdSucursal();
        }
        return null;
    }

    @Override
    public Sucursal datosPorClave(String rowKey) {
        if (rowKey != null && !rowKey.isEmpty()) {
            try {
                Integer search = new Integer(rowKey);
                for (Sucursal tu : this.List) {
                    if (tu.getIdSucursal().compareTo(search) == 0) {
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
    protected AbstractFacade<Sucursal> getFacade() {
        return sucursalFacade;
    }
    
    
    @Override
    public LazyDataModel<Sucursal> getModelo() {
        return super.getModelo(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Sucursal getRegistro() {
        if (this.registro == null) {
            this.registro = new Sucursal();
        }
        return super.getRegistro(); //To change body of generated methods, choose Tools | Templates.
    }
   
    
}


