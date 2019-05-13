package com.conexia.beans;

import com.conexia.beans.Cocinero;
import com.conexia.beans.DetallefacturaPK;
import com.conexia.beans.Factura;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-05-12T23:37:50")
@StaticMetamodel(DetalleFactura.class)
public class DetalleFactura_ { 

    public static volatile SingularAttribute<DetalleFactura, DetallefacturaPK> detallefacturaPK;
    public static volatile SingularAttribute<DetalleFactura, Factura> factura;
    public static volatile SingularAttribute<DetalleFactura, Cocinero> cocinero;
    public static volatile SingularAttribute<DetalleFactura, String> plato;
    public static volatile SingularAttribute<DetalleFactura, Float> importe;

}