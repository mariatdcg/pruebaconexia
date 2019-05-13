package com.conexia.jsf.controller;

import com.conexia.beans.DetalleFactura;
import com.conexia.beans.DetallefacturaPK;

import java.io.Serializable;
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

@Named("detalleFacturaController")
@SessionScoped
public class DetalleFacturaController implements Serializable {

    private DetalleFactura current;
    private DataModel items = null;
    @EJB
    private com.conexia.jsf.beans.DetalleFacturaFacade ejbFacade;
    private com.conexia.jsf.controller.util.PaginationHelper pagination;
    private int selectedItemIndex;

    public DetalleFacturaController() {
    }

    public DetalleFactura getSelected() {
        if (current == null) {
            current = new DetalleFactura();
            current.setDetallefacturaPK(new com.conexia.beans.DetallefacturaPK());
            selectedItemIndex = -1;
        }
        return current;
    }

    private com.conexia.jsf.beans.DetalleFacturaFacade getFacade() {
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
        current = (DetalleFactura) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new DetalleFactura();
        current.setDetallefacturaPK(new DetallefacturaPK());
        selectedItemIndex = -1;
        return "Create";
    }
    
    public String prepareCreateComplete(DetallefacturaPK detalleFk) {
        current = new DetalleFactura();
      if(detalleFk!= null)
          current.setDetallefacturaPK(detalleFk);
      else
        current.setDetallefacturaPK(new DetallefacturaPK());
        selectedItemIndex = -1;
        return "FacturaDetalle";
    }

    public String create() {
        try {
            getFacade().create(current);
            com.conexia.jsf.controller.util.JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("DetalleFacturaCreated"));
            return prepareCreate();
        } catch (Exception e) {
            com.conexia.jsf.controller.util.JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }
    
  
    
    public String createComplete() {
        try {
            
            getFacade().create(current);
            com.conexia.jsf.controller.util.JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("DetalleFacturaCreated"));
            return prepareCreateComplete(new DetallefacturaPK());
        } catch (Exception e) {
            com.conexia.jsf.controller.util.JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }


    public String prepareEdit() {
        current = (DetalleFactura) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            getFacade().edit(current);
            com.conexia.jsf.controller.util.JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("DetalleFacturaUpdated"));
            return "View";
        } catch (Exception e) {
            com.conexia.jsf.controller.util.JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (DetalleFactura) getItems().getRowData();
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
            com.conexia.jsf.controller.util.JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("DetalleFacturaDeleted"));
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

    public DetalleFactura getDetalleFactura(com.conexia.beans.DetallefacturaPK id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = DetalleFactura.class)
    public static class DetalleFacturaControllerConverter implements Converter {

        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            DetalleFacturaController controller = (DetalleFacturaController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "detalleFacturaController");
            return controller.getDetalleFactura(getKey(value));
        }

        com.conexia.beans.DetallefacturaPK getKey(String value) {
            com.conexia.beans.DetallefacturaPK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new com.conexia.beans.DetallefacturaPK();
            key.setIdDetalleFactura(Integer.parseInt(values[0]));
            key.setIdFactura(Integer.parseInt(values[1]));
            return key;
        }

        String getStringKey(com.conexia.beans.DetallefacturaPK value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value.getIdDetalleFactura());
            sb.append(SEPARATOR);
            sb.append(value.getIdFactura());
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof DetalleFactura) {
                DetalleFactura o = (DetalleFactura) object;
                return getStringKey(o.getDetallefacturaPK());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + DetalleFactura.class.getName());
            }
        }

    }
    
     public List<DetalleFactura> getDetalleFactura(){
        List<DetalleFactura> lista =  ejbFacade.getDetallesDeFactura(current.getFactura());
        return lista;
    }
    
}
