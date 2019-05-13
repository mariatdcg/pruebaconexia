package com.conexia.jsf.controller;

import com.conexia.beans.DetalleFactura;
import com.conexia.beans.DetallefacturaPK;
import com.conexia.beans.Factura;
import com.conexia.jsf.controller.util.JsfUtil;
import com.conexia.jsf.controller.util.PaginationHelper;
import com.conexia.jsf.beans.FacturaFacade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;

@Named("facturaController")
@SessionScoped
public class FacturaController implements Serializable {

    private Factura current;
    private DataModel items = null;
    @EJB
    private com.conexia.jsf.beans.FacturaFacade ejbFacade;
    private com.conexia.jsf.controller.util.PaginationHelper pagination;
    private int selectedItemIndex;
    
    public FacturaController() {
    }

    public Factura getSelected() {
        if (current == null) {
            current = new Factura();
            selectedItemIndex = -1;
        }
        return current;
    }

    private com.conexia.jsf.beans.FacturaFacade getFacade() {
        return ejbFacade;
    }

    public com.conexia.jsf.controller.util.PaginationHelper getPagination() {
        if (pagination == null) {
            pagination = new com.conexia.jsf.controller.util.PaginationHelper(10) {

                @Override
                public int getItemsCount() {
                    return getFacade().count();
                }

                @Override
                public DataModel createPageDataModel() {
                    return new ListDataModel(getFacade().findRange(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}));
                }
            };
        }
        return pagination;
    }

    public String prepareList() {
        recreateModel();
        return "List";
    }

    public String prepareView() {
        current = (Factura) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }
    
     public String prepareCreatedComplete() {
        current = new Factura();
        current.setFechaFactura(new Date());
        selectedItemIndex = -1;
        return "FacturaDetalle";
    }

    public String prepareCreate() {
        current = new Factura();
        current.setFechaFactura(new Date());
        selectedItemIndex = -1;
        return "Create";
    }
    
    public String create() {
        try {
            current.setFechaFactura(new Date());
            getFacade().create(current);
            com.conexia.jsf.controller.util.JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("FacturaCreated"));
            return prepareCreate();
        } catch (Exception e) {
            com.conexia.jsf.controller.util.JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }
    
    public String createComplete() {
        try {
            current.setFechaFactura(new Date());
            DetalleFacturaController detalle = new DetalleFacturaController();
            DetallefacturaPK relation = new DetallefacturaPK();
            relation.setIdFactura(current.getIdFactura());
            detalle.prepareCreateComplete(relation);
            getFacade().create(current);
            com.conexia.jsf.controller.util.JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("FacturaCreated"));
            return prepareCreatedComplete();
        } catch (Exception e) {
            com.conexia.jsf.controller.util.JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }
    
    public String prepareEdit() {
        current = (Factura) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            getFacade().edit(current);
            com.conexia.jsf.controller.util.JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("FacturaUpdated"));
            return "View";
        } catch (Exception e) {
            com.conexia.jsf.controller.util.JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (Factura) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        performDestroy();
        recreatePagination();
        recreateModel();
        return "List";
    }

    public String destroyAndView() {
        performDestroy();
        recreateModel();
        updateCurrentItem();
        if (selectedItemIndex >= 0) {
            return "View";
        } else {
            // all items were removed - go back to list
            recreateModel();
            return "List";
        }
    }

    private void performDestroy() {
        try {
            getFacade().remove(current);
            com.conexia.jsf.controller.util.JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("FacturaDeleted"));
        } catch (Exception e) {
            com.conexia.jsf.controller.util.JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
        }
    }

    private void updateCurrentItem() {
        int count = getFacade().count();
        if (selectedItemIndex >= count) {
            // selected index cannot be bigger than number of items:
            selectedItemIndex = count - 1;
            // go to previous page if last page disappeared:
            if (pagination.getPageFirstItem() >= count) {
                pagination.previousPage();
            }
        }
        if (selectedItemIndex >= 0) {
            current = getFacade().findRange(new int[]{selectedItemIndex, selectedItemIndex + 1}).get(0);
        }
    }

    public DataModel getItems() {
        if (items == null) {
            items = getPagination().createPageDataModel();
        }
        return items;
    }

    private void recreateModel() {
        items = null;
    }

    private void recreatePagination() {
        pagination = null;
    }

    public String next() {
        getPagination().nextPage();
        recreateModel();
        return "List";
    }

    public String previous() {
        getPagination().previousPage();
        recreateModel();
        return "List";
    }

    public SelectItem[] getItemsAvailableSelectMany() {
        return com.conexia.jsf.controller.util.JsfUtil.getSelectItems(ejbFacade.findAll(), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return com.conexia.jsf.controller.util.JsfUtil.getSelectItems(ejbFacade.findAll(), true);
    }

    public Factura getFactura(int id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = Factura.class)
    public static class FacturaControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            FacturaController controller = (FacturaController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "facturaController");
            return controller.getFactura(getKey(value));
        }

        int getKey(String value) {
            int key;
            key = Integer.parseInt(value);
            return key;
        }

        String getStringKey(int value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Factura) {
                Factura o = (Factura) object;
                return getStringKey(o.getIdFactura());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Factura.class.getName());
            }
        }

    }
    
  /*  public void setDetalleFactura(DetalleFactura detalle){
        detalle.setFactura(current);
        detalleFactura.add(detalle);
        System.out.println("Se guarda el detalle " + detalle.toString());
    }*/
    
   /* public List<DetalleFactura> getDetalleFactura(){
       // if(detalleFactura.size()>0)
            return detalleFactura;
        
       // List<DetalleFactura> lista =  ejbFacade.getDetallesFactura(current.getIdFactura());
     //   return lista;
    }*/
    
    

}
