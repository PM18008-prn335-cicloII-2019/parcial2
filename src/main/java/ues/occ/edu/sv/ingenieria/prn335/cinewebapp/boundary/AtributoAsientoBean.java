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
import java.util.Map;
import java.util.UUID;
import java.util.WeakHashMap;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.inject.Inject;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;
import ues.occ.edu.sv.ingenieria.prn335.cinewebapp.control.AbstractFacade;
import ues.occ.edu.sv.ingenieria.prn335.cinewebapp.control.AsientoFacade;
import ues.occ.edu.sv.ingenieria.prn335.cinewebapp.control.AtributoAsientoFacade;
import ues.occ.edu.sv.ingenieria.prn335.cinewebapp.control.CaracteristicaAsientoFacade;
import ues.occ.edu.sv.ingenieria.prn335.cinewebapp.entity.Asiento;
import ues.occ.edu.sv.ingenieria.prn335.cinewebapp.entity.AtributoAsiento;
import ues.occ.edu.sv.ingenieria.prn335.cinewebapp.entity.AtributoAsientoPK;
import ues.occ.edu.sv.ingenieria.prn335.cinewebapp.entity.CaracteristicaAsiento;

/**
 *
 * @author carlos
 */
@FacesConverter("entidadConverter")
@Named(value = "atributoAsientoBean")
@ViewScoped
public class AtributoAsientoBean extends BackingBean<AtributoAsiento> implements Serializable, Converter {

    private static Map<Object, String> entities = new WeakHashMap<Object, String>();

    /**
     * Creates a new instance of AtributoAsientoBean
     */
    public AtributoAsientoBean() {
    }
    @Inject
    private AtributoAsientoFacade atributoAsientoFacade;
    private List<AtributoAsiento> atributoAsientoList;
    @Inject
    private AsientoFacade asientoFacade;
    @Inject
    private CaracteristicaAsientoFacade caracteristicaAsientoFacade;
    private List<CaracteristicaAsiento> caracteristicaAsientoList;
    private String formularioTab;
    int idAsiento = 0;
    Asiento asientoSeleccionado;

    @PostConstruct
    @Override
    public void iniciar() {
        iniciarRelaciones();
        formularioTab = "oculto";        
        super.estado = "NONE";
        registro = null;
    }

    public void iniciarRelaciones() {
        caracteristicaAsientoList = new ArrayList<>();
        atributoAsientoList = new ArrayList<>();
        if (caracteristicaAsientoFacade != null && atributoAsientoFacade != null) {
            caracteristicaAsientoList = caracteristicaAsientoFacade.findAll();
            atributoAsientoList = atributoAsientoFacade.AtributoAsientoIdAs(idAsiento);
        } else {
            caracteristicaAsientoList = Collections.EMPTY_LIST;
            atributoAsientoList = Collections.EMPTY_LIST;
        }
    }
    
    public void onAsientoRowSelect(SelectEvent ae){
        asientoSeleccionado = (Asiento) ae.getObject();
        this.idAsiento = asientoSeleccionado.getIdAsiento();
        iniciarRelaciones();
    }

    @Override
    public Object clavePorDatos(AtributoAsiento object) {
        if (object != null) {
            return object.getAtributoAsientoPK();
        }
        return null;
    }

    @Override
    public AtributoAsiento datosPorClave(String rowKey) {
        if (rowKey != null && !rowKey.isEmpty()) {
            String buscar = rowKey;
            for (AtributoAsiento a : this.List) {
                if (a.getAtributoAsientoPK().toString().equals(buscar)) {
                    return a;
                }
            }
        }
        return null;
    }

    @Override
    protected AbstractFacade<AtributoAsiento> getFacade() {
        return atributoAsientoFacade;
    }

    @Override
    public AtributoAsiento getRegistro() {
        if (registro == null) {
            registro = new AtributoAsiento();
        }
        return super.getRegistro();
    }

    @Override
    public LazyDataModel<AtributoAsiento> getModelo() {
        return super.getModelo();
    }

    @Override
    public void onRowSelect(SelectEvent event) {
        registro = (AtributoAsiento) event.getObject();
        formularioTab = "activo";
    }
    
    @Override
    public void btnCancelarHandler (ActionEvent ae){
        iniciar();
    }
    
    @Override
    public void btnEditarHandler(ActionEvent ae){
        atributoAsientoFacade.edit(registro);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Registro editado correctamente"));   
        iniciar();
    }
    
    @Override
    public void btnEliminarHandler(ActionEvent ae){
        atributoAsientoFacade.remove(registro);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Registro eliminado"));
        iniciar();
    }
    
    @Override
    public void btnAgregarHandler(ActionEvent ae){
        registro.setAsiento(asientoSeleccionado);
        AtributoAsientoPK atributoAsientoPK = new AtributoAsientoPK(registro.getCaracteristicaAsiento().getIdCaracteristica(), registro.getAsiento().getIdAsiento());
        registro.setAtributoAsientoPK(atributoAsientoPK);
        atributoAsientoFacade.create(registro);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Registro guardado correctamente"));
        iniciar();
    }

    @Override
    public void btnNuevoHandler(ActionEvent ae) {
        formularioTab = "activo";
        System.out.println("idAsiento: "+ idAsiento);
    }

    public int getIdAsiento() {
        return idAsiento;
    }

    public void setIdAsiento(int idAsiento) {
        this.idAsiento = idAsiento;
    }
    
  

    public List<CaracteristicaAsiento> getCaracteristicaAsientoList() {
        return caracteristicaAsientoList;
    }

    public void setCaracteristicaAsientoList(List<CaracteristicaAsiento> caracteristicaAsientoList) {
        this.caracteristicaAsientoList = caracteristicaAsientoList;
    }

    public String getFormularioTab() {
        return formularioTab;
    }

    public void setFormularioTab(String formularioTab) {
        this.formularioTab = formularioTab;
    }

    public List<AtributoAsiento> getAtributoAsientoList() {
        return atributoAsientoList;
    }

    public void setAtributoAsientoList(List<AtributoAsiento> atributoAsientoList) {
        this.atributoAsientoList = atributoAsientoList;
    }

    public Asiento getAsientoSeleccionado() {
        return asientoSeleccionado;
    }

    public void setAsientoSeleccionado(Asiento asientoSeleccionado) {
        this.asientoSeleccionado = asientoSeleccionado;
    }
    
    
    
    

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        for (Map.Entry<Object, String> entry : entities.entrySet()) {
            if (entry.getValue().equals(value)) {
                return entry.getKey();
            }
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object object) {
        synchronized (entities) {
            if (!entities.containsKey(object)) {
                String uuid = UUID.randomUUID().toString();
                entities.put(object, uuid);
                return uuid;
            } else {
                return entities.get(object);
            }
        }

    }

}
