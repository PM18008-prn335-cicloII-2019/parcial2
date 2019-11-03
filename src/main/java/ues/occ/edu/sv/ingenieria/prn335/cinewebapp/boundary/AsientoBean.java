/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ues.occ.edu.sv.ingenieria.prn335.cinewebapp.boundary;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;
import ues.occ.edu.sv.ingenieria.prn335.cinewebapp.control.AbstractFacade;
import ues.occ.edu.sv.ingenieria.prn335.cinewebapp.control.AsientoFacade;
import ues.occ.edu.sv.ingenieria.prn335.cinewebapp.entity.Asiento;


/**
 *
 * @author Vladimir
 */
@Named(value = "asientoBean")
@ViewScoped
public class AsientoBean extends BackingBean<Asiento> implements Serializable{

    @Inject
    private AsientoFacade asientoFacade;
    private Asiento asiento;    
    private List<Asiento> AsientoList;
    private String tab;
    
    public Asiento getAsiento() {
        return asiento;        
    }

    public void setAsiento(Asiento asiento) {
        this.asiento = asiento;
    }

    public List<Asiento> getAsientoList() {
        return AsientoList;
    }

    public void setAsientoList(List<Asiento> AsientoList) {
        this.AsientoList = AsientoList;
    }

    public String getTab() {
        return tab;
    }

    public void setTab(String tab) {
        this.tab = tab;
    }          
    
    
    
    @PostConstruct
    @Override
    public void iniciar(){
        super.iniciar();          
        tab = "deshabilitado";
        iniciarRelaciones();
    }

    public void iniciarRelaciones(){
        if (asientoFacade != null) {
            AsientoList = asientoFacade.findAll();
        }
    }


    @Override
    public Object clavePorDatos(Asiento objecto) {
        if (objecto != null) {
            return objecto.getIdAsiento();
        }
        return null;
    }

    @Override
    public Asiento datosPorClave(String rowKey) {
        if (rowKey != null && !rowKey.isEmpty()) {
            Integer buscar = new Integer(rowKey);
            for (Asiento a : this.List) {
                if (a.getIdAsiento().compareTo(buscar) == 0) {
                    return a;
                }
            }
        }
        return null;
    }

     @Override
    public Asiento getRegistro(){
        if (this.registro == null) {
            this.registro = new Asiento();
        }
        return super.getRegistro();        
    }

    @Override
    public LazyDataModel<Asiento> getModelo(){
        return super.getModelo();
    }


    @Override
    protected AbstractFacade<Asiento> getFacade() {
        return asientoFacade;
    }

    @Override
    public void onRowSelect(SelectEvent event){
        registro = (Asiento) event.getObject();
        estado = "NONE";
        tab = "habilitado";
    }
    
}