/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ues.occ.edu.sv.ingenieria.prn335.cinewebapp.boundary;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;
import ues.occ.edu.sv.ingenieria.prn335.cinewebapp.control.AbstractFacade;
import ues.occ.edu.sv.ingenieria.prn335.cinewebapp.control.AtributoAsientoFacade;
import ues.occ.edu.sv.ingenieria.prn335.cinewebapp.control.CaracteristicaAsientoFacade;
import ues.occ.edu.sv.ingenieria.prn335.cinewebapp.entity.Asiento;
import ues.occ.edu.sv.ingenieria.prn335.cinewebapp.entity.AtributoAsiento;
import ues.occ.edu.sv.ingenieria.prn335.cinewebapp.entity.AtributoAsientoPK;
import ues.occ.edu.sv.ingenieria.prn335.cinewebapp.entity.CaracteristicaAsiento;

/**
 *
 * @author melvin
 */
@Named(value = "atributoAsientoBean")
@ViewScoped
public class AtributoAsientoBean extends BackingBean<AtributoAsiento> implements Serializable{
    public AtributoAsientoBean() {
    }
    @Inject
    private AtributoAsientoFacade atributoAsientoFacade;
    List<AtributoAsiento> atributoAsientoC;
    @Inject 
    AsientoBean asientoFacade;
    @Inject
    private CaracteristicaAsientoFacade caracteristicaAsientoFacade;
    private List<CaracteristicaAsiento> caracteristicaAsientoList;
    String formularioTab;
    List<String> nombreCaracteristica =new ArrayList();
    Asiento selecionadoAS;
    protected boolean exito = false;
    protected String atributoVal;
    protected int asiento;
    protected String caracteristicaNome;
    protected int caracteristica;
    
    @Override
    public void iniciar(){
        relaciona();
        if (atributoAsientoC.size() == caracteristicaAsientoList.size()) {
            this.exito = true;
        }else{
            this.exito = false;
        }
        for (int iteracion = 0; iteracion < caracteristicaAsientoList.size(); iteracion++) {
            nombreCaracteristica.add(caracteristicaAsientoList.get(iteracion).getCaracteristica());
        }
        for (int item = 0; item < atributoAsientoC.size(); item++) {
            if (nombreCaracteristica.contains(atributoAsientoC.get(item).getCaracteristicaAsiento().getCaracteristica())) {
                nombreCaracteristica.remove(atributoAsientoC.get(item).getCaracteristicaAsiento().getCaracteristica());
            }
        }
        this.acciones = Estado.NADA;
        registro = null;
    }
    
    public void relaciona(){
    nombreCaracteristica.clear();
        caracteristicaAsientoList = caracteristicaAsientoFacade.findAll();
        atributoAsientoC = atributoAsientoFacade.AtributoAsientoIdAs(this.asiento);    
    }
    
    @Override
    public void onRowSelect(SelectEvent event) {
        acciones = Estado.OTHER;
        for (int i = 0; i < caracteristicaAsientoList.size(); i++) {
            nombreCaracteristica.add(caracteristicaAsientoList.get(i).getCaracteristica());
        }
        registro = (AtributoAsiento) event.getObject();
        this.atributoVal = registro.getValor();
        this.caracteristicaNome = registro.getCaracteristicaAsiento().getCaracteristica();
    }
    
    
     public void getAsientoRowS(SelectEvent event) {
        this.selecionadoAS = (Asiento) event.getObject();
        this.asiento = selecionadoAS.getIdAsiento();
        iniciar();
    }

     public void menuCaracteristicaAsiento() {
        for (int j = 0; j < selecionadoAS.getAtributoAsientoList().size(); j++) {
            for (int i = 0; i < caracteristicaAsientoList.size(); i++) {
                if (selecionadoAS.getAtributoAsientoList().get(j).getCaracteristicaAsiento().getIdCaracteristica() != caracteristicaAsientoList.get(i).getIdCaracteristica()) {
                    System.out.println(caracteristicaAsientoList.get(i).getCaracteristica());
                    nombreCaracteristica.add(caracteristicaAsientoList.get(i).getCaracteristica());
                }
            }
        }
    }
     
     @Override
    public void btnEditarHandler(ActionEvent ae) {
        registro.setValor(this.atributoVal);
        atributoAsientoFacade.edit(registro);
        limpiar();
        this.iniciar();
    }
     
     
    @Override
    public Object clavePorDatos(AtributoAsiento object) {
        if(object != null){
            return object.getAtributoAsientoPK();
        }
        return null;
    }

    @Override
    public AtributoAsiento datosPorClave(String rowKey) {
        if (rowKey != null && !rowKey.isEmpty()) {
            try {
                Integer buscar = new Integer(rowKey);
                for (AtributoAsiento at : this.List) {
                    if (Integer.valueOf(at.getAtributoAsientoPK().getIdAsiento()).compareTo(buscar) == 0) {
                        return at;
                    }
                }
            } catch (NumberFormatException e) {

                Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
            }
        }
        return null;
    }

    @Override
    protected AbstractFacade<AtributoAsiento> getFacade() {
        return atributoAsientoFacade;
    }
    
    @Override
    public void btnCancelarHandler(ActionEvent ae) {
        registro = null;
        limpiar();
        iniciar();
    }

    @Override
    public void btnAgregarHandler(ActionEvent ae) {
        AtributoAsiento nuevo = new AtributoAsiento();
        this.caracteristica = caracteristicaAsientoFacade.caracteristicaAsientoNombre(this.caracteristicaNome);
        AtributoAsientoPK id = new AtributoAsientoPK(caracteristica, asiento);
        nuevo.setAtributoAsientoPK(id);
        nuevo.setAsiento(selecionadoAS);
        nuevo.setCaracteristicaAsiento(caracteristicaAsientoFacade.find(caracteristica));
        nuevo.setValor(atributoVal);
        atributoAsientoFacade.create(nuevo);
        limpiar();
        this.iniciar();
    }

    @Override
    public void btnEliminarHandler(ActionEvent ae) {
        try {
            atributoAsientoFacade.remove(registro);
            limpiar();
            iniciar();
            
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
        }
    }
    
    
    
    public void limpiar() {
        this.atributoVal = new String();
        this.caracteristicaNome = new String();
    }
    
    @Override
    public AtributoAsiento getRegistro(){
        if(registro == null){
            registro = new AtributoAsiento();
        }        
        return super.getRegistro();
    }
    
    @Override
    public LazyDataModel<AtributoAsiento> getModelo(){
        return super.getModelo();
    }

    public List<CaracteristicaAsiento> getCaracteristicaAsientoList() {
        return caracteristicaAsientoList;
    }

    public void setCaracteristicaAsientoList(List<CaracteristicaAsiento> caracteristicaAsientoList) {
        this.caracteristicaAsientoList = caracteristicaAsientoList;
    }

    public List<AtributoAsiento> getAtributoAsientoC() {
        return atributoAsientoC;
    }

    public void setAtributoAsientoC(List<AtributoAsiento> atributoAsientoC) {
        this.atributoAsientoC = atributoAsientoC;
    }

    public List<String> getNombreCaracteristica() {
        return nombreCaracteristica;
    }

    public void setNombreCaracteristica(List<String> nombreCaracteristica) {
        this.nombreCaracteristica = nombreCaracteristica;
    }

    public Asiento getSelecionadoAS() {
        return selecionadoAS;
    }

    public void setSelecionadoAS(Asiento selecionadoAS) {
        this.selecionadoAS = selecionadoAS;
    }

    public boolean isExito() {
        return exito;
    }

    public void setExito(boolean exito) {
        this.exito = exito;
    }

    public String getAtributoVal() {
        return atributoVal;
    }

    public void setAtributoVal(String atributoVal) {
        this.atributoVal = atributoVal;
    }

    public int getAsiento() {
        return asiento;
    }

    public void setAsiento(int asiento) {
        this.asiento = asiento;
    }

    public String getCaracteristicaNome() {
        return caracteristicaNome;
    }

    public void setCaracteristicaNome(String caracteristicaNome) {
        this.caracteristicaNome = caracteristicaNome;
    }

    public int getCaracteristica() {
        return caracteristica;
    }

    public void setCaracteristica(int caracteristica) {
        this.caracteristica = caracteristica;
    }

    
    
    public String getFormularioTab() {
        return formularioTab;
    }

    public void setFormularioTab(String formularioTab) {
        this.formularioTab = formularioTab;
    }
    
}
