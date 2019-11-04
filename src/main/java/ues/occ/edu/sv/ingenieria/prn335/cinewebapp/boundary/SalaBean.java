/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ues.occ.edu.sv.ingenieria.prn335.cinewebapp.boundary;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;
import ues.occ.edu.sv.ingenieria.prn335.cinewebapp.control.AbstractFacade;
import ues.occ.edu.sv.ingenieria.prn335.cinewebapp.control.SalaFacade;
import ues.occ.edu.sv.ingenieria.prn335.cinewebapp.control.SucursalFacade;
import ues.occ.edu.sv.ingenieria.prn335.cinewebapp.entity.Sala;
import ues.occ.edu.sv.ingenieria.prn335.cinewebapp.entity.Sucursal;

/**
 *
 * @author carlos
 */
@Named(value = "salaBean")
@ViewScoped
public class SalaBean extends BackingBean<Sala> implements Serializable {

    @Inject
    private SalaFacade salaFacade;

    @Inject
    private SucursalFacade sucursalFacade;
    private List<Sucursal> sucursalList;
    private String tab;

    @PostConstruct
    @Override
    public void iniciar() {
        iniciarRelaciones();
        tab = "deshabilitado";
        super.iniciar();
    }

    public void iniciarRelaciones() {
        sucursalList = new ArrayList<>();
        if (salaFacade != null & sucursalFacade != null) {
            sucursalList = sucursalFacade.findAll();
        }
    }

    @Override
    public Object clavePorDatos(Sala object) {
        if (object != null) {
            return object.getIdSala();
        }
        return null;
    }

    @Override
    public Sala datosPorClave(String rowKey) {
        if (rowKey != null && !rowKey.isEmpty()) {
            try {
                Integer search = new Integer(rowKey);
                for (Sala tu : this.List) {
                    if (tu.getIdSala().compareTo(search) == 0) {
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
    protected AbstractFacade<Sala> getFacade() {
        return salaFacade;
    }

    @Override
    public LazyDataModel<Sala> getModelo() {
        return super.getModelo(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Sala getRegistro() {
        if (this.registro == null) {
            this.registro = new Sala();
        }
        return super.getRegistro(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void btnAgregarHandler(ActionEvent ae) {
        if (registro != null) {            
            registro.setIdSala(salaFacade.getNextId());
            salaFacade.create(registro); 
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Registro guardado éxito"));
            iniciar();            
        }
    }

    @Override
    public void onRowSelect(SelectEvent event){
        registro = (Sala) event.getObject();
        estado = "NONE";
        tab = "habilitado";
    }

    public List<Sucursal> getSucursalList() {
        return sucursalList;
    }

    public void setSucursalList(List<Sucursal> sucursalList) {
        this.sucursalList = sucursalList;
    }

    public String getTab() {
        return tab;
    }

    public void setTab(String tab) {
        this.tab = tab;
    }    

}